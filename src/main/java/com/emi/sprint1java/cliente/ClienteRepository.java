package com.emi.sprint1java.cliente;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByEmail(String ds_email);
    
    List<Cliente> findByClienteContainingIgnoreCase(String nm_usuario);
}