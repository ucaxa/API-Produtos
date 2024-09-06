package com.appProdutos.produtos.model.factory;

import com.appProdutos.produtos.datasource.entity.Produto;
import com.appProdutos.produtos.model.produto.ProdutoDetalhamentoDto;
import com.appProdutos.produtos.model.produto.ProdutoDto;
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
