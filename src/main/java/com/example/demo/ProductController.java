package com.example.demo;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
@RequestMapping("/products")
public class ProductController {
	
	private final ProductService serviceProduct;
	
	@GetMapping
	public String getProducts(Model model) {
		model.addAttribute("products", serviceProduct.getAllProducts());
		return "products";
	}
	
	@GetMapping("/add")
	public String addProductForm() {
		return "product-form";
	}
	
	@GetMapping("/edit/{id}")
	public String editProduct(@PathVariable Long id, Model model) {
		Producto prod = serviceProduct.getProductById(id);
		
		if(prod != null) {
			model.addAttribute("product", prod);
			model.addAttribute("editando", true);
		}
		
		return "product-form";
	}
	
	@PostMapping("/save")
	public String saveProduct(@RequestParam(required=false) Long id, @RequestParam String name, @RequestParam Double price) {
		Producto prod;
		if(id == null) {
			prod = new Producto();
		} else {
			prod = serviceProduct.getProductById(id);
		}
		
		if(prod != null) {
			prod.setNombre(name);
			prod.setPrecio(price);
			serviceProduct.saveProduct(prod);
		}
		
		return "redirect:/products";
		
	}
	
	@PostMapping("/delete/{id}")
	public String deleteProduct(@PathVariable Long id) {
		serviceProduct.deleteProduct(id);
		return "redirect:/products";
	}

}
