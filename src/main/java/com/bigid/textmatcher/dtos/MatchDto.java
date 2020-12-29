package com.bigid.textmatcher.dtos;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

public class MatchDto implements Serializable {

    private static final long serialVersionUID = 3561739410439178363L;

    private int lineOffset;
    private int charOffset;

    public int getLineOffset() {
        return lineOffset;
    }

    public void setLineOffset(int lineOffset) {
        this.lineOffset = lineOffset;
    }

    public int getCharOffset() {
        return charOffset;
    }

    public void setCharOffset(int charOffset) {
        this.charOffset = charOffset;
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
