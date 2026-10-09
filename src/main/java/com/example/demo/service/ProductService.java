package com.example.demo.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Producto;
import com.example.demo.repository.ProductRepo;

import org.springframework.beans.factory.annotation.Autowired;

//Servicio de productos que contiene la lógica de negocio relacionado con la gestión de productos
@Service
public class ProductService {
	
	@Autowired //Inyección de dependencias para el repositorio de productos
	private ProductRepo productRepo;
	
	//Obtiene todos los productos de la base de datos 
	public List<Producto> getAllProducts(){
		return productRepo.findAll();
	}
	
	//Obtiene un producto a partir de su identificador, si no se encuentra devuelve null
	public Producto getProductById(Long id){
		return productRepo.findById(id).orElse(null);
	}

	//Guarda un producto en la base de datos
	public void saveProduct(Producto product){
		productRepo.save(product);
		
	}
	
	//Elimina un producto de la base de datos
	public void deleteProduct(Long id){
		productRepo.deleteById(id);
	}

	//Busca productos por nombre
	public List<Producto> searchProduct(String consulta){
		return productRepo.findByNombreContainingIgnoreCase(consulta);
	}

	//Busca productos cuyo precio esté entre un rango mínimo y máximo
	public List<Producto> findByPrecioBetween(double minimo, double maximo){
		return productRepo.findByPrecioBetween(minimo, maximo);
	}

}
