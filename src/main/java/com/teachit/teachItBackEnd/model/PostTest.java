package com.teachit.teachItBackEnd.model;

import lombok.*;

import javax.persistence.*;

@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PostTest {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    private String title;
    private String body;
    private String email;
    // to let posts know it belongs to a user

}
