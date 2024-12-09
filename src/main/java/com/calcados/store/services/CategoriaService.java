package com.calcados.store.services;

import com.calcados.store.dto.CategoriaDTO;
import com.calcados.store.dto.ProdutoDTO;
import com.calcados.store.entities.CategoriaEntity;
import com.calcados.store.entities.ProdutoEntity;
import com.calcados.store.repositories.CategoriaRepository;
import com.calcados.store.repositories.ProdutoRepository;
import com.calcados.store.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoDTO> listarProdutosPorCategoria(Long categoriaId) {
        CategoriaEntity categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new ObjectNotFoundException(
                        "Categoria não encontrada: " + categoriaId));

        return categoria.getProdutos()
                .stream()
                .map(this::toProdutoDTO)
                .collect(Collectors.toList());
    }

    // Método para listar todas as categorias
    public List<CategoriaDTO> listarTodas() {
        List<CategoriaEntity> categorias = categoriaRepository.findAll();
        if (categorias.isEmpty()) {
            throw new ObjectNotFoundException("Nenhuma categoria encontrada.");
        }

        return categorias.stream()
                .map(this::toCategoriaDTO)
                .toList();
    }

    public CategoriaDTO buscar(Long id) {
        CategoriaEntity categoriaEntity = categoriaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        "Objeto categoria não encontrado: " + id + ", Tipo: " + CategoriaEntity.class.getName()));
        return toCategoriaDTO(categoriaEntity);
    }

    @Transactional
    public CategoriaDTO inserir(CategoriaDTO categoriaDTO) {
        var categoriaEntity = new CategoriaEntity();
        categoriaEntity = getCategoriaEntity(categoriaDTO, categoriaEntity);

        return new CategoriaDTO(
                categoriaEntity.getId(),
                categoriaEntity.getNome(),
                toListProdutoDTO(categoriaEntity.getProdutos())
        );
    }

    @Transactional
    public CategoriaDTO atualizar(Long id, CategoriaDTO categoriaDTO) {
        CategoriaEntity categoriaEntity = categoriaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        "Objeto categoria não encontrado: " + id + ", Tipo: " + CategoriaEntity.class.getName()));

        categoriaEntity = getCategoriaEntity(categoriaDTO, categoriaEntity);

        return new CategoriaDTO(
                categoriaEntity.getId(),
                categoriaEntity.getNome(),
                toListProdutoDTO(categoriaEntity.getProdutos()));
    }

    private CategoriaEntity getCategoriaEntity(CategoriaDTO categoriaDTO, CategoriaEntity categoriaEntity) {
        categoriaEntity.setNome(categoriaDTO.getNome());

        // Converter os DTOs de produtos para entidades de produtos
        List<ProdutoEntity> produtos = toListProdutoEntity(categoriaDTO.getProdutos());

        // Garantir que a coleção de produtos esteja inicializada corretamente
        if (categoriaEntity.getProdutos() == null) {
            categoriaEntity.setProdutos(new ArrayList<>());
        }

        for (ProdutoEntity produto : produtos) {
            if (produto.getCategorias() == null) {
                produto.setCategorias(new ArrayList<>());
            }

            // Adicionar a categoria à coleção de categorias do produto
            if (!produto.getCategorias().contains(categoriaEntity)) {
                produto.getCategorias().add(categoriaEntity);
            }

            // Adicionar o produto à lista de produtos da categoria
            if (!categoriaEntity.getProdutos().contains(produto)) {
                categoriaEntity.getProdutos().add(produto);
            }
        }

        // Salvar a categoria e os produtos
        categoriaEntity = categoriaRepository.save(categoriaEntity);
        produtoRepository.saveAll(produtos); // Salvar todos os produtos modificados

        return categoriaEntity;
    }

    @Transactional
    public void delete(Long id) {
        CategoriaEntity categoriaEntity = categoriaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        "Objeto categoria não encontrado: " + id + ", Tipo: " + CategoriaEntity.class.getName()));

        // Remover a associação de produtos com a categoria (relacionamento bidirecional)
        for (ProdutoEntity produto : categoriaEntity.getProdutos()) {
            if (produto.getCategorias() != null) {
                produto.getCategorias().remove(categoriaEntity);
            }
        }

        // Remover a categoria do banco de dados
        categoriaRepository.delete(categoriaEntity);
    }

    private List<ProdutoEntity> toListProdutoEntity(List<ProdutoDTO> produtos) {
        if (produtos == null || produtos.isEmpty()) {
            throw new ObjectNotFoundException("Favor informar o produto");
        }

        return produtos.stream()
                .map(produtoDTO -> new ProdutoEntity(
                        produtoDTO.getId(),
                        produtoDTO.getNome(),
                        produtoDTO.getDescricao(),
                        produtoDTO.getPreco(),
                        produtoDTO.getUnidadesEmEstoque(),
                        produtoDTO.getDataCriacao(),
                        produtoDTO.getUltimaAtualizacao(),
                        produtoDTO.isAtivo()
                ))
                .toList();
    }

    private List<ProdutoDTO> toListProdutoDTO(List<ProdutoEntity> produtoEntities) {
        if (produtoEntities == null || produtoEntities.isEmpty()) {
            throw new ObjectNotFoundException("Favor informar o produto");
        }

        return produtoEntities.stream()
                .map(produto -> new ProdutoDTO(
                        produto.getId(),
                        produto.getNome(),
                        produto.getDescricao(),
                        produto.getPreco(),
                        produto.getUnidadesEmEstoque(),
                        produto.getDataCriacao(),
                        produto.getUltimaAtualizacao(),
                        produto.isAtivo()
                ))
                .toList();
    }

    private CategoriaDTO toCategoriaDTO(CategoriaEntity categoriaEntity) {
        var produtos = categoriaEntity.getProdutos()
                .stream()
                .map(this::toProdutoDTO)
                .toList();

        return new CategoriaDTO(categoriaEntity.getId(), categoriaEntity.getNome(), produtos);
    }

    private ProdutoDTO toProdutoDTO(ProdutoEntity produto) {
        return new ProdutoDTO(produto.getId(), produto.getNome(), produto.getDescricao(),
                produto.getPreco(), produto.getUnidadesEmEstoque(), produto.getDataCriacao(),
                produto.getUltimaAtualizacao(), produto.isAtivo());
    }
}
