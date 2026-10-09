package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.Producto;
import com.example.demo.repository.ProductRepo;

import org.springframework.beans.factory.annotation.Autowired;
import java.nio.charset.StandardCharsets;
import java.util.List;

//Servicio de exportación de la base de datos
@Service
public class DatabaseExportService {
    
    //Inyección de dependencias para el repositorio de productos
    @Autowired
    private ProductRepo productRepo;

    //Método para exportar los datos de la base de datos a un fichero SQL
    public byte[] exportDatabaseToSqlFile(){
        List<Producto> productos = productRepo.findAll();

        StringBuilder ficheroSql = new StringBuilder();

        for(Producto producto : productos){
            

            ficheroSql.append("INSERT INTO producto (id, nombre, precio) VALUES (")
                .append(producto.getId())
                .append(", '")
                .append(producto.getNombre().replace("'","''"))
                .append("', ")
                .append(producto.getPrecio())
                .append(");\n");
        }

        return ficheroSql.toString().getBytes(StandardCharsets.UTF_8);
    }
    
}
