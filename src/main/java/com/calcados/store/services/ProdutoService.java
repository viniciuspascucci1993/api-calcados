package com.calcados.store.services;

import com.calcados.store.dto.ProdutoDTO;
import com.calcados.store.entities.ProdutoEntity;
import com.calcados.store.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoDTO> getAllProducts() {
        List<ProdutoEntity> produtoEntities = produtoRepository.findAll();
        return toDTOList(produtoEntities);
    }

    private ProdutoDTO toDTO(ProdutoEntity entity) {
        var produtoDTO = new ProdutoDTO();
        produtoDTO.setId(entity.getId());
        produtoDTO.setNome(entity.getNome());
        produtoDTO.setDescricao(entity.getDescricao());
        produtoDTO.setPreco(entity.getPreco());
        produtoDTO.setUnidadesEmEstoque(entity.getUnidadesEmEstoque());
        produtoDTO.setDataCriacao(entity.getDataCriacao());
        produtoDTO.setUltimaAtualizacao(entity.getUltimaAtualizacao());
        produtoDTO.setAtivo(entity.isAtivo());

        return produtoDTO;
    }

    private List<ProdutoDTO> toDTOList(List<ProdutoEntity> entities) {
        return entities.stream()
                .map(this::toDTO)
                .toList();
    }
}
