package com.appProdutos.produtos.service;

import com.appProdutos.produtos.dto.ProdutoAtualizacaoDto;
import com.appProdutos.produtos.dto.ProdutoDetalhamentoDto;
import com.appProdutos.produtos.dto.ProdutoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProdutoService {
    ProdutoDetalhamentoDto cadastrar(ProdutoDto dto);
    Page<ProdutoDetalhamentoDto> listarTodos(Pageable paginacao);
    Page<ProdutoDetalhamentoDto> listarProdutoPorNome(String nome, Pageable paginacao);
    ProdutoDetalhamentoDto listPorId(Long id);
    void excluir(Long id);
    ProdutoAtualizacaoDto atualizar(Long id, ProdutoAtualizacaoDto dto);
}
