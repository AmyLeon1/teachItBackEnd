package com.teachit.teachItBackEnd.controller;

import com.teachit.teachItBackEnd.model.*;
import com.teachit.teachItBackEnd.repository.AppointmentRepo;
import com.teachit.teachItBackEnd.repository.RegistrationRepo;
import com.teachit.teachItBackEnd.service.AppointmentService;
import com.teachit.teachItBackEnd.service.RegistrationService;
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
    private AppointmentService appointentService;

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private RegistrationRepo registrationRepo;

    // *** Endpoint for appointment cancellation/deletion ***//
    @DeleteMapping("/users/appointments/{id}")
    public ResponseEntity<Void> cancelAppointment(
            @PathVariable long id) {
        appointentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/users/{user}/appointments")
    public List<Appointment> getAllAppointments(@PathVariable User user) {
        return appointentService.findByUser(user);
    }

    // ****    Endpoint to retrieve all appointments for specific student    **** //
    @GetMapping("/users/{studentEmail}/appointments/studentEmail")
    public List<Appointment> getAllAppointmentsByStudentEmail(@PathVariable String studentEmail) {
        return appointentService.findByStudentEmail(studentEmail);
    }


    /* Endpoint for booking a new appointment */
    @PostMapping(path = "/users/{email}/appointments")
    public ResponseEntity<Void> registerAppointment(@PathVariable String email,
                                                    @RequestBody Appointment appointment) throws Exception {

        //set the user
//        User user = registrationRepo.findById(email).get();
        User user = registrationService.fetchUserByEmail(email);
        appointment.setUser(user);

        ///assign string with required date
        String requestedDate = appointment.getDate();

        //if request user and requested date are not null enter if block
        if (user != null && !equals(requestedDate)) {

            //look for current appointment with requestedUser and requestedDate
            Appointment appObj = appointentService.findByUserAndDate(user, requestedDate);

            //if an appointment is found with these details then throw exception
            if (appObj != null) {
                throw new Exception("Appointment with " + appObj + " is already registered");
            }
        }
        //otherwise save the new appointment into the database by sending it to the service
        //to be passed onto the repository
        Appointment appObj;
        appObj = appointentService.save(appointment);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{appId}").buildAndExpand(appObj.getAppId()).toUri();

        return ResponseEntity.created(uri).build();
    }


}

