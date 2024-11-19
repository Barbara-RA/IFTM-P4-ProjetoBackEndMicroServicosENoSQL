package com.barbara.mongodb.relacionamentos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barbara.mongodb.relacionamentos.models.Estudante;
import com.barbara.mongodb.relacionamentos.repositories.CursoRepository;
import com.barbara.mongodb.relacionamentos.repositories.EstudanteRepository;

@RestController
@RequestMapping("/estudantes")
public class EstudanteController {

    @Autowired
    private EstudanteRepository estudanteRepository;

    @Autowired
    private CursoRepository cursoRepository;

    // POST /estudantes - adiciona um estudante
    @PostMapping
    public Estudante create(@RequestBody Estudante estudante) {
        if (estudante.getCurso() != null) {
            estudante.setCurso(
                estudante.getCurso().stream()
                    .map(curso -> curso.getId() == null ? cursoRepository.save(curso) : curso)
                    .toList()
            );
        }
        return estudanteRepository.save(estudante);
    }

    // GET /estudantes - retorna todos os estudantes
    @GetMapping
    public List<Estudante> getAll() {
        return estudanteRepository.findAll();
    }

    // GET /estudantes/{id} - retorna apenas o ID selecionado
    @GetMapping("/{id}")
    public ResponseEntity<Estudante> getById(@PathVariable String id) {
        return estudanteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT /estudantes/{id} - atualiza um estudante
    @PutMapping("/{id}")
    public ResponseEntity<Estudante> update(@PathVariable String id, @RequestBody Estudante estudante) {
        return estudanteRepository.findById(id)
                .map(estudanteExistente -> {
                    estudante.setId(estudanteExistente.getId()); // Mantém o ID existente
                    if (estudante.getCurso() != null) {
                        estudante.setCurso(
                            estudante.getCurso().stream()
                                .map(curso -> curso.getId() == null ? cursoRepository.save(curso) : curso)
                                .toList()
                        );
                    }
                    Estudante estudanteAtualizado = estudanteRepository.save(estudante);
                    return ResponseEntity.ok(estudanteAtualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /estudantes/{id} - deleta um estudante
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (estudanteRepository.existsById(id)) {
            estudanteRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // Retorna 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found
        }
    }
}
