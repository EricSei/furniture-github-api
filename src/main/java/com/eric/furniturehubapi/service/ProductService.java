package com.eric.furniturehubapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eric.furniturehubapi.model.Product;
import com.eric.furniturehubapi.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepo;
	
	boolean udpateProduct(int productId, int purchasedQuantity){
		
		Optional<Product> opt = productRepo.findById(productId);
		if( opt.isEmpty() || opt.get().getStockQuantity() < purchasedQuantity ) {
			System.out.println("Proudct Not foud or Insufficient Stock");
			return false;
		}
		
		Product product = opt.get();
		System.out.println("remaining stock >>>> " + product.getStockQuantity());
		product.setStockQuantity( product.getStockQuantity()  - purchasedQuantity );
		
		productRepo.save(product);
		return true;
	}

}
