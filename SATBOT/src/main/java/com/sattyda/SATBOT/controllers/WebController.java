package com.sattyda.SATBOT.controllers;

import com.sattyda.SATBOT.entities.User;
import com.sattyda.SATBOT.utils.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class WebController {

    @Autowired
    CommonService commonService;

    BCryptPasswordEncoder bCryptPasswordEncoder =  new BCryptPasswordEncoder();

    @RequestMapping(value = "/" , method = RequestMethod.GET)
    public String index(){

        Authentication authetication = SecurityContextHolder.getContext().getAuthentication();
        if(authetication != null && authetication.getPrincipal() instanceof UserDetails && ((UserDetails)authetication.getPrincipal()).getAuthorities().stream().anyMatch( a -> a.getAuthority().equals("ADMIN"))){
            return "redirect:/admin";
        }
        return "index";
    }


    @RequestMapping(value = "/register" , method = RequestMethod.GET)
    public String register(){
        return "register";
    }

    @RequestMapping(value = "/admin" , method = RequestMethod.GET)
    public String admin(){
        return "admin";
    }

    @RequestMapping(value = "/register" , method = RequestMethod.POST)
    public String save( User user ){
        user.setPassword( bCryptPasswordEncoder.encode( user.getPassword() ));
        commonService.saveUser( user );/////  ==== 5
        return "redirect:/login";
    }
}
