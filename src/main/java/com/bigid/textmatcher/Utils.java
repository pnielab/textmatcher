package com.bigid.textmatcher;

import com.bigid.textmatcher.dtos.WordMatch;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class Utils {

    private Utils() {
    }

    public static List<List<WordMatch>> allOf(List<CompletableFuture<List<WordMatch>>> futuresList) {
        CompletableFuture<Void> allFuturesResult =
                CompletableFuture.allOf(futuresList.toArray(new CompletableFuture[futuresList.size()]));
        return allFuturesResult.thenApply(v ->
                futuresList.stream().
                        map(future -> future.join()).
                        collect(Collectors.toList())
        ).join();
    }
}
