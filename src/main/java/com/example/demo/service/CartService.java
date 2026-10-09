package com.example.demo.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.example.demo.model.Producto;

import org.springframework.beans.factory.annotation.Autowired;

//Servicio de Carrito de compras

@Service
public class CartService {
	
	private List<Long> products = new ArrayList<>();
	@Autowired
	private ProductService serviceProduct; 
	
	
	public List<Producto> getAllCartItems(){
		return serviceProduct.getAllProducts();
	}
	
	public void addProductToCart(Long id) {

		if(serviceProduct.getProductById(id) != null){
			products.add(id);
		}

	}
	
	public void deleteProductFromCart(Long id) {
		products.remove(id);
	}

}
