package com.bigid.textmatcher.dtos;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

public class WordMatch implements Serializable {

    private static final long serialVersionUID = 8296320418637108264L;
    private String word;
    private int lineOffset;
    private int charOffset;


    public WordMatch(String word, int lineOffset, int charOffset) {
        this.word = word;
        this.lineOffset = lineOffset;
        this.charOffset = charOffset;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

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
