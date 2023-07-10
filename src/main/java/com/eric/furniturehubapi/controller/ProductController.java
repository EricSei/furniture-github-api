package com.eric.furniturehubapi.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eric.furniturehubapi.model.Product;
import com.eric.furniturehubapi.repository.ProductRepository;
import com.eric.furniturehubapi.service.ProductService;

import java.util.List;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api")
public class ProductController {
	@Autowired
	ProductRepository productRepo;
	
	
	
	@PostMapping("/products")
	public ResponseEntity<Product> createProduct(@RequestBody Product product){
		product.setId(null);
		Product created = productRepo.save(product);
		System.out.println(created.toString());
		return ResponseEntity.status(201).body(created);
		
	}
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getProducts(){
		
		return ResponseEntity.status(200).body(productRepo.findAll());
				
	}

}
