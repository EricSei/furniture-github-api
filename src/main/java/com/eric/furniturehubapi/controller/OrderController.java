package com.eric.furniturehubapi.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eric.furniturehubapi.model.Order;
import com.eric.furniturehubapi.repository.OrderRepository;

@RestController
@RequestMapping("/api")
public class OrderController {
	
	@Autowired
	OrderRepository orderRepository;
	
	@PostMapping("/orders")
	public ResponseEntity<Order> createOrder(@RequestBody Order order){
		System.out.println(order.toString());
		order.setId(null);
		order.setPurchasedTime(LocalDateTime.now());
//		User user = new User();
//		user.setId(order.userId)
//		order.setUser()
		
		Order createdOrder = orderRepository.save(order);
		
		return ResponseEntity.status(201).body(createdOrder);
	
	}
	
	
	
	@GetMapping("/orders")
	public List<Order> getOrders() {
		System.out.print("Getting all orders");
		return orderRepository.findAll();
	}
}
