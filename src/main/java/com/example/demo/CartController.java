package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired 
	private CartService serviceCart;
	@Autowired
	private ProductService serviceProduct;
	
	@GetMapping
	public String showCartContents(Model model){
		
		model.addAttribute("cart", serviceCart.getAllCartItems());
		return "cart";
	}
	
	@PostMapping("/add/{id}")
	public String addItemToCart(@PathVariable Long id){
		Producto prod = serviceProduct.getProductById(id);
		
		if(prod != null) {
			serviceCart.addProductToCart(prod);
		}
		
		return "redirect:/cart";
		
	}
	
	@PostMapping("/remove/{id}")
	public String removeItemFromCart(@PathVariable Long id) {
		Producto prod = serviceProduct.getProductById(id);
		
		if(prod != null) {
			serviceCart.deleteProductFromCart(prod);
		}
		
		return "redirect:/cart";
	}
	
	
	

}
