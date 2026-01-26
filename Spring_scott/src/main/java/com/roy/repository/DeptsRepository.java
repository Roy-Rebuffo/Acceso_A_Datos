package com.roy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.roy.model.Dept;

public interface DeptsRepository extends JpaRepository<Dept, Integer>{

}
