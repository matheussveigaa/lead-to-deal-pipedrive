package br.com.teste.demo.domain.usecase;

import br.com.teste.demo.domain.entities.Lead;
import br.com.teste.demo.domain.enums.LeadSituation;
import br.com.teste.demo.domain.repository.LeadRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

class SaveLeadUseCaseTest {

    private LeadRepository leadRepository;

    @Test
    @DisplayName("Should save a lead if is in WON or LOST status with success")
    void should_saveLeadIfIsInWonOrLost_withSuccess() {
        //arrange
        var lead = new Lead();
        lead.setStatus(LeadSituation.LOST);
        lead.setCompany("Empresa teste");
        lead.setEmail("email@teste.com");
        lead.setUserId(UUID.randomUUID().toString());
        lead.setName("João da silva");

        var leadToSave = new Lead();
        leadToSave.setEmail("email@teste.com");

        leadRepository = mock(LeadRepository.class);

        when(leadRepository.findByEmail(leadToSave.getEmail())).thenReturn(lead);
        doAnswer(invocationOnMock -> {
            var leadMock = (Lead)invocationOnMock.getArgument(0);

            leadMock.setId(UUID.randomUUID().toString());

            return leadMock;
        }).when(leadRepository).save(leadToSave);

        var saveLeadUseCase = new SaveLeadUseCase(leadRepository);

        //act
        saveLeadUseCase.handle(leadToSave);

        //assert
        verify(leadRepository, times(1)).findByEmail(leadToSave.getEmail());
        verify(leadRepository, times(1)).save(leadToSave);

        assertThat(leadToSave.getId(), not(emptyOrNullString()));
        assertThat(leadToSave.getEmail(), is(lead.getEmail()));
    }

    @Test
    @DisplayName("Should save a lead if is not exists in database with success")
    void should_saveLead_withSuccess() {
        //arrange
        var leadToSave = new Lead();
        leadToSave.setStatus(LeadSituation.LOST);
        leadToSave.setCompany("Empresa teste");
        leadToSave.setEmail("email@teste.com");
        leadToSave.setUserId(UUID.randomUUID().toString());
        leadToSave.setName("João da silva");

        leadRepository = mock(LeadRepository.class);

        when(leadRepository.findByEmail(leadToSave.getEmail())).thenReturn(null);
        doAnswer(invocationOnMock -> {
            var leadMock = (Lead)invocationOnMock.getArgument(0);

            leadMock.setId(UUID.randomUUID().toString());

            return leadMock;
        }).when(leadRepository).save(leadToSave);

        var saveLeadUseCase = new SaveLeadUseCase(leadRepository);

        //act
        saveLeadUseCase.handle(leadToSave);

        //assert
        verify(leadRepository, times(1)).findByEmail(leadToSave.getEmail());
        verify(leadRepository, times(1)).save(leadToSave);

        assertThat(leadToSave.getId(), not(emptyOrNullString()));
        assertThat(leadToSave.getStatus(), is(LeadSituation.OPEN));
    }

    @Test
    @DisplayName("Should not save a lead if is in OPEN status with success")
    void shouldNot_saveLeadIfIsInOpen_withSuccess() {
        //arrange
        var lead = new Lead();
        lead.setStatus(LeadSituation.OPEN);
        lead.setCompany("Empresa teste");
        lead.setEmail("email@teste.com");
        lead.setUserId(UUID.randomUUID().toString());
        lead.setName("João da silva");

        var leadToSave = new Lead();
        leadToSave.setEmail("email@teste.com");

        leadRepository = mock(LeadRepository.class);

        when(leadRepository.findByEmail(leadToSave.getEmail())).thenReturn(lead);
        doAnswer(invocationOnMock -> {
            var leadMock = (Lead)invocationOnMock.getArgument(0);

            leadMock.setId(UUID.randomUUID().toString());

            return leadMock;
        }).when(leadRepository).save(leadToSave);

        var saveLeadUseCase = new SaveLeadUseCase(leadRepository);

        //act
        saveLeadUseCase.handle(leadToSave);

        //assert
        verify(leadRepository, times(1)).findByEmail(leadToSave.getEmail());
        verify(leadRepository, never()).save(leadToSave);

        assertThat(leadToSave.getId(), is(emptyOrNullString()));
        assertThat(leadToSave.getEmail(), is(lead.getEmail()));
    }
}