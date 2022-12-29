package com.teachit.teachItBackEnd.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long appId;
    private String date;
    private String studentEmail;
    private String time;
    @ManyToOne
    private User user;


}
