package br.com.teste.demo.domain.usecase;

import br.com.teste.demo.domain.entities.Lead;
import br.com.teste.demo.domain.enums.LeadSituation;
import br.com.teste.demo.domain.repository.LeadRepository;

public class SaveLeadUseCase {

    private LeadRepository leadRepository;

    private SaveLeadFoundUseCase saveLeadFoundUseCase;

    public SaveLeadUseCase(LeadRepository leadRepository) {
        this.leadRepository = leadRepository;
        this.saveLeadFoundUseCase = new SaveLeadFoundUseCase(leadRepository);
    }

    public void handle(Lead lead) {
        var leadFound = this.leadRepository.findByEmail(lead.getEmail());

        if(leadFound != null) {
            this.saveLeadFoundUseCase.handle(leadFound, lead);

            return;
        }

        lead.setStatus(LeadSituation.OPEN);

        this.leadRepository.save(lead);
    }
}
