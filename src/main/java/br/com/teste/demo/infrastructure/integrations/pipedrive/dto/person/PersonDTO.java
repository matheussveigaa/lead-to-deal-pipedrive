package br.com.teste.demo.infrastructure.integrations.pipedrive.dto.person;

import br.com.teste.demo.infrastructure.integrations.pipedrive.dto.EmailDTO;
import br.com.teste.demo.infrastructure.integrations.pipedrive.dto.OrganizationDTO;
import br.com.teste.demo.infrastructure.integrations.pipedrive.dto.PhoneDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PersonDTO implements Serializable {

    private String id;

    private String name;

    @JsonProperty(value = "org_id")
    private OrganizationDTO organization;

    @JsonProperty(value = "email")
    private List<EmailDTO> emails;

    @JsonProperty(value = "phone")
    private List<PhoneDTO> phones;

    public PersonDTO() {
        this.emails = new ArrayList<>();
        this.phones = new ArrayList<>();
    }

    public PersonDTO(String name, OrganizationDTO organization, List<EmailDTO> emails, List<PhoneDTO> phones) {
        this.name = name;
        this.organization = organization;
        this.emails = emails;
        this.phones = phones;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OrganizationDTO getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationDTO organization) {
        this.organization = organization;
    }

    public List<EmailDTO> getEmails() {
        return emails;
    }

    public void setEmails(List<EmailDTO> emails) {
        this.emails = emails;
    }

    public List<PhoneDTO> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneDTO> phones) {
        this.phones = phones;
    }
}
