package br.com.teste.demo.infrastructure.integrations.pipedrive.dto.person;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CreatePersonDTO implements Serializable {

    private String name;

    @JsonProperty(value = "org_id")
    private String orgId;

    @JsonProperty(value = "email")
    private List<String> emails;

    @JsonProperty(value = "phone")
    private List<String> phones;

    public CreatePersonDTO() {
        this.emails = new ArrayList<>();
        this.phones = new ArrayList<>();
    }

    public CreatePersonDTO(String name, String orgId, List<String> emails, List<String> phones) {
        this.name = name;
        this.orgId = orgId;
        this.emails = emails;
        this.phones = phones;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }
}
