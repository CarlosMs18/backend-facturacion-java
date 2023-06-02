package com.proejectone.springboot.jguzman.app.services;

import com.proejectone.springboot.jguzman.app.models.Cliente;
import com.proejectone.springboot.jguzman.app.models.Factura;
import com.proejectone.springboot.jguzman.app.models.Producto;
import com.proejectone.springboot.jguzman.app.models.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IClienteService {

    public List<Cliente> findAll();

    public List<Cliente> searchByCliente(String name);

    public Page<Cliente> findAll(Pageable pageable);

    public ResponseEntity<?> findById(Long id);

    public ResponseEntity<?> save(Cliente cliente);

    public ResponseEntity<?> update(Cliente cliente, Long clienteId);

    public ResponseEntity<?> delete(Long id);


    boolean existePorEmail(String email);


    Optional<Cliente> porEmail(String email);
    //SERVICIOS PARA PODER REALIZAR OPERACIONES CON EL MODELO FACTURA

    public Factura findFacturaById(Long clienteId);
}
