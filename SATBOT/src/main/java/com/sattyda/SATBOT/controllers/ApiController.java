package com.sattyda.SATBOT.controllers;

import com.sattyda.SATBOT.entities.Conversation;
import com.sattyda.SATBOT.utils.CommonService;
import com.sattyda.SATBOT.utils.MessageBody;
import com.sattyda.SATBOT.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class ApiController {

    @Autowired
    CommonService commonService;

    @ResponseBody
    @RequestMapping(value = "/api/hello" , method = RequestMethod.POST)
    public ResponseEntity<Response> hello(@RequestBody MessageBody messageBody){
        String user =  "anonymous";
        if( messageBody.getIndex() == 0){
            Conversation con = commonService.createConversation( user );
            messageBody.setIndex( con.getId() );
        }

        commonService.saveChat( messageBody.getIndex(), user , messageBody.getMessage() , "user" );


        String botResponse = satbot(messageBody.getMessage());

        Response response = new Response();
        response.setStatus("success");
        response.setData(botResponse);
        response.setConvId(messageBody.getIndex());

        commonService.saveChat( messageBody.getIndex(), user , botResponse , "bot" );

        return new ResponseEntity<>( response , HttpStatus.OK);
    }

    public String satbot( String message ){
        String qna = commonService.isInQnA( message );

        return qna;
    }
}
