package com.eric.furniturehubapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eric.furniturehubapi.model.Order;

@Repository
public interface OrderRepository extends JpaRepository< Order , Integer >{
	
	
	Optional<Order> findById(Integer id);

}