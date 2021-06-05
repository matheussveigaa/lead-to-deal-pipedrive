package br.com.teste.demo.application.services;

import br.com.teste.demo.application.dto.LeadDTO;
import br.com.teste.demo.application.request.SaveLeadRequest;
import br.com.teste.demo.domain.entities.Lead;
import br.com.teste.demo.domain.enums.LeadSituation;
import br.com.teste.demo.domain.exceptions.DomainException;
import br.com.teste.demo.domain.usecase.FinalizeLeadUseCase;
import br.com.teste.demo.domain.usecase.SaveLeadUseCase;
import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeadService {

    private SaveLeadUseCase saveLeadUseCase;

    private DozerBeanMapper mapper;

    private FinalizeLeadUseCase finalizeLeadUseCase;

    @Autowired
    public LeadService(SaveLeadUseCase saveLeadUseCase, DozerBeanMapper mapper, FinalizeLeadUseCase finalizeLeadUseCase) {
        this.saveLeadUseCase = saveLeadUseCase;
        this.mapper = mapper;
        this.finalizeLeadUseCase = finalizeLeadUseCase;
    }

    public LeadDTO save(SaveLeadRequest saveLeadRequest) {
        if(saveLeadRequest == null)
            return null;

        var lead = mapper.map(saveLeadRequest, Lead.class);

        this.saveLeadUseCase.handle(lead);

        var leadSavedDTO = mapper.map(lead, LeadDTO.class);

        return leadSavedDTO;
    }

    public void finalize(String leadId, LeadSituation situation) throws DomainException {
        if(StringUtils.isBlank(leadId))
            throw new IllegalArgumentException("Lead id is invalid.");

        this.finalizeLeadUseCase.handle(leadId, situation);
    }
}
