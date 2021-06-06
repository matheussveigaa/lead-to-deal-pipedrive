package br.com.teste.demo.application.request;

import br.com.teste.demo.application.dto.LeadPhoneDTO;
import br.com.teste.demo.domain.enums.LeadSituation;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SaveLeadRequest implements Serializable {

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String company;

    private String site;

    @NotEmpty
    private List<LeadPhoneDTO> phones;

    private LeadSituation status;

    private String annotations;

    private String userId;

    public SaveLeadRequest() {
        this.phones = new ArrayList<>();
    }

    public SaveLeadRequest(@NotBlank String name, @NotBlank @Email String email, @NotBlank String company, String site, @NotEmpty List<LeadPhoneDTO> phones, LeadSituation status, String annotations, String userId) {
        this.name = name;
        this.email = email;
        this.company = company;
        this.site = site;
        this.phones = phones;
        this.status = status;
        this.annotations = annotations;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public List<LeadPhoneDTO> getPhones() {
        return phones;
    }

    public void setPhones(List<LeadPhoneDTO> phones) {
        this.phones = phones;
    }

    public LeadSituation getStatus() {
        return status;
    }

    public void setStatus(LeadSituation status) {
        this.status = status;
    }

    public String getAnnotations() {
        return annotations;
    }

    public void setAnnotations(String annotations) {
        this.annotations = annotations;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
