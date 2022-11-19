package com.teachit.teachItBackEnd.model;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PostTest {

    //@Column(name = "id")
    @Id
    @GeneratedValue()
    private int id;
    private String title;
    private String body;
    private String email;
    // to let posts know it belongs to a user

}
