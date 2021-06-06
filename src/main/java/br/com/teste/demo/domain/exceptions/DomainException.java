package br.com.teste.demo.domain.exceptions;

public abstract class DomainException extends Exception {
    public DomainException(String message) {
        super(message);
    }
}
