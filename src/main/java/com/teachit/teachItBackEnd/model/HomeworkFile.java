package com.teachit.teachItBackEnd.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class HomeworkFile {

    @Id
    private String id;

    private String name;

    private String type;

    //To say file will be large
    @Lob
    private byte[] data;


}
