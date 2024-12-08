package com.calcados.store.dto;

import java.util.List;

public class CategoriaDTO {

    private Long id;
    private String nome;
    private List<ProdutoDTO> produtos;

    public CategoriaDTO() { }

    public CategoriaDTO(Long id, String nome, List<ProdutoDTO> produtos) {
        this.id = id;
        this.nome = nome;
        this.produtos = produtos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<ProdutoDTO> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoDTO> produtos) {
        this.produtos = produtos;
    }
}
