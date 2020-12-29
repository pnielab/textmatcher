package com.bigid.textmatcher.handlers;

import com.bigid.textmatcher.Utils;
import com.bigid.textmatcher.dtos.WordMatch;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;


public class MatcherHandler implements Matcher {
    private String text;
    private List<String> words;
    private int lineOffset;

    public MatcherHandler(String text, List<String> words, int lineOffset) {
        this.text = text;
        this.words = words;
        this.lineOffset = lineOffset;
    }

    @Override
    public CompletableFuture<List<WordMatch>> match() {
        return CompletableFuture.supplyAsync(() -> {
            List<CompletableFuture<List<WordMatch>>> futuresList = words.stream().map(word -> findWordMatch(word, text, lineOffset)).collect(Collectors.toList());
            List<List<WordMatch>> wordMatches = Utils.allOf(futuresList);
            List<WordMatch> results = new LinkedList<WordMatch>();
            wordMatches.stream().forEach(matches -> {
                matches.stream().forEach(match -> {
                    results.add(match);
                });
            });
            return results;
        });
    }

    private CompletableFuture<List<WordMatch>> findWordMatch(String word, String text, int lineOffset) {
        CompletableFuture<List<WordMatch>> completableFuture
                = CompletableFuture.supplyAsync(() -> {
            List<WordMatch> matches = new LinkedList<WordMatch>();
            String wordLowerCase = word.toLowerCase();
            String textLowerCase = text.toLowerCase();
            int charOffset = 0;
            while (charOffset != -1) {
                charOffset = textLowerCase.indexOf(wordLowerCase, charOffset);
                if (charOffset != -1) {
                    matches.add(new WordMatch(word, lineOffset, charOffset));
                    charOffset++;
                }
            }
            return matches;
        });
        return completableFuture;
    }
}
