package com.teachit.teachItBackEnd.controller;

import com.teachit.teachItBackEnd.model.*;
import com.teachit.teachItBackEnd.repository.AppointmentDateRepo;
import com.teachit.teachItBackEnd.repository.RegistrationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/* This file holds the endpoints for teachers to add available dates
 to their schedules */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/appointmentDates")
public class AppointmentDateController {

    @Autowired
    AppointmentDateRepo appointmentDateRepo;

    @Autowired
    RegistrationRepo registrationRepo;

    @GetMapping("/user/{email}/dates/{date}")
    public AppointmentDate getDateByEmailAndDate(@PathVariable String email, @PathVariable String date) throws Exception {

        User user = registrationRepo.findById(email).get();
        AppointmentDate appointmentDate = appointmentDateRepo.findByDate(date);

        if (appointmentDate == null) {
            throw new Exception("Your chosen date, " + date + " is not availavle");
        }
        System.out.println("Printing out date object" + appointmentDate.getDate());
        return appointmentDateRepo.findByUsersEmailAndDate(user.getEmail(), date);
    }

    /* Endpoint to retrieve all dates for a particular user */
    @GetMapping("{email}/dates")
    public List<AppointmentDate> getDatesByUser(@PathVariable String email) {
        if (!registrationRepo.existsById(email)) {
            System.out.println("not found");
        }
        List<AppointmentDate> appointmentDatesList = appointmentDateRepo.findAllByUsersEmail(email);

        return appointmentDateRepo.findAll();
    }

    /* Endpoint to add a new date  */
    @PostMapping
    public AppointmentDate createAppDate(@RequestBody AppointmentDate app) throws Exception {
        //retrieve the date passed in with the date object
        String requestedDate = app.getDate();
        //check to see if this date is already in the database
        AppointmentDate requestedDateObj = appointmentDateRepo.findByDate(requestedDate);

        //if date is already in the database throw this exception
        if (requestedDateObj != null) {
            System.out.println("date already registered" + requestedDate);
            throw new Exception("Your chosen date, " + requestedDate + " is already registered");
        }
        //otherwise save the date into the database
        AppointmentDate createdapp = appointmentDateRepo.save(app);
        return appointmentDateRepo.save(createdapp);
    }


    /* Endpoint to add a date into a user object */
    @PutMapping("/{userEmail}/dates/{date}")
    User addDateToUser(
            @PathVariable String userEmail, @PathVariable String date) throws Exception {

        //find the user in the database by searching with email that was supplied as a path variable
        User user = registrationRepo.findById(userEmail).get();

        //search the database for date obj with the supplied date string
        AppointmentDate appointmentDate = appointmentDateRepo.findByDate(date);

        //if this user has already added this date, throw an exception
        if (user.appointmentDates.contains(appointmentDate)) {
            throw new Exception("Your chosen date, " + appointmentDate + " has already been added to your account");
        }
        //otherwise add it to their appointmentDates[]
        user.appointmentDates.add(appointmentDate);
        return registrationRepo.save(user);
    }

}
