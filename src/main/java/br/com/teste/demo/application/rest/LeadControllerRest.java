package br.com.teste.demo.application.rest;

import br.com.teste.demo.application.dto.LeadDTO;
import br.com.teste.demo.application.request.SaveLeadRequest;
import br.com.teste.demo.domain.enums.LeadSituation;
import br.com.teste.demo.domain.exceptions.DomainException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface LeadControllerRest {

    Logger LOGGER = LoggerFactory.getLogger(LeadControllerRest.class);

    @PostMapping("/leads")
    ResponseEntity<LeadDTO> save(@RequestBody SaveLeadRequest saveLeadRequest);

    @PatchMapping("/leads/{leadId}/finalize/{status}")
    ResponseEntity<Void> finalizeLead(@PathVariable String leadId, @PathVariable LeadSituation status) throws DomainException;
}
