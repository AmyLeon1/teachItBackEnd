package com.teachit.teachItBackEnd.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.ManyToOne;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AvailableTime {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long Id;
    private String time;

    @ManyToOne
    private AvailableDate availableDate;

    @ManyToOne
    private User user;
}
