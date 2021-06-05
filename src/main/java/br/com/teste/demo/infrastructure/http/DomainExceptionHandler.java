package br.com.teste.demo.infrastructure.http;

import br.com.teste.demo.domain.exceptions.DomainException;
import br.com.teste.demo.infrastructure.http.messages.BadRequestMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestControllerAdvice
public class DomainExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(DomainExceptionHandler.class);

    @ExceptionHandler(DomainException.class)
    public ResponseEntity handleException(HttpServletRequest request, DomainException ex){
        log.info(ex.getMessage(), ex);

        var badRequestMessage = new BadRequestMessage(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), new Date());

        return new ResponseEntity<>(badRequestMessage, HttpStatus.BAD_REQUEST);
    }
}
