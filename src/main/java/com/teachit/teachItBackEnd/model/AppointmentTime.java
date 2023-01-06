package com.teachit.teachItBackEnd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AppointmentTime {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String time;
    @JsonIgnore
    @ManyToMany(mappedBy = "appointmentTimes")
    private List<AppointmentDate> appointmentDates;

    public List<AppointmentDate> getAppointmentDates() {

        return appointmentDates;
    }

    public void setAppointmentDates(List<AppointmentDate> appointmentDates) {

        this.appointmentDates = appointmentDates;
    }


}