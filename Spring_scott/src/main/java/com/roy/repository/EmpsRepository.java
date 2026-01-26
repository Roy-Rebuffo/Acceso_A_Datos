package com.roy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.roy.model.Emp;

public interface EmpsRepository extends JpaRepository<Emp, Integer>{

}
