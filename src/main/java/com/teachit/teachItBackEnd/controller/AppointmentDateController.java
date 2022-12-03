package com.teachit.teachItBackEnd.controller;

import com.teachit.teachItBackEnd.model.*;
import com.teachit.teachItBackEnd.repository.AppointmentDateRepo;
import com.teachit.teachItBackEnd.repository.RegistrationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
//        AppointmentDate requestedDate = appointmentDateRepo.findByUsersEmailAndDate(email, date);
//        System.out.println("In get appointment date method");
//        System.out.println("Printing out date" + date);
//        User user = registrationRepo.findById(email).get();
//        System.out.println("Printing out email" + email);
//        System.out.println("In get appointment requestedDate" + requestedDate);
//        if(requestedDate == null){
//            throw new Exception("The user does not have any availability for this date");
//        }

        System.out.println("In get appointment date method");
        System.out.println("Printing out date" + date);
        System.out.println("Printing out email" + email);

        User user = registrationRepo.findById(email).get();
        System.out.println("Past finding user");
        System.out.println("Printing out user object email" + user.getEmail());

        AppointmentDate appointmentDate = appointmentDateRepo.findByDate(date);

        if(appointmentDate == null){
            throw new Exception("Your chosen date, " + date + " is not availavle");
        }
        System.out.println("Printing out date object" + appointmentDate.getDate());

//        if(!user.appointmentDates.contains(appointmentDate)){
//            throw new Exception("Your chosen date, " + appointmentDate + " is not availavle");
//        }


        return appointmentDateRepo.findByUsersEmailAndDate(user.getEmail(), date);
    }

    @GetMapping("{email}/dates")
    public List<AppointmentDate> getDatesByUser(@PathVariable String email){
       if(!registrationRepo.existsById(email)){
           System.out.println("not found");
       }
       List<AppointmentDate> appointmentDatesList=appointmentDateRepo.findAllByUsersEmail(email);


        return appointmentDateRepo.findAll();

    }

    // ENDPOINT TO ALLOW FOR A DATE TO BE ADDED //
    @PostMapping
    public AppointmentDate createAppDate(@RequestBody AppointmentDate app) throws Exception {
        String requestedDate = app.getDate();
        AppointmentDate requestedDateObj = appointmentDateRepo.findByDate(requestedDate);
        System.out.println("In create app date");
        System.out.println("THIS IS THE REQUESTED DATE" + requestedDate);
        if(requestedDateObj!=null){
            System.out.println("date already registered" + requestedDate);
            throw new Exception("Your chosen date, " + requestedDate + " is already registered");

        }

        AppointmentDate createdapp = appointmentDateRepo.save(app);

        System.out.println("here is created app " + createdapp);
        return appointmentDateRepo.save(createdapp);
    }

    // ENDPOINT TO INJECT THE DATE INTO A USER OBJECT //
    @PutMapping("/{userEmail}/dates/{date}")
    User addDateToUser(
            @PathVariable String userEmail,  @PathVariable String date) throws Exception {

        User user = registrationRepo.findById(userEmail).get();
        System.out.println("Printing email:" + userEmail);
        System.out.println("Printing email from user:" + user.getEmail());
        AppointmentDate appointmentDate = appointmentDateRepo.findByDate(date);
        System.out.println("Printing date:" + date);
//        System.out.println("Printing sppointmentdate:" + appointmentDate);
//        System.out.println("Printing sppointmentdate:" + appointmentDate.getId());
        //REMOVE IF NOT WORKING
        if(user.appointmentDates.contains(appointmentDate)){
            throw new Exception("Your chosen date, " + appointmentDate + " has already been added to your account");
        }

        //
        user.appointmentDates.add(appointmentDate);
        return registrationRepo.save(user);
    }

}
