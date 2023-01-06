package com.teachit.teachItBackEnd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/* This class holds the appointment dates which can be added to teachers schedules*/
@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AppointmentDate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String date;
    @JsonIgnore
    @ManyToMany(mappedBy = "appointmentDates")
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @ManyToMany
    @JoinTable(
            name = "appointment_time",
            joinColumns = @JoinColumn(name = "date_id"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    public List<AppointmentTime> appointmentTimes = new ArrayList<>();

    public List<AppointmentTime> getAppointmentTimes() {

        return appointmentTimes;
    }

}
