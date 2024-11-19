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

import com.barbara.mongodb.relacionamentos.models.Perfil;
import com.barbara.mongodb.relacionamentos.repositories.PerfilRepository;

@RestController
@RequestMapping("/perfis")
public class PerfilController {

    @Autowired
    private PerfilRepository perfilRepository;

    // POST /perfis - adiciona um perfil
    @PostMapping
    public Perfil create(@RequestBody Perfil perfil) {
        return perfilRepository.save(perfil);
    }

    // GET /perfis - retorna todos os perfis
    @GetMapping
    public List<Perfil> getAll() {
        return perfilRepository.findAll();
    }

    // GET /perfis/{id} - retorna apenas o ID selecionado
    @GetMapping("/{id}")
    public ResponseEntity<Perfil> getById(@PathVariable String id) {
        return perfilRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT /perfis/{id} - atualiza um perfil
    @PutMapping("/{id}")
    public ResponseEntity<Perfil> update(@PathVariable String id, @RequestBody Perfil perfil) {
        return perfilRepository.findById(id)
                .map(perfilExistente -> {
                    perfil.setId(perfilExistente.getId()); // Mantém o ID existente
                    Perfil perfilAtualizado = perfilRepository.save(perfil);
                    return ResponseEntity.ok(perfilAtualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /perfis/{id} - deleta um perfil
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (perfilRepository.existsById(id)) {
            perfilRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // Retorna 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found
        }
    }
}
