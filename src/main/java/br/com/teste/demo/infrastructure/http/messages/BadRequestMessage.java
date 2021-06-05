package br.com.teste.demo.infrastructure.http.messages;

import java.io.Serializable;
import java.util.Date;

public class BadRequestMessage implements Serializable {
    private String message;
    private int code;
    private Date timestamp;

    public BadRequestMessage(String message, int code, Date timestamp) {
        this.message = message;
        this.code = code;
        this.timestamp = timestamp;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
