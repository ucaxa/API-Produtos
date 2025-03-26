package com.appProdutos.produtos.dto;

import com.appProdutos.produtos.model.Produto;

import java.math.BigDecimal;

public record ProdutoAtualizacaoDto(
        String nome, String descricao, BigDecimal preco,Integer quantidadeEstoque) {
    public ProdutoAtualizacaoDto(Produto produto) {
        this(produto.getNome(), produto.getDescricao(), produto.getPreco(), produto.getQuantidadeEstoque());
    }

}


