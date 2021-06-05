package br.com.teste.demo.domain.entities;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class LeadPhone implements Serializable {
    @NotBlank
    private String code;

    @NotBlank
    private String number;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
