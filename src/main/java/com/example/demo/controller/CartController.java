package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.service.CartService;

//Controlador del carrito que maneja las peticiones relacionados con las operaciones de gestión de carrito

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired 
	private CartService serviceCart;
	
	//Método para mostrar todos los productos en el carrito actualmente
	@GetMapping
	public String showCartContents(Model model){
		
		model.addAttribute("cart", serviceCart.getAllCartItems());
		model.addAttribute("total", serviceCart.totalCart());
		return "cart";
	}
	
	//Función para agregar un producto al carrito de compras
	@PostMapping("/add/{id}")
	public String addItemToCart(@PathVariable Long id){
				
		serviceCart.addProductToCart(id);

		return "redirect:/cart";
		
	}
	
	//Función para eliminar un producto del carrito de compras
	@PostMapping("/remove/{id}")
	public String removeItemFromCart(@PathVariable Long id) {
		serviceCart.deleteProductFromCart(id);
		return "redirect:/cart";
	}
	
}
