package br.com.teste.demo.infrastructure.integrations.pipedrive;

import br.com.teste.demo.domain.entities.Lead;
import br.com.teste.demo.domain.enums.LeadSituation;
import br.com.teste.demo.infrastructure.integrations.pipedrive.dto.DealDTO;
import br.com.teste.demo.infrastructure.integrations.pipedrive.dto.OrganizationDTO;
import br.com.teste.demo.infrastructure.integrations.pipedrive.dto.PipedriveResponseDTO;
import br.com.teste.demo.infrastructure.integrations.pipedrive.dto.person.CreatePersonDTO;
import br.com.teste.demo.infrastructure.integrations.pipedrive.dto.person.PersonDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.stream.Collectors;

@Service
public class PipedriveIntegrationService {

    private RestTemplate restTemplate;

    @Value("${pipedrive.host}")
    private String host;

    @Value("${pipedrive.deal.api}")
    private String dealApi;

    @Value("${pipedrive.organization.api}")
    private String orgApi;

    @Value("${pipedrive.person.api}")
    private String personApi;

    @Value("${pipedrive.note.api}")
    private String noteApi;

    @Value("${pipedrive.token}")
    private String token;

    @Autowired
    public PipedriveIntegrationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void createDeal(Lead lead) {

        var organization = createOrg(lead);
        var person = createPerson(lead, organization.getId());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> formMap = new LinkedMultiValueMap<>();
        formMap.add("org_id",organization.getId());
        formMap.add("person_id", person.getId());
        formMap.add("title", String.format("%s - %s", lead.getName(), lead.getCompany()));
        formMap.add("status", LeadSituation.OPEN.name());

        var httpEntity = new HttpEntity<>(formMap, headers);

        var uri = URI.create(String.format("%s%s?api_token=%s", host, dealApi, token));

        var response = this.restTemplate.exchange(
                uri,
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<PipedriveResponseDTO<DealDTO>>() {}
        );

        if(!StringUtils.isBlank(lead.getAnnotations())) {
            var responseBody = response.getBody();

            var createdDeal = responseBody.getData();

            createNote(lead, createdDeal.getId(), createdDeal.getPerson().getId(), createdDeal.getOrganization().getId());
        }
    }

    public OrganizationDTO createOrg(Lead lead) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> formMap = new LinkedMultiValueMap<>();
        formMap.add("name",lead.getCompany());

        var httpEntity = new HttpEntity<>(formMap, headers);

        var uri = URI.create(String.format("%s%s?api_token=%s", host, orgApi, token));

        var response = this.restTemplate.exchange(
                uri,
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<PipedriveResponseDTO<OrganizationDTO>>() {}
        );

        var responseBody = response.getBody();

       return responseBody.getData();
    }

    public PersonDTO createPerson(Lead lead, String orgId) {

        var person = new CreatePersonDTO();
        person.setName(lead.getName());
        person.getEmails().add(lead.getEmail());
        person.setPhones(
                lead
                    .getPhones()
                    .stream()
                    .map(phone -> String.format("(%s) %s", phone.getCode(), phone.getNumber()))
                    .collect(Collectors.toList())
        );
        person.setOrgId(orgId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        var httpEntity = new HttpEntity<>(person, headers);

        var uri = URI.create(String.format("%s%s?api_token=%s", host, personApi, token));

        var response = this.restTemplate.exchange(
                uri,
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<PipedriveResponseDTO<PersonDTO>>() {}
        );

        var responseBody = response.getBody();

        return responseBody.getData();
    }

    public void createNote(Lead lead, String dealId, String personId, String orgId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> formMap = new LinkedMultiValueMap<>();
        formMap.add("content",lead.getAnnotations());
        formMap.add("deal_id", dealId);
        formMap.add("person_id", personId);
        formMap.add("org_id", orgId);

        var httpEntity = new HttpEntity<>(formMap, headers);

        var uri = URI.create(String.format("%s%s?api_token=%s", host, noteApi, token));

        this.restTemplate.exchange(
                uri,
                HttpMethod.POST,
                httpEntity,
                PipedriveResponseDTO.class
        );
    }
}
