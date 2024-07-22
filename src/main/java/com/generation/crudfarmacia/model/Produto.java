package com.generation.crudfarmacia.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_produtos")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O atributo nome é obrigatório")
    @Size(min = 3, max = 200, message = "O atributo nome deve ter no mínimo 3 e no máximo 200 caracteres")
    private String nome;

    @Size(min = 10, max = 3000, message = "A descrição de produto precisar ter no mínimo 10 e no máximo 3000 caracteres")
    private String descricao;

    @NotNull(message = "O atributo preco é obrigatório.")
    @Digits(integer = 8, fraction = 2, message = "O atributo preco deve deve conter no máximo 8 casas inteiras e 2 decimais.")
    private BigDecimal preco;

    @NotBlank(message = "O atributo fabricante é obrigatório")
    @Size(min = 2, max = 200, message = "O atributo fabricante deve ter no mínimo 2 e no máximo 200 caracteres")
    private String fabricante;

    @ManyToOne
    @JsonIgnoreProperties("produto")
    private Categoria categoria;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
