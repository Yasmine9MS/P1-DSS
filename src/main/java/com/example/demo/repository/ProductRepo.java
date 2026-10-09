package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Producto;

import java.util.List;

//Interfaz que extiende JpaRepository para proporcionar métodos CRUD y consultas personalizadas para la entidad Producto
public interface ProductRepo extends JpaRepository<Producto, Long>{

    //Permite buscar productos cuyo nombre contenga el texto indicado en 'consulta'. No distingue entre mayúsculas y minúsculas
    List<Producto> findByNombreContainingIgnoreCase(String consulta); 
    //Búsqueda de productos cuyo precio esté entre mínimo y máximo devolviendo una lista con los productos que cumplen
    List<Producto> findByPrecioBetween(double minimo, double maximo);

}
