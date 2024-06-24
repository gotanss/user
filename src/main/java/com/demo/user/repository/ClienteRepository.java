package com.demo.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.user.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {

   boolean existsClienteByTelefono(String tel);
}
