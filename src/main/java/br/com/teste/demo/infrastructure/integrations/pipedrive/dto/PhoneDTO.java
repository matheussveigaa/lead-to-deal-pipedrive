package br.com.teste.demo.infrastructure.integrations.pipedrive.dto;

import java.io.Serializable;

public class PhoneDTO implements Serializable {

    private boolean primary;

    private String value;

    private String label;

    public PhoneDTO() {
    }

    public PhoneDTO(boolean primary, String value, String label) {
        this.primary = primary;
        this.value = value;
        this.label = label;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
