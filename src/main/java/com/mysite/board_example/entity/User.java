package com.mysite.board_example.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "profile_picture")
    private String profilePicture;

    @Column(name = "profile_introduce", columnDefinition = "TEXT")
    private String profileIntroduce;

    @OneToMany(mappedBy = "user")
    private List<Article> articles = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    public void updateProfilePicture(String profilePicture){
        this.profilePicture = profilePicture;
    }

    public void updateProfileIntroduce(String profileIntroduce){
        this.profileIntroduce = profileIntroduce;
    }

    @Builder
    public User(String id, String password, String name){
        this.id = id;
        this.password = password;
        this.name = name;
    }
}
