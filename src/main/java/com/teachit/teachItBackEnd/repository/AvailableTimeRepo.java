package com.teachit.teachItBackEnd.repository;

import com.teachit.teachItBackEnd.model.AvailableTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvailableTimeRepo extends JpaRepository<AvailableTime, Long> {

}
