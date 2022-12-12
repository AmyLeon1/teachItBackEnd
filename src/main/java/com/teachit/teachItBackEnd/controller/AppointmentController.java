package com.teachit.teachItBackEnd.controller;

import com.teachit.teachItBackEnd.model.*;
import com.teachit.teachItBackEnd.repository.AppointmentRepo;
import com.teachit.teachItBackEnd.repository.RegistrationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AppointmentController {

    @Autowired
    private AppointmentRepo appointmentRepo;

    @Autowired
    private RegistrationRepo registrationRepo;


//    @PostMapping("/users/{user}/appointments")
//    @PostMapping("/users/{user}/appointments")
//    public ResponseEntity<Void> createAppointment(
//           @PathVariable User user,
//            @RequestBody Appointment appointment
//    ){
//        appointment.setUser(user);
//
//        Appointment createdAppointment = appointmentRepo.save(appointment);
//
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{appId}").buildAndExpand(createdAppointment.getAppId()).toUri();
//
//        return ResponseEntity.created(uri).build();
//
//    }

    @GetMapping("/users/{user}/appointments")
    public List<Appointment> getAllAppointments(@PathVariable User user){
        return appointmentRepo.findByUser(user);
        //return todoService.findAll();
    }

    //find by studentEmail
    //take email path variable and pass it in to the findByStudentEmail repo method
    @GetMapping("/users/{studentEmail}/appointments/studentEmail")
    public List<Appointment> getAllAppointmentsByStudentEmail(@PathVariable String studentEmail){
        return appointmentRepo.findByStudentEmail(studentEmail);
        //return todoService.findAll();
    }


//    @GetMapping("/users/{user}/appointments")
//    public List<Appointment> getAllAppointments(@PathVariable User user){
//        return appointmentRepo.findByUser(user);
//        //return todoService.findAll();
//    }
//
//    @PostMapping(path= "/users/{user}/appointments")
//    public Appointment registerAppointment(@PathVariable User user,
//                                    @RequestBody Appointment appointment) throws Exception {
//        appointment.setUser(user);
//
//        //check if email already exists in db
//        String tempDate = appointment.getDate();
//        String tempUserEmail = appointment.getUser().getEmail();
//
////        if(tempDate != null && !equals(tempDate)){
//        if(tempDate != null && !equals(tempDate)){
//
//            Appointment appObj = appointmentRepo.findByDate(tempDate);
//
//            String currentUser  = appObj.getUser().getEmail();
//            String currentDate = appObj.getDate();
//            if(currentUser !=null || currentDate!=null){
//
//           // if(appObj != null){
//                throw new Exception("Appointment with " + appObj + " is already registered");
//            }
//        }
//
//        Appointment appObj;// = null;
//        //pass in user to the registration service save method
//        appObj = appointmentRepo.save(appointment);
//        return appObj;
//
//
//    }




//
//    @PostMapping(path= "/users/{user}/appointments")
//    public ResponseEntity<Void> registerAppointment(@PathVariable User user,
//                                                      @RequestBody Appointment appointment) throws Exception {
//        appointment.setUser(user);
//
//        //check if email already exists in db
//        String requestedDate = appointment.getDate();
//        System.out.println("Here is the requested date" + requestedDate);
//        User tempUser = appointment.getUser();
//
////        if(tempDate != null && !equals(tempDate)){
//        if(tempUser != null && !equals(tempUser)){
//
//            Appointment appObj = appointmentRepo.findAppByUser(tempUser);
//             String currentDate = appObj.getDate();
//
//             System.out.println("Here is the current date" + currentDate);
//
//
////            String currentUser  = appObj.getUser().getEmail();
////            String currentDate = appObj.getDate();
//            if(requestedDate.equals(currentDate)){
//
//                // if(appObj != null){
//                throw new Exception("Appointment with " + appObj + " is already registered");
//            }
//        }
//
//        Appointment appObj;// = null;
//        //pass in user to the registration service save method
//        appObj = appointmentRepo.save(appointment);
//
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{appId}").buildAndExpand(appObj.getAppId()).toUri();
//
//        return ResponseEntity.created(uri).build();
//
//
////        return appObj;
//
//
//    }



    @PostMapping(path= "/users/{user}/appointments2")
    public ResponseEntity<Void> registerAppointment(@PathVariable User user,
                                                    @RequestBody Appointment appointment) throws Exception {
        //set the user
        appointment.setUser(user);

        ///assign string with required date
        String requestedDate = appointment.getDate();
        System.out.println("Here is the requested date" + requestedDate);

        //assign user object with requested user(teacher)
        User requestedUser = appointment.getUser();
        System.out.println("Here is the requested user" + requestedUser);

        //if request user and requested date are not null enter if block
        if(requestedUser != null && !equals(requestedDate)){

            //look for current appointment with requestedUser and requestedDate
            Appointment appObj = appointmentRepo.findByUserAndDate(requestedUser, requestedDate);


            System.out.println("We have passed findByUserAndDate");

            //if an appointment is found with this criteria the throw exception
            if(appObj!=null){

                // if(appObj != null){
                throw new Exception("Appointment with " + appObj + " is already registered");
            }
        }
        //otherwise save the new appointment into the database via the repository
        Appointment appObj;
        appObj = appointmentRepo.save(appointment);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{appId}").buildAndExpand(appObj.getAppId()).toUri();

        return ResponseEntity.created(uri).build();

    }


    // TESTING WITH NEW DATES
    @PostMapping(path= "/users/{user}/appointments")
    public ResponseEntity<Void> registerAppointment1(@PathVariable User user,
                                                    @RequestBody Appointment appointment) throws Exception {
        //set the user
        appointment.setUser(user);

        ///assign string with required date
        String requestedDate = appointment.getDate();
        System.out.println("Here is the requested date" + requestedDate);

        //assign user object with requested user(teacher)
        User requestedUser = appointment.getUser();
        System.out.println("Here is the requested user" + requestedUser);

        //if request user and requested date are not null enter if block
        if(requestedUser != null && !equals(requestedDate)){

            //look for current appointment with requestedUser and requestedDate
            Appointment appObj = appointmentRepo.findByUserAndDate(requestedUser, requestedDate);


            System.out.println("We have passed findByUserAndDate");

            //if an appointment is found with this criteria the throw exception
            if(appObj!=null){

                // if(appObj != null){
                throw new Exception("Appointment with " + appObj + " is already registered");
            }
        }
        //otherwise save the new appointment into the database via the repository
        Appointment appObj;
        appObj = appointmentRepo.save(appointment);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{appId}").buildAndExpand(appObj.getAppId()).toUri();

        return ResponseEntity.created(uri).build();

    }







    @PostMapping(path= "/users/{user}/appointments3")
    public ResponseEntity<Void> registerAppointment3(@PathVariable User user,
                                                     @RequestBody Appointment appointment) throws Exception {
        System.out.println("*****In set apps 3*******");
        //set the user
        appointment.setUser(user);

        ///assign string with required date
        String requestedDate = appointment.getDate();
        System.out.println("Here is the requested date" + requestedDate);

        //assign user object with requested user(teacher)
        User requestedUser = appointment.getUser();
        System.out.println("Here is the requested user" + requestedUser);

        //if request user and requested date are not null enter if block
        if(requestedUser != null && !equals(requestedDate)){

            //look for current appointment with requestedUser and requestedDate
            Appointment appObj = appointmentRepo.findByUserAndDate(requestedUser, requestedDate);


            System.out.println("We have passed findByUserAndDate");

            //if an appointment is found with this criteria the throw exception
            if(appObj!=null){

                // if(appObj != null){
                throw new Exception("Appointment with " + appObj + " is already registered");
            }
        }
        //otherwise save the new appointment into the database via the repository
        Appointment appObj;
        appObj = appointmentRepo.save(appointment);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{appId}").buildAndExpand(appObj.getAppId()).toUri();

        return ResponseEntity.created(uri).build();

    }



    @PostMapping(path= "/users/{email}/appointments4")
    public ResponseEntity<Void> registerAppointment4(@PathVariable String email,
                                                     @RequestBody Appointment appointment) throws Exception {
        System.out.println("*****In set apps 3*******");
        //set the user
        User user = registrationRepo.findById(email).get();
        appointment.setUser(user);
       // System.out.println("Printing out user" +user);
       // System.out.println("Printing out user email" +user.getEmail());

        ///assign string with required date
        String requestedDate = appointment.getDate();
        System.out.println("Here is the requested date" + requestedDate);

       ;

        //if request user and requested date are not null enter if block
        if(user != null && !equals(requestedDate)){

            //look for current appointment with requestedUser and requestedDate
            Appointment appObj = appointmentRepo.findByUserAndDate(user, requestedDate);


            System.out.println("We have passed findByUserAndDate");

            //if an appointment is found with this criteria the throw exception
            if(appObj!=null){

                // if(appObj != null){
                throw new Exception("Appointment with " + appObj + " is already registered");
            }
        }
        //otherwise save the new appointment into the database via the repository
        Appointment appObj;
        appObj = appointmentRepo.save(appointment);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{appId}").buildAndExpand(appObj.getAppId()).toUri();

        return ResponseEntity.created(uri).build();

    }



}

