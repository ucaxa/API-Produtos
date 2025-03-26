package com.appProdutos.produtos.repository;

import com.appProdutos.produtos.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {

    Page<Produto> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
