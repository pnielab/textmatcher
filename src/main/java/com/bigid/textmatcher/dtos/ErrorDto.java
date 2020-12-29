package com.bigid.textmatcher.dtos;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

public class ErrorDto implements Serializable {

    private static final long serialVersionUID = 680004898479456447L;
    private String error;

    public ErrorDto(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (Exception e) {
        }
        return "";
    }
}
