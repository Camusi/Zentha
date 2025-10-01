package com.zentha.zentha.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long uid;

    @Column(nullable = false)
    private Integer score;

    public User() {
        //JPA empty constructor
    }

    public User(Integer score) {
        this.score = score;
    }


    public Long getUid() {
        return uid;
    }

    public Integer getScore() {
        return score;
    }
    public void setScore(Integer score) {
        this.score = score;
    }
}
