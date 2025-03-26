package com.appProdutos.produtos.factory;

import com.appProdutos.produtos.model.Produto;
import com.appProdutos.produtos.dto.ProdutoDto;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ProdutoFactory {

    public static Produto create(ProdutoDto dto) {
        return Produto.builder()
                .nome(dto.nome())
                .descricao(dto.descricao())
                .preco(dto.preco())
                .quantidadeEstoque(dto.quantidadeEstoque())
                .build();
    }
}
