package com.teachit.teachItBackEnd.controller;

import com.teachit.teachItBackEnd.model.AppointmentDate;
import com.teachit.teachItBackEnd.model.AppointmentTime;
import com.teachit.teachItBackEnd.model.User;
import com.teachit.teachItBackEnd.repository.AppointmentDateRepo;
import com.teachit.teachItBackEnd.repository.AppointmentTimeRepo;
import com.teachit.teachItBackEnd.repository.RegistrationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/appointmentTimes")
public class AppointmentTimeController {

    @Autowired
    AppointmentTimeRepo appointmentTimeRepo;

    @Autowired
    AppointmentDateRepo appointmentDateRepo;

    @Autowired
    RegistrationRepo registrationRepo;


    @GetMapping("{id}/times/id")
    public List<AppointmentTime> getTimeByDateId(@PathVariable Long id) {

        System.out.println("**** IN METHOD TO FIND TIMES BY ID ***********");
        List<AppointmentTime> appointmentTimesList = appointmentTimeRepo.findByAppointmentDatesId(id);

        return appointmentTimeRepo.findByAppointmentDatesId(id);
    }


    /* Endpoint to add available time to date */
    @PutMapping("{email}/{date}/dates/times/{time}")
    AppointmentDate addTimeToDateOfUser(
            @PathVariable String email, @PathVariable String date, @PathVariable String time) throws Exception {

        //find user by the email passed in
        User user = registrationRepo.findById(email).get();

        //check the database for an appointmentDate obj with requested user and date
        AppointmentDate appDate = appointmentDateRepo.findByUsersAndDate(user, date);

        //fetch time from database and sore in appointmentTime object
        AppointmentTime appointmentTime = appointmentTimeRepo.findByTime(time);

        //if this appdate object already contains this time - ie if this tine has already been added
        //throw an exception
        if (appDate.appointmentTimes.contains(appointmentTime)) {
            throw new Exception("Your chosen time, " + appointmentTime.getTime() + " has already been added to your account");
        }
        //if not, add the time
        appDate.appointmentTimes.add(appointmentTime);
        return appointmentDateRepo.save(appDate);
    }


    /* Endpoint to add a new time to the system */
    @PostMapping
    public AppointmentTime createTime(@RequestBody AppointmentTime appTime) {
        AppointmentTime createdTime = appointmentTimeRepo.save(appTime);

        return appointmentTimeRepo.save(createdTime);
    }

}
