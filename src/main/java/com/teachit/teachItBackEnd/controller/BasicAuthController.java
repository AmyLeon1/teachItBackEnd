package com.teachit.teachItBackEnd.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


//***** Remove this class if others are failing ****

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class BasicAuthController {

    @GetMapping(path="/basicauth")
    public AuthenticationBean authenticationBean(){
        return new AuthenticationBean("You are authenticared");
    }

}
