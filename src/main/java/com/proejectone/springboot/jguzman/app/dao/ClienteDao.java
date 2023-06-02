package com.proejectone.springboot.jguzman.app.dao;

import com.proejectone.springboot.jguzman.app.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClienteDao extends JpaRepository<Cliente, Long> {

    @Query("select u from Cliente u where u.nombre like %?1%")
    List<Cliente> findByNameLike(String name);

    @Query("select u from Cliente u where u.email=?1")
    Optional<Cliente> porEmail(String email);
    boolean existsByEmail(String email);
}
