package com.springboot.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.controller.CustomerDTO;
import com.springboot.model.SimpleTable;

@Repository
public interface CassandraRepository extends CrudRepository<CustomerDTO, Serializable> {
	
//	Optional<CustomerDTO> findByIdAndName(String orgguid);

}