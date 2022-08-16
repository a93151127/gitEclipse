package com.martin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.martin.dao.User;

public interface UserRepository extends JpaRepository<User,Long>{

}
