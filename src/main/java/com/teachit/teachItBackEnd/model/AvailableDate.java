package com.teachit.teachItBackEnd.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.List;

@JsonSerialize
@JsonDeserialize
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AvailableDate  implements Serializable {

    @Id
    private String date;
    @OneToMany
    @JoinColumn(name = "asAt_fk", referencedColumnName = "date")
    private List<AvailableTime> time;
    @ManyToOne
    private User user;
}
