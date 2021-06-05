package br.com.teste.demo.domain.usecase;

import br.com.teste.demo.domain.entities.Lead;
import br.com.teste.demo.domain.enums.LeadSituation;
import br.com.teste.demo.domain.repository.LeadRepository;

public class SaveLeadFoundUseCase {

    private LeadRepository leadRepository;

    public SaveLeadFoundUseCase(LeadRepository leadRepository) {
        this.leadRepository = leadRepository;
    }

    public void handle(Lead leadFound, Lead leadRequest) {
        var canMerge = leadFound.getStatus() == LeadSituation.LOST || leadFound.getStatus() == LeadSituation.WON;

        if(canMerge) {
            leadRequest.merge(leadFound);

            leadRequest.setStatus(LeadSituation.OPEN);

            this.leadRepository.save(leadRequest);
        }
    }
}
