package com.generation.crudfarmacia.controller;

import com.generation.crudfarmacia.model.Produto;
import com.generation.crudfarmacia.model.Produto;
import com.generation.crudfarmacia.repository.ProdutoRepository;
import com.generation.crudfarmacia.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public ResponseEntity<List<Produto>> getAll() {
        return ResponseEntity.ok(produtoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getById(@PathVariable Long id) {
        return produtoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Produto>> getByNome(@PathVariable String nome) {
        return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
    }

    @PutMapping
    public ResponseEntity<Produto> put(@Valid @RequestBody Produto produto) {
        return produtoRepository.findById(produto.getId())
                .map(resposta -> ResponseEntity.status(HttpStatus.OK)
                        .body(produtoRepository.save(produto)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Produto> post(@Valid @RequestBody Produto produto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(produtoRepository.save(produto));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);

        if (produto.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        produtoRepository.deleteById(id);
    }
}
