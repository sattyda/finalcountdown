package com.sattyda.SATBOT.utils;

import com.sattyda.SATBOT.entities.Conversation;
import com.sattyda.SATBOT.entities.Message;
import com.sattyda.SATBOT.repos.ChatRepo;
import com.sattyda.SATBOT.repos.ConvRepo;
import com.sattyda.SATBOT.repos.QRepo;
import com.sattyda.SATBOT.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommonService {

    @Autowired
    ChatRepo chatRepo;

    @Autowired
    ConvRepo convRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    QRepo qRepo;


    public Conversation createConversation(String anonymous) {
        Conversation  conversation = new Conversation();
        conversation.setUser("Anonymous");
        conversation.setStarttime( new Date());
        convRepo.save( conversation );

        return  conversation;
    }

    public void saveChat(Long index, String user, String msg , String owner) {
        Message message = new Message();
        message.setMessage(msg);
        message.setUser(user);
        message.setOwner(owner);
        message.setConversation(index);
        chatRepo.save(message);
    }
}
