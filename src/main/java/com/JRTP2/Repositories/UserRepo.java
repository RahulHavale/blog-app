package com.JRTP2.Repositories;

import com.JRTP2.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity,Integer> {

    public UserEntity findByEmail(String email);
}
