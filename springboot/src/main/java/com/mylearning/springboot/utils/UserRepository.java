package com.mylearning.springboot.utils;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mylearning.springboot.bo.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
