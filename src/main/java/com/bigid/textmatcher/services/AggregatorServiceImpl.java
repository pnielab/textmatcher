package com.bigid.textmatcher.services;

import com.bigid.textmatcher.Utils;
import com.bigid.textmatcher.dtos.MatchDto;
import com.bigid.textmatcher.dtos.WordMatch;
import com.bigid.textmatcher.handlers.Matcher;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
public class AggregatorServiceImpl implements AggregatorService {


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BeanFactory beanFactory;

    @Value("${data.url}")
    private String dataUrl;

    @Value("${batch.amount:1000}")
    private int batchAmount;

    private List<String> words = Arrays.asList("James", "John", "Robert", "Michael"
            , "William", "David", "Richard", "Charles", "Joseph", "Thomas", "Christopher", "Daniel", "Paul", "Mark", "Donald",
            "George", "Kenneth", "Steven", "Edward", "Brian", "Ronald", "Anthony", "Kevin", "Jason", "Matthew", "Gary", "Timothy", "Jose", "Larry", "Jeffrey",
            "Frank", "Scott", "Eric", "Stephen", "Andrew", "Raymond", "Gregory", "Joshua", "Jerry", "Dennis", "Walter", "Patrick", "Peter", "Harold", "Douglas"
            , "Henry", "Carl", "Arthur", "Ryan", "Roger");


    @Override
    public Map<String, List<MatchDto>> getMatches() {

        try (InputStream inputStream = new URL(dataUrl).openStream(); Scanner sc = new Scanner(inputStream, "UTF-8")) {
            List<CompletableFuture<List<WordMatch>>> all = new LinkedList<CompletableFuture<List<WordMatch>>>();
            int lineOffset = 0;
            StringBuilder sb = new StringBuilder();
            while (sc.hasNextLine()) {
                lineOffset++;
                String line = sc.nextLine();
                sb.append(line).append("\n");
                if (lineOffset % batchAmount == 0) {
                    Matcher matcher = beanFactory.getBean(Matcher.class, sb.toString(), words, lineOffset);
                    all.add(matcher.match());
                    sb = new StringBuilder();
                }
            }
            return aggregate(all);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, List<MatchDto>> aggregate(List<CompletableFuture<List<WordMatch>>> all) {
        List<List<WordMatch>> wordMatches = Utils.allOf(all);
        Map<String, List<MatchDto>> result = new HashMap<String, List<MatchDto>>();
        wordMatches.stream().forEach(matches -> {
            matches.stream().forEach(match -> {
                if (!result.containsKey(match.getWord())) {
                    result.put(match.getWord(), new LinkedList<MatchDto>());
                }
                result.get(match.getWord()).add(modelMapper.map(match, MatchDto.class));
            });
        });
        return result;
    }
}
