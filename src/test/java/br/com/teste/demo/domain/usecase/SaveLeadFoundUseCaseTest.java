package br.com.teste.demo.domain.usecase;

import br.com.teste.demo.domain.entities.Lead;
import br.com.teste.demo.domain.enums.LeadSituation;
import br.com.teste.demo.domain.repository.LeadRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

class SaveLeadFoundUseCaseTest {

    private LeadRepository leadRepository;

    @Test
    @DisplayName("Should save a lead if is in WON or LOST status with success")
    void should_saveLeadIfIsInWonOrLost_withSuccess() {
        //arrange
        var leadFound = new Lead(
            "João da silva",
            "email@teste.com",
            "Empresa teste",
            null,
            new ArrayList<>(),
            LeadSituation.LOST,
            null,
            UUID.randomUUID().toString(),
            null
        );
        leadFound.setId(UUID.randomUUID().toString());

        var leadToSave = new Lead();

        leadRepository = mock(LeadRepository.class);

        doAnswer(invocationOnMock -> invocationOnMock.getArgument(0)).when(leadRepository).save(leadToSave);

        var saveLeadUseCase = new SaveLeadFoundUseCase(leadRepository);

        //act
        saveLeadUseCase.handle(leadFound, leadToSave);

        //assert
        verify(leadRepository, times(1)).save(leadToSave);

        assertThat(leadToSave.getId(), not(emptyOrNullString()));
        assertThat(leadToSave.getStatus(), is(LeadSituation.OPEN));
    }

    @Test
    @DisplayName("Should not save a lead if is in OPEN status with success")
    void shouldNot_saveLeadIfIsInOpen_withSuccess() {
        //arrange
        var leadFound = new Lead(
                "João da silva",
                "email@teste.com",
                "Empresa teste",
                null,
                new ArrayList<>(),
                LeadSituation.OPEN,
                null,
                UUID.randomUUID().toString(),
                null
        );
        leadFound.setId(UUID.randomUUID().toString());

        var leadToSave = new Lead();

        leadRepository = mock(LeadRepository.class);

        doAnswer(invocationOnMock -> invocationOnMock.getArgument(0)).when(leadRepository).save(leadToSave);

        var saveLeadUseCase = new SaveLeadFoundUseCase(leadRepository);

        //act
        saveLeadUseCase.handle(leadFound, leadToSave);

        //assert
        verify(leadRepository, never()).save(leadToSave);

        assertThat(leadToSave.getId(), is(emptyOrNullString()));
    }
}