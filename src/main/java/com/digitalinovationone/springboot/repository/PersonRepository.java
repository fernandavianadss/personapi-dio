package com.digitalinovationone.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digitalinovationone.springboot.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

}
