package br.com.teste.demo.domain.exceptions;

public class LeadToFinalizeNotFoundException extends DomainException {
    public LeadToFinalizeNotFoundException(String message) {
        super(message);
    }
}
