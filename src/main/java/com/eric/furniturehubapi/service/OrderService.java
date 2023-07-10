package com.eric.furniturehubapi.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.eric.furniturehubapi.model.Order;
import com.eric.furniturehubapi.model.Product;
import com.eric.furniturehubapi.model.User;
import com.eric.furniturehubapi.repository.OrderRepository;
import com.eric.furniturehubapi.repository.ProductRepository;
import com.eric.furniturehubapi.repository.UserRepository;

@Service
public class OrderService {
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	ProductService productservice;
	
	public Order createOrder(Order order){
		System.out.println(order.toString());
		order.setId(null);
		order.setPurchasedTime(LocalDateTime.now());
		
		//Get Current User
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(userDetails.getUsername());
		Optional<User > userOpt = userRepo.findByName(userDetails.getUsername());
		User currentUser = userOpt.get();
		order.setUser(currentUser);
		System.out.println("product id is >>>>> " + order.getProduct().getId());
		//Validate Product
		Optional<Product > productOpt = productRepo.findById(order.getProduct().getId());
		
		if(productOpt.isEmpty()) {
			System.out.println("Proudct Not foud or Insufficient Stock");
			return null; // return Exception
		}
		
		//Update Product Quantity
		Product updateProduct = productOpt.get();
		boolean isPurchased = productservice.udpateProduct(updateProduct.getId(), order.getQuantity());
		if(!isPurchased) {
			System.out.println("Order Transaction failed");
			return null;
		}
		Order createdOrder = orderRepository.save(order);
		
		return createdOrder;
	
	}
	
	
	public List<Order> getUserOrders() {
		System.out.print("Getting all orders from >>>> ");
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(userDetails.getUsername());
		Optional<User > userOpt = userRepo.findByName(userDetails.getUsername());
		
		int id = userOpt.get().getId();
		System.out.println( "id " +id);
		User user = new User();
		user.setId(id);
		
		List<Order> orderList = orderRepository.findAll();
		List<Order> filteredList = orderList.stream().filter(o -> o.getUser() != null && (int) o.getUser().getId() == id).collect(Collectors.toList());
		filteredList.forEach(System.out::print);
		return filteredList;
	}
	
	
	public List<Order> getOrders() {
		System.out.print("Getting all orders");
		return orderRepository.findAll();
	}

}
