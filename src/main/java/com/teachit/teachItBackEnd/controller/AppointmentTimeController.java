package com.teachit.teachItBackEnd.controller;

import com.teachit.teachItBackEnd.model.AppointmentDate;
import com.teachit.teachItBackEnd.model.AppointmentTime;
import com.teachit.teachItBackEnd.model.User;
import com.teachit.teachItBackEnd.repository.AppointmentDateRepo;
import com.teachit.teachItBackEnd.repository.AppointmentTimeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/appointmentTimes")
public class AppointmentTimeController {

    @Autowired
    AppointmentTimeRepo appointmentTimeRepo;

    @Autowired
    AppointmentDateRepo appointmentDateRepo;

    // INSERT TIME INTO THE DATE //
    @PutMapping("/{date}/dates/times/{time}")
    AppointmentDate addTimeToDate(
            @PathVariable String date,  @PathVariable String time) {
        System.out.println("In add time to date method" + date);
        System.out.println("In add time to date method, time:" + date);
        System.out.println("In add time to date method, date " + time);
        AppointmentDate appDate = appointmentDateRepo.findByDate(date);
        System.out.println("Printing out date:" + date);

        AppointmentTime appointmentTime = appointmentTimeRepo.findByTime(time);
        System.out.println("Printing time:" + time);
        System.out.println("Printing appointmentTime:" + appointmentTime);
        appDate.appointmentTimes.add(appointmentTime);
        return appointmentDateRepo.save(appDate);
    }


    // ADD NEW TIME //
    @PostMapping
    public AppointmentTime createTime(@RequestBody AppointmentTime appTime){
        AppointmentTime createdTime = appointmentTimeRepo.save(appTime);

        return appointmentTimeRepo.save(createdTime);
    }
}
