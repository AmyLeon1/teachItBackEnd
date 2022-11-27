package com.teachit.teachItBackEnd.controller;

import com.teachit.teachItBackEnd.model.AvailableDate;
import com.teachit.teachItBackEnd.model.AvailableTime;
import com.teachit.teachItBackEnd.model.User;
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


    // Method to create and save an available date into the database //
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/users/{user}/availableDates")
    public ResponseEntity<Void> createAvailableDate(
            @PathVariable User user, @RequestBody AvailableDate availableDate
    ){
        availableDate.setUser(user);
        AvailableDate createdAvailableDate = avaiableSlotRepo.save(availableDate);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(createdAvailableDate.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }


    // Method to save available time into the database with one at a time input //
    @PostMapping("/users/{availableDate}/availableTimes")
    public ResponseEntity<Void> createAvailableTime(
            @PathVariable AvailableDate availableDate, @RequestBody AvailableTime availableTime
    ){
        availableTime.setAvailableDate(availableDate);
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

}
