package com.teachit.teachItBackEnd.model;

import lombok.*;

import javax.persistence.*;
import java.awt.*;
import java.awt.image.BufferedImage;
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
    private byte picByte;
    private String aboutMe;

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

}
