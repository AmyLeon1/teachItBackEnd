package com.teachit.teachItBackEnd.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Blog {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String email;
    private String title;
    private String description;

    @OneToMany(targetEntity = Comment.class, cascade=CascadeType.ALL)
    @JoinColumn(name = "bc_fk", referencedColumnName = "id")
    private List<Comment> comments;

//    @OneToMany(targetEntity = Comment.class,cascade = CascadeType.ALL)
//    @JoinColumn(name="bc_fk", referencedColumnName = "id")
//    private List<Comment> comments;

}
