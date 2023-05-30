package com.proejectone.springboot.jguzman.app.controllers;

import com.proejectone.springboot.jguzman.app.models.Factura;
import com.proejectone.springboot.jguzman.app.services.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FacturaController {

    @Autowired
    private IClienteService clienteService;

    @GetMapping("/facturas/{clienteId}")
    public Factura showFacturasbyCliente(@PathVariable Long clienteId){
        return clienteService.findFacturaById(clienteId);
    }
}
