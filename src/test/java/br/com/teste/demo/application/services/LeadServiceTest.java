package br.com.teste.demo.application.services;

import br.com.teste.demo.application.dto.LeadPhoneDTO;
import br.com.teste.demo.application.request.SaveLeadRequest;
import br.com.teste.demo.domain.entities.Lead;
import br.com.teste.demo.domain.enums.LeadSituation;
import br.com.teste.demo.domain.exceptions.DomainException;
import br.com.teste.demo.domain.usecase.FinalizeLeadUseCase;
import br.com.teste.demo.domain.usecase.SaveLeadUseCase;
import org.dozer.DozerBeanMapper;
import org.dozer.MappingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LeadServiceTest {

    private SaveLeadUseCase saveLeadUseCase;

    private DozerBeanMapper mapper;

    private DozerBeanMapper mockMapper;

    private FinalizeLeadUseCase finalizeLeadUseCase;

    @BeforeEach
    void setUp() {
        this.mapper = new DozerBeanMapper();
        this.mockMapper = mock(DozerBeanMapper.class);
    }

    @Test
    @DisplayName("Should not save a lead and not mapping to dto with success")
    void shouldNot_saveLeadAndNotMappingDto_withSuccess() {
        //arrange
        saveLeadUseCase = mock(SaveLeadUseCase.class);

        var leadService = new LeadService(saveLeadUseCase, mockMapper, finalizeLeadUseCase);

        //act
        var result = leadService.save(null);

        //assert
        verify(saveLeadUseCase, never()).handle(any(Lead.class));

        assertThat(result, is(nullValue()));
    }

    @Test
    @DisplayName("Should save a lead and mapping to dto with success")
    void should_saveLeadAndMappingDto_withSuccess() {
        //arrange
        var request = new SaveLeadRequest(
                "João teste",
                "email@teste.com",
                "Empresa teste",
                "google.com",
                Arrays.asList(new LeadPhoneDTO("47", "977634223")),
                null,
                null,
                UUID.randomUUID().toString()
        );

        saveLeadUseCase = mock(SaveLeadUseCase.class);
        doAnswer(invocationOnMock -> invocationOnMock.getArgument(0)).when(saveLeadUseCase).handle(any(Lead.class));

        var leadService = new LeadService(saveLeadUseCase, mapper, finalizeLeadUseCase);

        //act
        var result = leadService.save(request);

        //assert
        assertThat(result, not(nullValue()));

        assertThat(result, is(
                allOf(
                        hasProperty("name", is(request.getName())),
                        hasProperty("company", is(request.getCompany())),
                        hasProperty("email", is(request.getEmail())),
                        hasProperty("userId", is(request.getUserId())),
                        hasProperty("site", is(request.getSite())),
                        hasProperty("phones", contains(
                                allOf(
                                        hasProperty("code", is("47")),
                                        hasProperty("number", is("977634223"))
                                )
                        ))
                )
        ));
    }

    @Test
    @DisplayName("Should not save a lead and not mapping to dto when mapping error with success")
    void shouldNot_saveLeadAndNotMappingDtoWhenMappingError_withSuccess() {
        //arrange
        var request = new SaveLeadRequest();
        request.setCompany("Empresa teste");
        request.setName("João teste");
        request.setEmail("email@teste.com");
        request.setUserId(UUID.randomUUID().toString());
        request.setSite("google.com");
        request.getPhones().add(new LeadPhoneDTO("47", "977634223"));

        saveLeadUseCase = mock(SaveLeadUseCase.class);

        doAnswer(invocationOnMock -> {
            throw new MappingException("Error mapping");
        }).when(mockMapper).map(request, Lead.class);

        var leadService = new LeadService(saveLeadUseCase, mockMapper, finalizeLeadUseCase);

        //act & assert
        assertThrows(MappingException.class, () ->  leadService.save(request));

        verify(saveLeadUseCase, never()).handle(any(Lead.class));
    }

    @Test
    @DisplayName("Should not finalize a lead when leadId is invalid with success")
    void shouldNot_finalizeLeadWhenLeadIdIsInvalid_withSuccess() throws DomainException {
        //arrange
        finalizeLeadUseCase = mock(FinalizeLeadUseCase.class);

        var leadService = new LeadService(saveLeadUseCase, mockMapper, finalizeLeadUseCase);

        //act & assert
        assertThrows(IllegalArgumentException.class, () -> leadService.finalize(null, LeadSituation.WON));

        verify(finalizeLeadUseCase, never()).handle(null, LeadSituation.WON);
    }

    @Test
    @DisplayName("Should finalize a lead with success")
    void should_finalizeLead_withSuccess() throws DomainException {
        //arrange
        var leadId = UUID.randomUUID().toString();

        finalizeLeadUseCase = mock(FinalizeLeadUseCase.class);

        var leadService = new LeadService(saveLeadUseCase, mockMapper, finalizeLeadUseCase);

        //act & assert
        assertDoesNotThrow(() -> leadService.finalize(leadId, LeadSituation.WON));

        verify(finalizeLeadUseCase, times(1)).handle(leadId, LeadSituation.WON);
    }
}