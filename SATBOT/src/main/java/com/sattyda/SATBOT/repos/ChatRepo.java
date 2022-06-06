package com.sattyda.SATBOT.repos;

import com.sattyda.SATBOT.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepo extends JpaRepository<Message, Long> {

}

