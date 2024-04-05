package com.example.rest.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuariosRepository extends JpaRepository<UsuariosEntity, Long> {
    UserDetails findByEmail(String username);
}