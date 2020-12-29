package com.bigid.textmatcher.controllers;

import com.bigid.textmatcher.dtos.ErrorDto;
import com.bigid.textmatcher.dtos.MatchDto;
import com.bigid.textmatcher.services.AggregatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/matcher")
public class TextMatcherController {


    private static final Logger logger = LoggerFactory.getLogger(TextMatcherController.class);
    @Autowired
    private AggregatorService matcher;


    @RequestMapping(value = "/match", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, List<MatchDto>> getMatches() {
        return matcher.getMatches();
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorDto onError(Exception e, HttpServletResponse res) {
        logger.error("unexpected error: {}", e.getMessage(), e);
        res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ErrorDto("unexpected error");
    }

}
