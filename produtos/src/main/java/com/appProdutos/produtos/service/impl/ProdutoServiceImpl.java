package com.appProdutos.produtos.service.impl;

import com.appProdutos.produtos.repository.ProdutoRepository;
import com.appProdutos.produtos.factory.ProdutoFactory;
import com.appProdutos.produtos.dto.ProdutoAtualizacaoDto;
import com.appProdutos.produtos.dto.ProdutoDetalhamentoDto;
import com.appProdutos.produtos.dto.ProdutoDto;
import com.appProdutos.produtos.service.ProdutoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository repository;

    @Override
    public ProdutoDetalhamentoDto cadastrar(ProdutoDto dto) {
        var produto = ProdutoFactory.create(dto);
        repository.save(produto);
        return new ProdutoDetalhamentoDto(produto);
    }

    @Override
    @Transactional
    public ProdutoAtualizacaoDto atualizar(Long id, ProdutoAtualizacaoDto dto) {

        var  produto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        if (dto.nome()!=null){
            produto.setNome(dto.nome());
        }
        if (dto.descricao()!=null){
            produto.setDescricao(dto.descricao());
        }
        if (dto.preco()!=null){
            produto.setPreco(dto.preco());
        }
        if (dto.quantidadeEstoque()!=null){
            produto.setQuantidadeEstoque(dto.quantidadeEstoque());
        }
        return new ProdutoAtualizacaoDto(produto);
    }

    @Override
    public Page<ProdutoDetalhamentoDto> listarTodos(Pageable paginacao) {
        return repository
                .findAll(paginacao)
                .map(ProdutoDetalhamentoDto::new);
    }

    @Override
    public Page<ProdutoDetalhamentoDto> listarProdutoPorNome(String nome, Pageable paginacao) {
        return repository
                .findByNomeContainingIgnoreCase(nome, paginacao)
                .map(ProdutoDetalhamentoDto::new);
    }

    @Override
    public ProdutoDetalhamentoDto listPorId(Long id) {
        var produto = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return new ProdutoDetalhamentoDto(produto);
    }

    @Override
    public void excluir(Long id) {
        var  produto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        repository.deleteById(id);
    }

}
