package com.spring.repository2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.model2.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>{

}
