package com.social.media.application.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user_Post")
// This is a bean for user post.
public class UserPost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long userId;

    @Column(length = 10000)
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "userId",referencedColumnName = "id",insertable = false,updatable = false)
    @JsonBackReference
    User user;

    // Default constructor for userPost
    public UserPost() {
    }

    // Parameterized constructor for userPost
    public UserPost(Long id, byte[] image) {
        this.userId=id;
        this.image = image;
    }
}

