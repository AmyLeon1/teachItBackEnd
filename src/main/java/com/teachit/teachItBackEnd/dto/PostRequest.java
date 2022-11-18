package com.teachit.teachItBackEnd.dto;

import com.teachit.teachItBackEnd.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostRequest {

    private User user;


}
