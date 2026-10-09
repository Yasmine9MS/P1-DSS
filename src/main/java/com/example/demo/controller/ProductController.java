package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Producto;
import com.example.demo.service.ProductService;

//Controlador para manejar peticiones relacionadas con productos
@Controller
@RequestMapping("/products")
public class ProductController {
	
	//Inyección de dependencias para el servicio de productos
	@Autowired
	private ProductService serviceProduct;
	
	//Obtiene todos los productos y los agrega al modelo para enviarlos a la vista
	@GetMapping
	public String getProducts(Model model) {
		model.addAttribute("products", serviceProduct.getAllProducts());
		return "products";
	}

	//Método para abrir el formulario de adición de un nuevo producto
	@GetMapping("/add")
	public String addProductForm(Model model){
		model.addAttribute("product", new Producto());
		return "product-form";
	}

	//Método para guardar un producto nuevo
	@PostMapping("/save")
	public String saveProduct(@RequestParam(required=false) Long id, @RequestParam String name, @RequestParam Double price) {
		Producto prod;
		if(id == null) { 
			//Comprueba que el id es nulo,  si es nulo se crea un nuevo producto, si el id no es nulo se obtienen los datos del producto existente para su edición
			prod = new Producto();
		} else {
			prod = serviceProduct.getProductById(id);
		}
		
		if(prod != null) {
			prod.setNombre(name);
			prod.setPrecio(price);
			serviceProduct.saveProduct(prod);
		}
		
		return "redirect:/admin";
		
	}
	
	//Método para abrir el formulario de edición de un producto existente
	@GetMapping("/edit/{id}")
	public String editProductForm(@PathVariable Long id, Model model){
		Producto prod = serviceProduct.getProductById(id);
		if(prod != null){
			model.addAttribute("product", prod);
		}

		return "product-form";
	}

	//Método para editar un producto existente
	@PostMapping("/edit/{id}")
	public String editProduct(@PathVariable Long id, @RequestParam String name, @RequestParam double price){

		Producto prod = serviceProduct.getProductById(id);

		if(prod != null){
			prod.setNombre(name);
			prod.setPrecio(price);
			serviceProduct.saveProduct(prod);
		}
		
		return "redirect:/admin";

	}
	// Método par eliminar un producto existente
	@PostMapping("/delete/{id}")
	public String deleteProduct(@PathVariable Long id) {
		serviceProduct.deleteProduct(id);
		return "redirect:/admin";
	}
	// Método para la búsqueda de productos por nombre
	@GetMapping("/search")
	public String searchProduct(@RequestParam String consulta, Model model){
		model.addAttribute("products", serviceProduct.searchProduct(consulta));

		return "products";
	}

	//Método para filtrar productos por rango de precio

	@GetMapping("/filter")
	public String filterProducts(@RequestParam double minimo, @RequestParam double maximo, Model model){
		model.addAttribute("products", serviceProduct.findByPrecioBetween(minimo, maximo));

		return "products";
	}

}
