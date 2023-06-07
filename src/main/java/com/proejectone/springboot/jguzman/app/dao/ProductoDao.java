package com.proejectone.springboot.jguzman.app.dao;

import com.proejectone.springboot.jguzman.app.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductoDao extends JpaRepository<Producto , Long> {

    @Query("select p from Producto p where p.nombre like %?1%")
    public List<Producto> findByNombre(String termino);

    public List<Producto> findByNombreContainingIgnoreCase(String termino);

    public List<Producto> findByNombreStartingWithIgnoreCase(String term);
}
