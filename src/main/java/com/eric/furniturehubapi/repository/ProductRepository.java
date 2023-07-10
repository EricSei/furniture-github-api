package com.eric.furniturehubapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eric.furniturehubapi.model.Product;
import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer > {
	
	 Optional<Product> findById(Integer id);

}
