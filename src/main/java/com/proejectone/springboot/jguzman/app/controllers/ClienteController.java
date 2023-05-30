package com.proejectone.springboot.jguzman.app.controllers;


import com.proejectone.springboot.jguzman.app.models.Cliente;
import com.proejectone.springboot.jguzman.app.models.Producto;
import com.proejectone.springboot.jguzman.app.models.Region;
import com.proejectone.springboot.jguzman.app.services.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;


    @GetMapping("/clientes")
    public List<Cliente> findAll(){
        List<Cliente> response = clienteService.findAll();
        return response;
    }


    @GetMapping("/clientes/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        ResponseEntity<?> response = clienteService.findById(id);
        return response;
    }

    @PostMapping("/clientes")
    public ResponseEntity<?> saveCliente(@Valid @RequestBody Cliente cliente, BindingResult result){
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
        ResponseEntity<?> response = clienteService.save(cliente);
        return response;
    }


    @PutMapping("/clientes/{clienteId}")
    public ResponseEntity<?>  updateCliente(@Valid @RequestBody Cliente cliente, BindingResult result, @PathVariable Long clienteId){
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
        ResponseEntity<?> response = clienteService.update(cliente,clienteId);
        return response;
    }


    @DeleteMapping("/clientes/{clienteId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long clienteId){
        ResponseEntity<?> response = clienteService.delete(clienteId);
        return response;
    }
}
