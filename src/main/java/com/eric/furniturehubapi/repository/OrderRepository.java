package com.eric.furniturehubapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eric.furniturehubapi.model.Order;
import com.eric.furniturehubapi.model.User;

@Repository
public interface OrderRepository extends JpaRepository< Order , Integer >{
	
	
	Optional<Order> findById(Integer id);

//	@Query("SELECT o from Order o where o.user_id =?1")
//	List<Order> findByUser(int id);
	


}
