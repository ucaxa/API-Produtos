package com.appProdutos.produtos.datasource.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Table
@Entity(name = "produtos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nome;
    @Column
    private String descricao;
    @Column
    private BigDecimal preco;
    @Column
    private Integer quantidadeEstoque;

}
