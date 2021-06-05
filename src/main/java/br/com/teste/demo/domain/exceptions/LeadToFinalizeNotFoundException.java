package br.com.teste.demo.domain.exceptions;

public class LeadToFinalizeNotFoundException extends DomainException {
    public LeadToFinalizeNotFoundException() {
    }

    public LeadToFinalizeNotFoundException(String message) {
        super(message);
    }

    public LeadToFinalizeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public LeadToFinalizeNotFoundException(Throwable cause) {
        super(cause);
    }

    public LeadToFinalizeNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
