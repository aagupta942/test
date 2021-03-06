package com.mylearning.springboot.utils;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mylearning.springboot.bo.Posts;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Integer>{

}
