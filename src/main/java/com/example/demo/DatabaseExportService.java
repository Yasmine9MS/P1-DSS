package com.example.demo;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class DatabaseExportService {
    
    @Autowired
    private ProductRepo productRepo;

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
