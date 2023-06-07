package com.proejectone.springboot.jguzman.app.controllers;

import com.proejectone.springboot.jguzman.app.models.Factura;
import com.proejectone.springboot.jguzman.app.models.Producto;
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

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class FacturaController {

    @Autowired
    private IClienteService clienteService;

    @GetMapping("/facturas/{id}")
    public Factura showFacturasbyCliente(@PathVariable Long id){
        return clienteService.findFacturaById(id);
    }

    @DeleteMapping("/facturas/{id}")
    public void deleteFactura(@PathVariable Long id) {
        clienteService.deleteFacturaById(id);
    }


    @GetMapping("/facturas/filtrar-productos/{term}")
    public List<Producto> filtrarProductos(@PathVariable String term){
        return clienteService.findProductoByNombre(term);
    }


    @PostMapping("/facturas")
    public ResponseEntity<?>crearFactura(@Valid @RequestBody Factura factura , BindingResult result){

        Map<String, Object> errors = new HashMap<>();
        if(result.hasErrors()){

            List<String> errores = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            errors.put("errors", errores);
            return new ResponseEntity<Map<String, Object>>(errors, HttpStatus.BAD_REQUEST);
        }
        ResponseEntity<?> response = clienteService.saveFactura(factura);
        return response;
    }
}
