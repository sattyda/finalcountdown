package com.sattyda.SATBOT.controllers;

import com.sattyda.SATBOT.entities.Conversation;
import com.sattyda.SATBOT.utils.CommonService;
import com.sattyda.SATBOT.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiController {

    @Autowired
    CommonService commonService;

//    @ResponseBody
    @RequestMapping(value = "/api/hello" , method = RequestMethod.POST)
    public ResponseEntity<Response> hello(@RequestParam("index") Long index , @RequestParam("message") String msg){
        String user =  "anonymous";
        if( index == 0){
            Conversation con = commonService.createConversation( user );
            index = con.getId();
        }

        commonService.saveChat( index, user , msg , "user" );


        String botResponse = "this is hello";

        Response response = new Response();
        response.setStatus("success");
        response.setData(botResponse);
        response.setConvId(index);

        commonService.saveChat( index, user , botResponse , "bot" );

        return new ResponseEntity<>( response , HttpStatus.OK);
    }


}
