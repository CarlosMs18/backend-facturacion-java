package com.proejectone.springboot.jguzman.app.services;

import com.proejectone.springboot.jguzman.app.models.Producto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IProductoService {

    public List<Producto> findAll();

    public ResponseEntity<?> findById(Long id);

    public ResponseEntity<?> save(Producto producto);

    public ResponseEntity<?> update(Producto producto, Long productId);

    public ResponseEntity<?> delete(Long id);
}
