package com.teachit.teachItBackEnd.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements Serializable {


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "auth_user_id")
    private int auth_user_id;
    private String email;
    private String username;
    private String password;


    //one user many posts
    //mapped by the user variable in the post entity
//    @OneToMany(mappedBy = "user")
//    private List<PostTest> postTests;

//    @OneToMany(targetEntity = Post.class,cascade = CascadeType.ALL)
//    @JoinColumn(name="up_fk", referencedColumnName = "auth_user_id")
//    private List<Post> posts;





}
