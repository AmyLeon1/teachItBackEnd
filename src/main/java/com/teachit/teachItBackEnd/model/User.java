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


//    @Column(name = "auth_user_id")
//    @GeneratedValue(strategy=GenerationType.AUTO)
//    private int auth_user_id;
    @Id
    private String email;
    private String username;
    private String password;
    private String role;

    @OneToMany
    @JoinColumn(name = "ua_fk", referencedColumnName = "email")
    private List<Appointment> appointments;

    @OneToMany
    @JoinColumn(name = "uAv_fk", referencedColumnName = "email")
    private List<AvailableDate> availableDates;


    //one user many posts
    //mapped by the user variable in the post entity
//    @OneToMany(mappedBy = "user")
//    private List<PostTest> postTests;

//    @OneToMany(targetEntity = Post.class,cascade = CascadeType.ALL)
//    @JoinColumn(name="up_fk", referencedColumnName = "auth_user_id")
//    private List<Post> posts;





}
