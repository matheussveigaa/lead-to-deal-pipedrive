package br.com.teste.demo.infrastructure.integrations.pipedrive.dto;

import java.io.Serializable;

public class NoteDTO implements Serializable {

    private String dealId;

    private String personId;

    private String content;

    public NoteDTO() {
    }

    public NoteDTO(String dealId, String personId, String content) {
        this.dealId = dealId;
        this.personId = personId;
        this.content = content;
    }

    public String getDealId() {
        return dealId;
    }

    public void setDealId(String dealId) {
        this.dealId = dealId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
