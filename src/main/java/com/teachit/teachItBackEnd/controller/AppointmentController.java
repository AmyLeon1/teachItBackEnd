package com.teachit.teachItBackEnd.controller;

import com.teachit.teachItBackEnd.model.Appointment;
import com.teachit.teachItBackEnd.model.Blog;
import com.teachit.teachItBackEnd.repository.AppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AppointmentController {

    @Autowired
    private AppointmentRepo appointmentRepo;


    @PostMapping("/users/{email}/appointments")
    public ResponseEntity<Void> createAppointment(
            @PathVariable String email, @RequestBody Appointment appointment
    ){
        appointment.setEmail(email);
        Appointment createdAppointment = appointmentRepo.save(appointment);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(createdAppointment.getAppId()).toUri();

        return ResponseEntity.created(uri).build();


    }
}

