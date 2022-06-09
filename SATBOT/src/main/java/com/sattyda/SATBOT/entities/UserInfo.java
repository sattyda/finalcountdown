package com.sattyda.SATBOT.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {



    @Id
    String email;

    String department;

    String mobile;

    String faculty;

    String name;

    String type;

}
