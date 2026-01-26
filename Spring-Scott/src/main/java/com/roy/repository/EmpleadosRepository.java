package com.roy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.roy.model.Emp;

public interface EmpleadosRepository extends JpaRepository<Emp, Integer> {

}
