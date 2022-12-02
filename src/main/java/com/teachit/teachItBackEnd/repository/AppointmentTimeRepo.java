package com.teachit.teachItBackEnd.repository;

import com.teachit.teachItBackEnd.model.AppointmentTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentTimeRepo extends JpaRepository<AppointmentTime, Long> {

    AppointmentTime findByTime(String time);

}
