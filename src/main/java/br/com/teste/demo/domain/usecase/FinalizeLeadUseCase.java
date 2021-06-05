package br.com.teste.demo.domain.usecase;

import br.com.teste.demo.domain.enums.LeadSituation;
import br.com.teste.demo.domain.exceptions.CanNotFinalizeLeadException;
import br.com.teste.demo.domain.exceptions.DomainException;
import br.com.teste.demo.domain.exceptions.LeadToFinalizeNotFoundException;
import br.com.teste.demo.domain.repository.LeadRepository;

public class FinalizeLeadUseCase {
    private LeadRepository leadRepository;

    public FinalizeLeadUseCase(LeadRepository leadRepository) {
        this.leadRepository = leadRepository;
    }

    public void handle(String leadId, LeadSituation situation) throws DomainException {
        if(situation == LeadSituation.OPEN)
            throw new CanNotFinalizeLeadException("Lead can be finalized only in WON or LOST status");

        var lead = this.leadRepository.findById(leadId);

        if(lead == null)
            throw new LeadToFinalizeNotFoundException("Lead to finalize not found");

        lead.setStatus(situation);

        this.leadRepository.save(lead);
    }
}
