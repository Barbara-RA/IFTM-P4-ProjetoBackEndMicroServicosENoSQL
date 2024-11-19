package com.barbara.mongodb.relacionamentos.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.barbara.mongodb.relacionamentos.models.Postagem;

public interface PostagemRepository extends MongoRepository<Postagem, String> {

    
} 
