package com.sattyda.SATBOT.repos;

import com.sattyda.SATBOT.entities.Conversation;
import com.sattyda.SATBOT.entities.QnA;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConvRepo extends JpaRepository<Conversation, Long> {
}

