package com.proejectone.springboot.jguzman.app.dao;

import com.proejectone.springboot.jguzman.app.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoDao extends JpaRepository<Producto , Long> {
}
