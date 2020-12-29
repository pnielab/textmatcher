package com.bigid.textmatcher.handlers;

import com.bigid.textmatcher.dtos.WordMatch;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface Matcher {

    CompletableFuture<List<WordMatch>> match();

}
