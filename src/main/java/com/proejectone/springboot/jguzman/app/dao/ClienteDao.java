package com.proejectone.springboot.jguzman.app.dao;

import com.proejectone.springboot.jguzman.app.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteDao extends JpaRepository<Cliente, Long> {
}
