package com.example.demo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepo extends JpaRepository<Producto, Long>{

    List<Producto> findByNombreContainingIgnoreCase(String consulta);
    List<Producto> findByPrecioBetween(double minimo, double maximo);

}
