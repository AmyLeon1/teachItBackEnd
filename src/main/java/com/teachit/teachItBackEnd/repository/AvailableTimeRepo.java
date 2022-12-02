package com.teachit.teachItBackEnd.repository;

import com.teachit.teachItBackEnd.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvailableTimeRepo extends JpaRepository<AvailableTime, String> {

    AppointmentTime findByTime(String time);
List<AvailableTime> findByAvailableDate(AvailableDate availableDate);
List <AvailableTime> findByUserAndAvailableDate(User user, AvailableDate availableDate);

}
