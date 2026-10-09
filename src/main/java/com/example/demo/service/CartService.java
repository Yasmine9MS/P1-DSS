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
		List<Producto> productos = new ArrayList<>();
		for(Long id : products) {
			Producto prod = serviceProduct.getProductById(id);
			if(prod != null) {
				productos.add(prod);
			}
		}

		return productos;
	}
	
	//Método para agregar un producot al carrito de compra mientras que exista
	public void addProductToCart(Long id) {

		if(serviceProduct.getProductById(id) != null){
			products.add(id);
		}

	}
	
	//Método para borrar un producto del carrito de comrpas
	public void deleteProductFromCart(Long id) {
		products.remove(id);
	}

	//Método para mostrar el total del carrito 
	public double totalCart() {
    double total = 0;

    for (Long id : products) {
        Producto prod = serviceProduct.getProductById(id);

        if (prod != null) {
            total += prod.getPrecio();
        }
    }

    return total;
}
}
