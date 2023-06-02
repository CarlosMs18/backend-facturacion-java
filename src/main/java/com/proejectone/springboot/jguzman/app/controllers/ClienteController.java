package com.proejectone.springboot.jguzman.app.controllers;


import com.proejectone.springboot.jguzman.app.models.Cliente;
import com.proejectone.springboot.jguzman.app.models.Producto;
import com.proejectone.springboot.jguzman.app.models.Region;
import com.proejectone.springboot.jguzman.app.services.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@CrossOrigin(origins = {"http://localhost:4200"})
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


    @GetMapping("/clientes/page/{page}")
    public Page<Cliente> findAllPages(@PathVariable Integer page){
        Pageable pageable = PageRequest.of(page,2);
        return clienteService.findAll(pageable);
    }


    @GetMapping("/clientes/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        ResponseEntity<?> response = clienteService.findById(id);
        return response;
    }

    @PostMapping("/clientes")
    public ResponseEntity<?> saveCliente(@Valid @RequestBody Cliente cliente, BindingResult result){
        System.out.println("reee");
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

        if(!cliente.getEmail().isEmpty() && clienteService.existePorEmail(cliente.getEmail())){
            return ResponseEntity.badRequest()
                    .body(Collections
                            .singletonMap("mensaje", "Ya existe un usuario con ese correo electronico!"));
        }
        System.out.println("reeei");
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


        /*
        if(!cliente.getEmail().isEmpty() && clienteService.existePorEmail(cliente.getEmail())){
            return ResponseEntity.badRequest()
                    .body(Collections
                            .singletonMap("mensaje", "Ya existe un usuario con ese correo electronico!"));
        }
        */

        ResponseEntity<?> response = clienteService.update(cliente,clienteId);
        return response;
    }


    @DeleteMapping("/clientes/{clienteId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long clienteId){
        ResponseEntity<?> response = clienteService.delete(clienteId);
        return response;
    }
}
