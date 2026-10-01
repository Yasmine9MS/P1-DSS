package com.example.demo;
import java.util.List;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductService {
	
	private final ProductRepo productRepo;
	

	public List<Producto> getAllProducts(){
		return productRepo.findAll();
	}
	
	public Producto getProductById(Long id){
		return productRepo.findById(id).orElse(null);
	}

	
	public void saveProduct(Producto product){
		productRepo.save(product);
		
	}
	
	public void deleteProduct(Long id){
		productRepo.deleteById(id);
	}

}
