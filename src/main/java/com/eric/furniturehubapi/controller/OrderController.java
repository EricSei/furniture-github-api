package com.eric.furniturehubapi.controller;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eric.furniturehubapi.model.Order;
import com.eric.furniturehubapi.service.OrderService;

@RestController
@RequestMapping("/api")
public class OrderController {
	

	@Autowired
	OrderService orderService;
	
	@PostMapping("/orders")
	public ResponseEntity<?> createOrder(@Valid @RequestBody Order order){
		
		Order newOrder=  orderService.createOrder(order);
		if(newOrder == null) {
			System.out.print("Product does not exist");
			return ResponseEntity.status(401).body("Product Does not exist");
		}
		return ResponseEntity.status(201).body(newOrder);
	
	}
	

	@GetMapping("/orders")
	public List<Order> getOrders() {
		System.out.print("Getting all orders");
		return orderService.getOrders();
	}
	
	@GetMapping("/orders/users")
	public List<Order> getOrdersByUser() {
		System.out.print("Getting all orders");
		return orderService.getUserOrders();
	}
}
