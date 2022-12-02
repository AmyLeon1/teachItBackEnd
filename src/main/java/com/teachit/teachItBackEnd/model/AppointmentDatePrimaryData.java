package com.teachit.teachItBackEnd.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Embeddable
public class AppointmentDatePrimaryData implements Serializable {

    private String date;
    @ManyToOne
    private User user;
}
