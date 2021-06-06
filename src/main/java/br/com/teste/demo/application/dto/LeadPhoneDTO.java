package br.com.teste.demo.application.dto;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class LeadPhoneDTO implements Serializable {
    @NotBlank
    private String code;

    @NotBlank
    private String number;

    public LeadPhoneDTO() {
    }

    public LeadPhoneDTO(@NotBlank String code, @NotBlank String number) {
        this.code = code;
        this.number = number;
    }

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
