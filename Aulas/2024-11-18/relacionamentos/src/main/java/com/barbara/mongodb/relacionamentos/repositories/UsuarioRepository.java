package com.barbara.mongodb.relacionamentos.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.barbara.mongodb.relacionamentos.models.Usuario;


public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    
}