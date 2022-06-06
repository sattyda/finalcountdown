package com.sattyda.SATBOT.repos;

import com.sattyda.SATBOT.entities.QnA;
import com.sattyda.SATBOT.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {
}
