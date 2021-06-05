package br.com.teste.demo.infrastructure.integrations.pipedrive.dto;

import java.io.Serializable;

public class OrganizationDTO implements Serializable {
    private String id;

    private String name;

    public OrganizationDTO() {
    }

    public OrganizationDTO(String id, String name) {
        this.id = id;
        this.name = name;
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
}
