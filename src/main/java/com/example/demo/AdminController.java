package com.example.demo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;



@Controller 
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController{
    private final DatabaseExportService databaseExportService;
    

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
