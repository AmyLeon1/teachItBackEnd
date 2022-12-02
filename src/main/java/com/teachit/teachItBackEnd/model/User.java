package com.teachit.teachItBackEnd.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements Serializable {

    @Id
    private String email;
    private String username;
    private String password;
    private String role;

    @OneToMany
    @JoinColumn(name = "ua_fk", referencedColumnName = "email")
    private List<Appointment> appointments;

    @ManyToMany
    @JoinTable(
            name="appointment_date",
            joinColumns = @JoinColumn(name="user_email"),
            inverseJoinColumns = @JoinColumn(name="id")

    )
    public List<AppointmentDate> appointmentDates = new ArrayList<>();

    public List<AppointmentDate> getAppointmentDates(){

        return appointmentDates;
    }


    //remove from here id it starts going wrong



    //one user many posts
    //mapped by the user variable in the post entity
//    @OneToMany(mappedBy = "user")
//    private List<PostTest> postTests;

//    @OneToMany(targetEntity = Post.class,cascade = CascadeType.ALL)
//    @JoinColumn(name="up_fk", referencedColumnName = "auth_user_id")
//    private List<Post> posts;





}
