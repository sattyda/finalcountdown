package com.SATBOT.api.repos;

import com.SATBOT.api.entities.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConvRepo extends JpaRepository<Conversation, Long> {
    List<Conversation> findAllByUser(String username);

}

