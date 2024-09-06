package com.appProdutos.produtos.service;

import com.appProdutos.produtos.datasource.entity.Produto;
import com.appProdutos.produtos.datasource.repository.ProdutoRepository;
import com.appProdutos.produtos.model.produto.ProdutoDetalhamentoDto;
import com.appProdutos.produtos.service.impl.ProdutoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.MockitoAnnotations;
import com.appProdutos.produtos.model.produto.ProdutoDto;
import java.math.BigDecimal;
import java.util.Optional;

class ProdutoServiceTest {

    @InjectMocks
    private  ProdutoServiceImpl produtoService;
    @Mock
    private ProdutoRepository produtoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
     }

    @Test
    @DisplayName("teste deve cadastrar o produto e devolve-lo corretamente")
    public void cenario1() {
        Produto produto = new Produto();
        produto.setNome("Notebook Samsung");
        produto.setDescricao("Notebook com 16G e HD SSD");
        produto.setPreco(BigDecimal.valueOf(4500.00));
        produto.setQuantidadeEstoque(10);

        ProdutoDto produtoDto = new ProdutoDto(produto);
        when(produtoRepository.save(produto)).thenReturn(produto);
        // Executando o método  cadastror do service
        ProdutoDetalhamentoDto produtoSalvo = produtoService.cadastrar(produtoDto);
        // Verificando se o produto foi salvo corretamente
        assertEquals(produto.getNome(), produtoSalvo.nome());
        assertEquals(produto.getDescricao(), produtoSalvo.descricao());
        assertEquals(produto.getQuantidadeEstoque(), produtoSalvo.quantidadeEstoque());
        assertEquals(produto.getPreco(), produtoSalvo.preco());
    }


    @Test
    @DisplayName("Teste que deve atualizar o produto se ele existir")
    public void cenario2() {

        Produto produtoExistente = new Produto();
        produtoExistente.setId(1L);
        produtoExistente.setNome("Mouse ´Óptico");
        produtoExistente.setDescricao("Mouse Óptico com entrada USB");
        produtoExistente.setPreco(BigDecimal.valueOf(150.0));
        produtoExistente.setQuantidadeEstoque(5);

        ProdutoDetalhamentoDto  produtoDetalhamentoDto = new ProdutoDetalhamentoDto(produtoExistente);
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produtoExistente));
        when(produtoRepository.save(any(Produto.class))).thenReturn(produtoExistente);

        ProdutoDetalhamentoDto produtoAtualizado = produtoService.atualizar(1L, produtoDetalhamentoDto);
        assertNotNull(produtoAtualizado);
        assertEquals(produtoDetalhamentoDto.nome(), produtoAtualizado.nome());
        assertEquals(produtoDetalhamentoDto.descricao(), produtoAtualizado.descricao());
        assertEquals(BigDecimal.valueOf(150.0), produtoAtualizado.preco());
        assertEquals(5, produtoAtualizado.quantidadeEstoque());

        verify(produtoRepository, times(1)).findById(1L);

    }

     @Test
    @DisplayName("Teste que verifica o funcionamento da exclusão de um produto")
    public void cenario3() {
        Produto produtoExistente = new Produto();
        produtoExistente.setId(1L);
        produtoExistente.setNome("Impressora HP");
        produtoExistente.setDescricao("Impresssora Multifuncional compacta");
        produtoExistente.setPreco(BigDecimal.valueOf(50.0));
        produtoExistente.setQuantidadeEstoque(5);

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produtoExistente));
        doNothing().when(produtoRepository).delete(produtoExistente);
        produtoService.excluir(1L);

        verify(produtoRepository).findById(1L);
        verify(produtoRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Teste que verifica faz uma consulta por um produto ")
    public void cenario4() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Mochila Dell");
        produto.setDescricao("Mochila para notebook premium");
        produto.setPreco(BigDecimal.valueOf(50.0));
        produto.setQuantidadeEstoque(10);

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));

        ProdutoDetalhamentoDto produtoEncontrado = produtoService.listPorId(1L);
        assertNotNull(produtoEncontrado);
        assertEquals(produto.getNome(), produtoEncontrado.nome());
        assertEquals(produto.getDescricao(), produtoEncontrado.descricao());
        assertEquals(produto.getPreco(), produtoEncontrado.preco());
        assertEquals(produto.getQuantidadeEstoque(), produtoEncontrado.quantidadeEstoque());

    }
}
