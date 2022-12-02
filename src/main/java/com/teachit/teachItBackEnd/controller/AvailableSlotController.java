package com.teachit.teachItBackEnd.controller;

import com.teachit.teachItBackEnd.model.*;
import com.teachit.teachItBackEnd.repository.AppointmentDateRepo;
import com.teachit.teachItBackEnd.repository.AvaiableSlotRepo;
import com.teachit.teachItBackEnd.repository.AvailableTimeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AvailableSlotController {

    //Autowire repositories so their methods can be availed of
    @Autowired
    AvaiableSlotRepo avaiableSlotRepo;
    @Autowired
    AvailableTimeRepo availableTimeRepo;

    @Autowired
    AppointmentDateRepo appointmentDateRepo;


    // Method to create and save an available date into the database //
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/users/{user}/availableDates")
    public ResponseEntity<Void> createAvailableDate(
            @PathVariable User user, @RequestBody AvailableDate availableDate
    ){
        availableDate.setUser(user);
        AvailableDate createdAvailableDate = avaiableSlotRepo.save(availableDate);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{date}").buildAndExpand(createdAvailableDate.getDate()).toUri();

        return ResponseEntity.created(uri).build();
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/users/{user}/availableDates1")
    public AvailableDate createAvailableDate1(
            @PathVariable User user, @RequestBody AvailableDate availableDate
    ){
        availableDate.setUser(user);
        AvailableDate createdAvailableDate = avaiableSlotRepo.save(availableDate);

        return createdAvailableDate;

    }


    // Method to save available time into the database with one at a time input //
    @PostMapping("/users/{user}/{availableDate}/availableTimes")
    public ResponseEntity<Void> createAvailableTime(
            @PathVariable AvailableDate availableDate,  @PathVariable User user,@RequestBody AvailableTime availableTime
    ){
        availableTime.setAvailableDate(availableDate);
        availableTime.setUser(user);
        AvailableTime createdAvailableTime = availableTimeRepo.save(availableTime);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(createdAvailableTime.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }


    // Method to save a list of times //
    // this will allow users to select more than one available time and send it in the post request
    @PostMapping("/users/{availableDate}/availableTimesList")
    public List<AvailableTime> createAvailableTimeList(
            @PathVariable AvailableDate availableDate, @RequestBody List<AvailableTime> availableTime
    ){
        //initialize currentAvailableTime as null
        AvailableTime currentAvailableTime = null;

        //iterate over the length of availableTime(list)
        for(int i=0; i < availableTime.size(); i++){

            //currentAvailableTime equals whatever iteration of the list we are one
           currentAvailableTime = availableTime.get(i);

           //set the available date
            currentAvailableTime.setAvailableDate(availableDate);

            //save currentAvailable time into the db via the repository
        AvailableTime createdAvailableTime = availableTimeRepo.save(currentAvailableTime);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(createdAvailableTime.getId()).toUri();}

        return availableTime;
    }

    @GetMapping("/users/{user}/availableDates/{date}")
    public AvailableDate getDateByDate(@PathVariable User user, @PathVariable String date){
        return avaiableSlotRepo.findByUserAndDate(user, date);
        //return todoService.findById(id);
    }


    //Get all available dates for a particular user
    @GetMapping("/users/{user}/availableDates")
    public List<AvailableDate> getAllAvailabeDates(@PathVariable User user){
        return avaiableSlotRepo.findByUser(user);

    }

//    @GetMapping("/users/{user}/{availableDate}/availableTimes")
//    public List<AvailableTime> getAllAvailabeTimes(@PathVariable User user,@PathVariable AvailableDate availableDate){
//        return availableTimeRepo.findByAvailableDate(availableDate);
//
//    }


     //Endpoint to allow retrieval of available times for a particular user at a particular time //
    @GetMapping("/users/{user}/availableDates/{availableDate}/availableTimes")
    public List<AvailableTime> getAvailableTimesForDate(@PathVariable User user, @PathVariable AvailableDate availableDate){
        return availableTimeRepo.findByUserAndAvailableDate(user, availableDate);
        //return todoService.findById(id);
    }


//    @CrossOrigin(origins = "http://localhost:4200")
//    @PostMapping("/users/{user}/availableDates2")
//    public ResponseEntity<Void> createAvailableDate2(
//            @PathVariable User user, @RequestBody AppointmentDate appointmentDate
//    ){
//        appointmentDate.setUser(user);
//        AppointmentDate createdAppointmentDate = appointmentDateRepo.save(appointmentDate);
//
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{date}").buildAndExpand(createdAppointmentDate.getDate()).toUri();
//
//        return ResponseEntity.created(uri).build();
//    }






}
