package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService serviceProduct;
	
	@GetMapping
	public String getProducts(Model model) {
		model.addAttribute("products", serviceProduct.getAllProducts());
		return "products";
	}
	
	@PostMapping("/add")
	public String addProduct(@RequestParam String name, @RequestParam double price){

		Producto prod = new Producto();
		prod.setNombre(name);
		prod.setPrecio(price);
		serviceProduct.saveProduct(prod);

		return "redirect:/admin";

	}

	@GetMapping("/edit/{id}")
	public String editProductForm(@PathVariable Long id, Model model){
		Producto prod = serviceProduct.getProductById(id);
		if(prod != null){
			model.addAttribute("product", prod);
		}

		return "product-form";
	}

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

	@PostMapping("/delete/{id}")
	public String deleteProduct(@PathVariable Long id) {
		serviceProduct.deleteProduct(id);
		return "redirect:/admin";
	}

}
