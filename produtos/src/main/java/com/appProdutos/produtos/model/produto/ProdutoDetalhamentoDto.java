package com.appProdutos.produtos.model.produto;

import com.appProdutos.produtos.datasource.entity.Produto;

import java.math.BigDecimal;

public record ProdutoDetalhamentoDto(Long id, String nome, String descricao, BigDecimal preco,
                                      Integer quantidadeEstoque) {
    public ProdutoDetalhamentoDto(Produto produto){
        this(produto.getId(), produto.getNome(), produto.getDescricao(),produto.getPreco(), produto.getQuantidadeEstoque());
    }
}
