package com.teachit.teachItBackEnd.model;

import lombok.*;

import javax.persistence.*;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;
    private String body;
    private String owner;
    private String date;
    @ManyToOne
    private Blog blog;

}
