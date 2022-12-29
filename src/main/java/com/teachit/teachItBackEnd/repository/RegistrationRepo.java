package com.teachit.teachItBackEnd.repository;

import com.teachit.teachItBackEnd.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//hold all basic methods for database communication
//inbuilt methods from JPArepository
@Repository
public interface RegistrationRepo extends JpaRepository<User, String> {

    /**** Find users by email ****/
    public User findByEmail(String email);

    /**** Find users by email & password ****/
    public User findByEmailAndPassword(String email,String Password);

}
