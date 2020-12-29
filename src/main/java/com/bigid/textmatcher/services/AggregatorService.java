package com.bigid.textmatcher.services;

import com.bigid.textmatcher.dtos.MatchDto;

import java.util.List;
import java.util.Map;

public interface AggregatorService {

    Map<String, List<MatchDto>> getMatches();

}




