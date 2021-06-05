package br.com.teste.demo.domain.exceptions;

public class CanNotFinalizeLeadException extends DomainException {
    public CanNotFinalizeLeadException() {
    }

    public CanNotFinalizeLeadException(String message) {
        super(message);
    }

    public CanNotFinalizeLeadException(String message, Throwable cause) {
        super(message, cause);
    }

    public CanNotFinalizeLeadException(Throwable cause) {
        super(cause);
    }

    public CanNotFinalizeLeadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
