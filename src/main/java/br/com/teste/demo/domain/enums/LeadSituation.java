package br.com.teste.demo.domain.enums;

public enum LeadSituation {
    OPEN("Open"),
    WON("Won"),
    LOST("Lost");

    LeadSituation(String description) {
        this.description = description;
    }

    private String description;

    public String getDescription() {
        return description;
    }
}
