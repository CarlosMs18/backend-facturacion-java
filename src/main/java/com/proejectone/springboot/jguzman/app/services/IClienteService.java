package com.proejectone.springboot.jguzman.app.services;

import com.proejectone.springboot.jguzman.app.models.Cliente;
import com.proejectone.springboot.jguzman.app.models.Producto;
import com.proejectone.springboot.jguzman.app.models.Region;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IClienteService {

    public List<Cliente> findAll();

    public ResponseEntity<?> findById(Long id);

    public ResponseEntity<?> save(Cliente cliente);

    public ResponseEntity<?> update(Cliente cliente, Long clienteId);

    public ResponseEntity<?> delete(Long id);
}
