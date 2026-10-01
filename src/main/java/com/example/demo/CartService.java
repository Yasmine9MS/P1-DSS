package com.example.demo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class CartService {
	
	private List<Producto> products = new ArrayList<>();
	
	
	public List<Producto> getAllCartItems(){
		return products;
	}
	
	public void addProductToCart(Producto product) {
		products.add(product);
	}
	
	public void deleteProductFromCart(Producto product) {
		products.remove(product);
	}
	
	

}
