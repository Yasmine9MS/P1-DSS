package com.example.demo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;



@Controller 
@RequestMapping("/admin")
public class AdminController{
    
    @Autowired
    private ProductService serviceProduct;
    @Autowired
    private DatabaseExportService databaseExportService;

    @GetMapping
    public String adminPage(Model modelo){
        modelo.addAttribute("products", serviceProduct.getAllProducts());
        return "admin";
    }
    

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
