package com.appProdutos.produtos.dto;

import com.appProdutos.produtos.model.Produto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProdutoDto(@NotBlank String nome,@NotBlank String descricao, @NotNull @Positive BigDecimal preco,
                         @NotNull Integer quantidadeEstoque) {
   public ProdutoDto(Produto produto){
        this(produto.getNome(),produto.getDescricao(),produto.getPreco(),produto.getQuantidadeEstoque());
    }

}
