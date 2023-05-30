package com.proejectone.springboot.jguzman.app.services;

import com.proejectone.springboot.jguzman.app.models.Cliente;
import com.proejectone.springboot.jguzman.app.models.Region;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IRegionService {

    public List<Region> findAll();

    public ResponseEntity<?> findById(Long id);

    public ResponseEntity<?> save(Region region);

    public ResponseEntity<?> update(Region region);

    public ResponseEntity<?> delete(Long id);
}
