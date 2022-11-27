package com.teachit.teachItBackEnd.repository;

import com.teachit.teachItBackEnd.model.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvaiableSlotRepo extends JpaRepository<AvailableDate, Long> {

    //find by date,time and user
}
