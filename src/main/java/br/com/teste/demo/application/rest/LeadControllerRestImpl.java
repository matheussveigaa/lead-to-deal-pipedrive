package br.com.teste.demo.application.rest;

import br.com.teste.demo.application.dto.LeadDTO;
import br.com.teste.demo.application.request.SaveLeadRequest;
import br.com.teste.demo.application.services.LeadService;
import br.com.teste.demo.domain.enums.LeadSituation;
import br.com.teste.demo.domain.exceptions.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LeadControllerRestImpl implements LeadControllerRest {

    private LeadService leadService;

    public LeadControllerRestImpl(LeadService leadService) {
        this.leadService = leadService;
    }

    @Override
    public ResponseEntity<LeadDTO> save(SaveLeadRequest saveLeadRequest) {
        LOGGER.info("start save");

        var result = this.leadService.save(saveLeadRequest);

        if(result == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if(result.getId() == null)
            return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);

        LOGGER.info("finish save");

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> finalizeLead(String leadId, LeadSituation status) throws DomainException {
        LOGGER.info("start finalize lead");

        this.leadService.finalize(leadId, status);

        LOGGER.info("finish finalize lead");

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
