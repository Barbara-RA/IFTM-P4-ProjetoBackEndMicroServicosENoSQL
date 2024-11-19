package com.barbara.mongodb.relacionamentos.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.barbara.mongodb.relacionamentos.models.Estudante;

@Repository
public interface EstudanteRepository extends MongoRepository<Estudante, String> {
}
