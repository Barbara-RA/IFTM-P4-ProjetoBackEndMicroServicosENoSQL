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

import com.barbara.mongodb.relacionamentos.models.Curso;
import com.barbara.mongodb.relacionamentos.repositories.CursoRepository;
import com.barbara.mongodb.relacionamentos.repositories.EstudanteRepository;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private EstudanteRepository estudanteRepository;

    // POST /cursos - adiciona um curso
    @PostMapping
    public Curso create(@RequestBody Curso curso) {
        if (curso.getEstudantes() != null) {
            curso.setEstudantes(
                curso.getEstudantes().stream()
                    .map(estudante -> estudante.getId() == null ? estudanteRepository.save(estudante) : estudante)
                    .toList()
            );
        }
        return cursoRepository.save(curso);
    }

    // GET /cursos - retorna todos os cursos
    @GetMapping
    public List<Curso> getAll() {
        return cursoRepository.findAll();
    }

    // GET /cursos/{id} - retorna apenas o ID selecionado
    @GetMapping("/{id}")
    public ResponseEntity<Curso> getById(@PathVariable String id) {
        return cursoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT /cursos/{id} - atualiza um curso
    @PutMapping("/{id}")
    public ResponseEntity<Curso> update(@PathVariable String id, @RequestBody Curso curso) {
        return cursoRepository.findById(id)
                .map(cursoExistente -> {
                    curso.setId(cursoExistente.getId()); // Mantém o ID existente
                    if (curso.getEstudantes() != null) {
                        curso.setEstudantes(
                            curso.getEstudantes().stream()
                                .map(estudante -> estudante.getId() == null ? estudanteRepository.save(estudante) : estudante)
                                .toList()
                        );
                    }
                    Curso cursoAtualizado = cursoRepository.save(curso);
                    return ResponseEntity.ok(cursoAtualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /cursos/{id} - deleta um curso
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (cursoRepository.existsById(id)) {
            cursoRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // Retorna 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found
        }
    }
}

