package com.proejectone.springboot.jguzman.app.controllers;

import com.proejectone.springboot.jguzman.app.models.Producto;
import com.proejectone.springboot.jguzman.app.services.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ProductoController {

    @Autowired
    private IProductoService service;


    @GetMapping("/product")
    public List<Producto> search(){
        List<Producto> response = service.findAll();
        return response;
    }


    @GetMapping("/product/{id}")
    public ResponseEntity<?> searchById(@PathVariable Long id){
        ResponseEntity<?> response = service.findById(id);
        return response;
    }

    @PostMapping("/product")
    public ResponseEntity<?> saveProduct(@Valid @RequestBody Producto product, BindingResult result){
                Map<String, Object> errors = new HashMap<>();
                if(result.hasErrors()){
                    System.out.println("aca!");
                    List<String> errores = result.getFieldErrors()
                            .stream()
                            .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                            .collect(Collectors.toList());

                    errors.put("errors", errores);
                    return new ResponseEntity<Map<String, Object>>(errors, HttpStatus.BAD_REQUEST);
                }
                ResponseEntity<?> response = service.save(product);
                return response;
        }


    @PutMapping("/product/{id}")
    public ResponseEntity<?> updateProduct(@Valid @RequestBody Producto product, BindingResult result , @PathVariable Long id){
        Map<String, Object> errors = new HashMap<>();
        if(result.hasErrors()){
            List<String> errores = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            errors.put("errors", errores);
            return new ResponseEntity<Map<String, Object>>(errors, HttpStatus.BAD_REQUEST);
        }
        ResponseEntity<?> response = service.update(product, id);
        return response;
    }



    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        ResponseEntity<?> response = service.delete(id);
        return response;
    }
}
