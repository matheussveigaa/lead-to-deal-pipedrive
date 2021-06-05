package br.com.teste.demo.domain.entities;

import br.com.teste.demo.domain.enums.LeadSituation;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Lead implements Serializable {

    private String id;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String company;

    private String site;

    @NotEmpty
    private List<LeadPhone> phones;

    @NotNull
    private LeadSituation status;

    private String annotations;

    private String userId;

    private Date promotedAt;

    public Lead() {
        this.status = LeadSituation.OPEN;
        this.phones = new ArrayList<>();
    }

    public Lead(@NotBlank String name, @NotBlank @Email String email, @NotBlank String company, String site, @NotEmpty List<LeadPhone> phones, @NotNull LeadSituation status, String annotations, String userId, Date promotedAt) {
        this.name = name;
        this.email = email;
        this.company = company;
        this.site = site;
        this.phones = phones;
        this.status = status;
        this.annotations = annotations;
        this.userId = userId;
        this.promotedAt = promotedAt;
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

    public List<LeadPhone> getPhones() {
        return phones;
    }

    public void setPhones(List<LeadPhone> phones) {
        this.phones = phones;
    }

    public LeadSituation getStatus() {
        return status;
    }

    public void setStatus(@NotNull LeadSituation status) {
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

    public Date getPromotedAt() {
        return promotedAt;
    }

    public void setPromotedAt(Date promotedAt) {
        this.promotedAt = promotedAt;
    }

    public void merge(Lead second) {
        Class<?> clazz = this.getClass();
        Field[] fields = clazz.getDeclaredFields();

        try {
            for (Field field : fields) {
                field.setAccessible(true);

                Object value1 = field.get(this);
                Object value2 = field.get(second);

                var hasChanges = (value1 != value2 && value1 != null);

                Object value = hasChanges ? value1 : value2;

                field.set(this, value);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
