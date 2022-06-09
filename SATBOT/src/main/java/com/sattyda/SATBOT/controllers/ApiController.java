package com.sattyda.SATBOT.controllers;

import com.sattyda.SATBOT.entities.Conversation;
import com.sattyda.SATBOT.entities.Message;
import com.sattyda.SATBOT.entities.User;
import com.sattyda.SATBOT.utils.ChatInput;
import com.sattyda.SATBOT.utils.CommonService;
import com.sattyda.SATBOT.utils.MessageBody;
import com.sattyda.SATBOT.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@RestController
public class ApiController {

    @Autowired
    CommonService commonService;

    @ResponseBody
    @RequestMapping(value = "/api/hello" , method = RequestMethod.POST)
    public ResponseEntity<Response> hello(@RequestBody MessageBody messageBody){
        String user =  "anonymous";

        Authentication authetication = SecurityContextHolder.getContext().getAuthentication();

        if( authetication != null && authetication.getPrincipal() instanceof UserDetails ){
            UserDetails userDetails = (UserDetails) authetication.getPrincipal() ;
            user = userDetails.getUsername();
        }

        if( messageBody.getIndex() == 0){
            Conversation con = commonService.createConversation( user );
            messageBody.setIndex( con.getId() );
        }

        commonService.saveChat( messageBody.getIndex(), user , messageBody.getMessage() , "user" );


        String botResponse = satbot(messageBody.getMessage() );

        Response response = new Response();
        response.setStatus("success");
        response.setData(botResponse);
        response.setConvId(messageBody.getIndex());

        commonService.saveChat( messageBody.getIndex(), user , botResponse , "bot" );

        return new ResponseEntity<>( response , HttpStatus.OK);
    }


    public String satbot(String message ){

        Authentication authetication = SecurityContextHolder.getContext().getAuthentication();

        if( authetication != null && authetication.getPrincipal() instanceof UserDetails ){
            UserDetails userDetails = (UserDetails) authetication.getPrincipal() ;
            String[] userpropeties = { "name" , "email" , "department" , "faculty" , "mobile"};
            if(Arrays.stream(userpropeties).toList().contains( message.toLowerCase())){
                User user = commonService.getUser(userDetails.getUsername());
                switch ( message.toLowerCase() ){
                    case "name" : return user.getName();
                    case "email" : return user.getEmail();
                    case "department" : return user.getDepartment();
                    case "faculty" : return user.getFaculty();
                    case "mobile" : return user.getMobile();
                }
            }
        }

        String qna = commonService.isInQnA( message );
        return qna;
    }



    @ResponseBody
    @RequestMapping(value = "/admin/conversations" , method = RequestMethod.POST)
    public ResponseEntity<List<Conversation>> conversations(){
        List<Conversation> response = commonService.getConversations();
        return new ResponseEntity<>( response , HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/admin/messages" , method = RequestMethod.POST)
    public ResponseEntity<List<Message>> messages(@RequestBody ChatInput chatInput){
        List<Message> response = commonService.getMessages( chatInput.getId() );
        return new ResponseEntity<>( response , HttpStatus.OK);
    }
}
