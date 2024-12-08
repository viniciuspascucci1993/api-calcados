package com.calcados.store.dto;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProdutoDTO {

    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Integer unidadesEmEstoque;
    private String imagemUrl;

    private Date dataCriacao;

    private Date ultimaAtualizacao;
    private boolean ativo;


    public ProdutoDTO() { }

    public ProdutoDTO(Long id, String nome, String descricao, BigDecimal preco, Integer unidadesEmEstoque,
                      String imagemUrl, Date dataCriacao, Date ultimaAtualizacao,
                      boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.unidadesEmEstoque = unidadesEmEstoque;
        this.imagemUrl = imagemUrl;
        this.dataCriacao = dataCriacao;
        this.ultimaAtualizacao = ultimaAtualizacao;
        this.ativo = ativo;
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

    public Integer getUnidadesEmEstoque() {
        return unidadesEmEstoque;
    }

    public void setUnidadesEmEstoque(Integer unidadesEmEstoque) {
        this.unidadesEmEstoque = unidadesEmEstoque;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    public void setUltimaAtualizacao(Date ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
