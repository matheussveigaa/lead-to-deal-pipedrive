package br.com.teste.demo.infrastructure.integrations.pipedrive.dto;

import java.io.Serializable;

public class PipedriveResponseDTO<T> implements Serializable {

    private boolean success;

    private T data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
