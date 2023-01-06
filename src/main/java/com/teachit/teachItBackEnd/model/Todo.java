package com.teachit.teachItBackEnd.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Todo {

    @Id
    @GeneratedValue()
    private Long id;
    private String email;
    private String description;
    private Date targetDate;

}
