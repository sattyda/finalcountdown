package com.sattyda.SATBOT.controllers;

import com.sattyda.SATBOT.utils.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebController {

    @Autowired
    CommonService commonService;

    @RequestMapping(value = "/" , method = RequestMethod.GET)
    public String index(){
        return "index";
    }
}
