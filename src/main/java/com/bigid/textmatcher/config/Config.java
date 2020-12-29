package com.bigid.textmatcher.config;

import com.bigid.textmatcher.handlers.Matcher;
import com.bigid.textmatcher.handlers.MatcherHandler;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.List;

@Configuration
public class Config {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    @Scope(value = "prototype")
    public Matcher matcherHandler(String text, List<String> words, int lineOffset) {
        return new MatcherHandler(text, words, lineOffset);
    }
}


