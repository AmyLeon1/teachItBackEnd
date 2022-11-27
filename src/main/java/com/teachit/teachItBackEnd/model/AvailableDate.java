package com.teachit.teachItBackEnd.model;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.List;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AvailableDate {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String date;
    @OneToMany
    @JoinColumn(name = "asAt_fk", referencedColumnName = "id")
    private List<AvailableTime> time;
    @ManyToOne
    private User user;
}
