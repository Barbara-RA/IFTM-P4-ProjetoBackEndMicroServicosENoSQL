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

import com.barbara.mongodb.relacionamentos.models.Postagem;
import com.barbara.mongodb.relacionamentos.repositories.PostagemRepository;

@RestController
@RequestMapping("/postagens")
public class PostagemController {

    @Autowired
    private PostagemRepository postagemRepository;

    // POST /postagens - adiciona uma nova postagem
    @PostMapping
    public Postagem create(@RequestBody Postagem postagem) {
        return postagemRepository.save(postagem);
    }

    // GET /postagens - retorna todas as postagens
    @GetMapping
    public List<Postagem> getAll() {
        return postagemRepository.findAll();
    }

    // GET /postagens/{id} - retorna apenas o ID selecionado
    @GetMapping("/{id}")
    public ResponseEntity<Postagem> getById(@PathVariable String id) {
        return postagemRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT /postagens/{id} - atualiza uma postagem
    @PutMapping("/{id}")
    public ResponseEntity<Postagem> update(@PathVariable String id, @RequestBody Postagem postagem) {
        return postagemRepository.findById(id)
                .map(postagemExistente -> {
                    postagem.setId(postagemExistente.getId()); // Mantém o ID existente
                    Postagem postagemAtualizada = postagemRepository.save(postagem);
                    return ResponseEntity.ok(postagemAtualizada);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /postagens/{id} - deleta uma postagem
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (postagemRepository.existsById(id)) {
            postagemRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // Retorna 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found
        }
    }
}

