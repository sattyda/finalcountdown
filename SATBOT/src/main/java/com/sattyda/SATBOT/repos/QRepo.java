package com.sattyda.SATBOT.repos;

import com.sattyda.SATBOT.entities.QnA;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QRepo extends JpaRepository<QnA , Long> {
}
