package com.social.media.application.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "user")
// This is bean for user. It consists of all properties of user.
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(length = 25, unique = true, nullable = false)
    private String username;

    @Column(length = 50, unique = true, nullable = false)
    private String email;

    @Column(length = 50, unique = true, nullable = false)
    private String password;

    @Column(length = 50, nullable = false)
    private String fullName;

    @Column(nullable = false)
    private long phoneNo;

    @Column(length = 200, nullable = false)
    private String dateOfBirth;

    @Column(length = 200)
    private String bio;

    // This relationship contains all images uploaded by user in single LinkedList.
    @OneToMany(mappedBy = "userId",cascade = CascadeType.REMOVE,fetch=FetchType.LAZY)
    @JsonIgnore
    List<UserPost> allPosts;

}