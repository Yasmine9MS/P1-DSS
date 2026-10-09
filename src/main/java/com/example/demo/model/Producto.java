package com.example.demo.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

//Indica a JPA que la clase representa una entidad que se almacena en la bd
@Entity
@Data //Lombok permite generar los getters y setters de forma automática
@NoArgsConstructor
public class Producto{
	@GeneratedValue(strategy = GenerationType.AUTO)//Indica a JPA que el valor del atributo id se generará automáticamente
	@Id //Esta anotación indica que el atributo id es la clave primaria de la entidad
	private Long id;
	private String nombre; 
	private Double precio;
	
	public Producto(String nombre, Double precio) {
		this.nombre = nombre;
		this.precio = precio;
	}
	

}

