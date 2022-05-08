package com.social.media.application.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Table(name = "following")
@IdClass(FollowingKey.class)
// This bean represents the connection between users.
public class Following {

    @Id
    private long userId;
    @Id
    private long gotId;

    public Following() {
    }

    public Following(long userId, long gotId) {
        this.userId = userId;
        this.gotId = gotId;
    }
}
