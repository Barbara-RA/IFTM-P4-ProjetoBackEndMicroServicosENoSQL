package com.barbara.mongodb.relacionamentos.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "estudantes")
public class Estudante {
    @Id
    private String id;
    private String nome;

    @DBRef
    private List<Curso> curso;
    
}