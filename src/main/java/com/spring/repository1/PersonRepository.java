package com.spring.repository1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.model1.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>{

}
