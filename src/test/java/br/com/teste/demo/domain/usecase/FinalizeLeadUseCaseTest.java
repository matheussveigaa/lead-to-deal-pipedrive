package br.com.teste.demo.domain.usecase;

import br.com.teste.demo.domain.entities.Lead;
import br.com.teste.demo.domain.enums.LeadSituation;
import br.com.teste.demo.domain.exceptions.CanNotFinalizeLeadException;
import br.com.teste.demo.domain.exceptions.LeadToFinalizeNotFoundException;
import br.com.teste.demo.domain.repository.LeadRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class FinalizeLeadUseCaseTest {

    private LeadRepository leadRepository;

    @Test
    @DisplayName("Should finalize a lead (won) with success")
    void should_finalizeLeadWithWonStatus_withSuccess() {
        //arrange
        var leadId = UUID.randomUUID().toString();

        var leadToFinalize = new Lead();
        leadToFinalize.setStatus(LeadSituation.OPEN);
        leadToFinalize.setCompany("Empresa teste");
        leadToFinalize.setEmail("email@teste.com");
        leadToFinalize.setUserId(UUID.randomUUID().toString());
        leadToFinalize.setName("João da silva");
        leadToFinalize.setId(leadId);

        leadRepository = mock(LeadRepository.class);

        when(leadRepository.findById(leadId)).thenReturn(leadToFinalize);
        doAnswer(invocationOnMock -> invocationOnMock.getArgument(0)).when(leadRepository).save(leadToFinalize);

        var finalizeLeadUseCase = new FinalizeLeadUseCase(leadRepository);

        //act & assert
        assertDoesNotThrow(() -> finalizeLeadUseCase.handle(leadId, LeadSituation.WON));

        verify(leadRepository, times(1)).findById(leadId);
        verify(leadRepository, times(1)).save(leadToFinalize);

        assertThat(leadToFinalize.getStatus(), is(LeadSituation.WON));
    }

    @Test
    @DisplayName("Should finalize a lead (lost) with success")
    void should_finalizeLeadWithLostStatus_withSuccess() {
        //arrange
        var leadId = UUID.randomUUID().toString();

        var leadToFinalize = new Lead();
        leadToFinalize.setStatus(LeadSituation.OPEN);
        leadToFinalize.setCompany("Empresa teste");
        leadToFinalize.setEmail("email@teste.com");
        leadToFinalize.setUserId(UUID.randomUUID().toString());
        leadToFinalize.setName("João da silva");
        leadToFinalize.setId(leadId);

        leadRepository = mock(LeadRepository.class);

        when(leadRepository.findById(leadId)).thenReturn(leadToFinalize);
        doAnswer(invocationOnMock -> invocationOnMock.getArgument(0)).when(leadRepository).save(leadToFinalize);

        var finalizeLeadUseCase = new FinalizeLeadUseCase(leadRepository);

        //act & assert
        assertDoesNotThrow(() -> finalizeLeadUseCase.handle(leadId, LeadSituation.LOST));

        verify(leadRepository, times(1)).findById(leadId);
        verify(leadRepository, times(1)).save(leadToFinalize);

        assertThat(leadToFinalize.getStatus(), is(LeadSituation.LOST));
    }

    @Test
    @DisplayName("Should not finalize a lead when status argument is equal OPEN with success")
    void shouldNot_finalizeLeadWhenIsInOpen_withSuccess() {
        //arrange
        var leadId = UUID.randomUUID().toString();

        var leadToFinalize = new Lead();
        leadToFinalize.setStatus(LeadSituation.OPEN);
        leadToFinalize.setCompany("Empresa teste");
        leadToFinalize.setEmail("email@teste.com");
        leadToFinalize.setUserId(UUID.randomUUID().toString());
        leadToFinalize.setName("João da silva");
        leadToFinalize.setId(leadId);

        leadRepository = mock(LeadRepository.class);

        when(leadRepository.findById(leadId)).thenReturn(leadToFinalize);
        doAnswer(invocationOnMock -> invocationOnMock.getArgument(0)).when(leadRepository).save(leadToFinalize);

        var finalizeLeadUseCase = new FinalizeLeadUseCase(leadRepository);

        //act & assert
        assertThrows(CanNotFinalizeLeadException.class, () -> finalizeLeadUseCase.handle(leadId, LeadSituation.OPEN));

        verify(leadRepository, never()).findById(leadId);
        verify(leadRepository, never()).save(leadToFinalize);

        assertThat(leadToFinalize.getStatus(), is(LeadSituation.OPEN));
    }

    @Test
    @DisplayName("Should not finalize a lead when not exists in database with success")
    void shouldNot_finalizeLeadWhenNotExists_withSuccess() {
        //arrange
        var leadId = UUID.randomUUID().toString();

        var leadToFinalize = new Lead();
        leadToFinalize.setStatus(LeadSituation.OPEN);
        leadToFinalize.setCompany("Empresa teste");
        leadToFinalize.setEmail("email@teste.com");
        leadToFinalize.setUserId(UUID.randomUUID().toString());
        leadToFinalize.setName("João da silva");
        leadToFinalize.setId(leadId);

        leadRepository = mock(LeadRepository.class);

        when(leadRepository.findById(leadId)).thenReturn(null);
        doAnswer(invocationOnMock -> invocationOnMock.getArgument(0)).when(leadRepository).save(leadToFinalize);

        var finalizeLeadUseCase = new FinalizeLeadUseCase(leadRepository);

        //act & assert
        assertThrows(LeadToFinalizeNotFoundException.class, () -> finalizeLeadUseCase.handle(leadId, LeadSituation.LOST));

        verify(leadRepository, times(1)).findById(leadId);
        verify(leadRepository, never()).save(leadToFinalize);

        assertThat(leadToFinalize.getStatus(), is(LeadSituation.OPEN));
    }
}