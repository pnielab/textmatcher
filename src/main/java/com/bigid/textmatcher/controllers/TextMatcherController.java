package com.bigid.textmatcher.controllers;

import com.bigid.textmatcher.dtos.MatchesDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/matcher")
public class TextMatcherController {

    @RequestMapping(value = "/match", method = RequestMethod.GET)
    @ResponseBody
    public MatchesDto getMatches() {
        return new MatchesDto();
    }
}
