package com.generation.crudfarmacia.controller;

import com.generation.crudfarmacia.model.Categoria;
import com.generation.crudfarmacia.repository.CategoriaRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CategoriaControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @BeforeAll
    void start(){
        categoriaRepository.deleteAll();
    }

    @Test
    @DisplayName("Cadastrar uma Categoria")
    public void deveCriarUmaCategoria() {
        HttpEntity<Categoria> corpoRequisicao = new HttpEntity<>(new Categoria(2L, "Genérico", "Categoria para os remédios classificados como numéricos"));

        ResponseEntity<Categoria> corpoResposta = testRestTemplate
                .exchange("/categorias", HttpMethod.POST, corpoRequisicao, Categoria.class);

        assertEquals(HttpStatus.CREATED, corpoResposta.getStatusCode());
    }

    @Test
    @DisplayName("Atualizar uma Categoria Existente")
    public void deveAtualizarUmaCategoriaExistente() {
        Categoria categoria = categoriaRepository.save(new Categoria(null, "Teste", "Esta é uma Categoria Teste para um remédio"));

        Categoria categoriaAtualizada = new Categoria(categoria.getId(), "Teste Atualizado", "Este é uma Categoria Teste para um remédio atualizada");
        HttpEntity<Categoria> corpoRequisicao = new HttpEntity<>(categoriaAtualizada);

        ResponseEntity<Categoria> corpoResposta = testRestTemplate
                .exchange("/categorias", HttpMethod.PUT, corpoRequisicao, Categoria.class);

        assertEquals(HttpStatus.OK, corpoResposta.getStatusCode());

    }




}

