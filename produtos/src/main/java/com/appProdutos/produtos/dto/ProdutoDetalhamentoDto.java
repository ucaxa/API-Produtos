package com.appProdutos.produtos.dto;

import com.appProdutos.produtos.model.Produto;

import java.math.BigDecimal;

public record ProdutoDetalhamentoDto(Long id, String nome, String descricao, BigDecimal preco,
                                      Integer quantidadeEstoque) {
    public ProdutoDetalhamentoDto(Produto produto){
        this(produto.getId(), produto.getNome(), produto.getDescricao(),produto.getPreco(), produto.getQuantidadeEstoque());
    }
}
