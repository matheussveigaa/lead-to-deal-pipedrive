package br.com.teste.demo.infrastructure.integrations.pipedrive.dto;

import br.com.teste.demo.infrastructure.integrations.pipedrive.dto.person.PersonDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class DealDTO implements Serializable {

    private String id;

    private String title;

    private String status;

    @JsonProperty("person_id")
    private PersonDTO person;

    @JsonProperty("org_id")
    private OrganizationDTO organization;

    public DealDTO() {
    }

    public DealDTO(String id, String title, String status, PersonDTO person, OrganizationDTO organization) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.person = person;
        this.organization = organization;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PersonDTO getPerson() {
        return person;
    }

    public void setPerson(PersonDTO person) {
        this.person = person;
    }

    public OrganizationDTO getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationDTO organization) {
        this.organization = organization;
    }
}
