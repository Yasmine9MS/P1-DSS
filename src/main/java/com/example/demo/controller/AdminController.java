package com.example.demo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.DatabaseExportService;
import com.example.demo.service.ProductService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;



@Controller 
@RequestMapping("/admin")
public class AdminController{
    
    //Inyección de dependencias para los servicios necesarios
    @Autowired
    private ProductService serviceProduct;
    @Autowired
    private DatabaseExportService databaseExportService;

    //Obtiene todos los productos y los agrega al modelo para enviarlos a la vista
    @GetMapping
    public String adminPage(Model modelo){
        modelo.addAttribute("products", serviceProduct.getAllProducts());
        return "admin";
    }
    

    //endpoint para exportar los datos de la base de datos
    @GetMapping ("/export")
    public ResponseEntity<byte[]> exportDatabaseToSqlFile(){
        byte[] ficheroSql = databaseExportService.exportDatabaseToSqlFile();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"productos.sql\"")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(ficheroSql);

    }
}
