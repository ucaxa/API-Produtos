package com.appProdutos.produtos.controller;

import com.appProdutos.produtos.model.produto.ProdutoDetalhamentoDto;
import com.appProdutos.produtos.model.produto.ProdutoDto;
import com.appProdutos.produtos.service.ProdutoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService service;

    @PostMapping
    public ResponseEntity<ProdutoDetalhamentoDto> cadastrar(@RequestBody @Valid ProdutoDto dto, UriComponentsBuilder uriBuilder) {
        var produtoDto = service.cadastrar(dto);
        URI endereco = uriBuilder.path("/produtos/{id}").buildAndExpand(produtoDto.id()).toUri();
        return ResponseEntity.created(endereco).body(produtoDto);
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoDetalhamentoDto>> listarTodos(@PageableDefault Pageable paginacao) {
        Page<ProdutoDetalhamentoDto> produtos = service.listarTodos(paginacao);
        return ResponseEntity.ok(produtos);
    }

    @GetMapping(value = "/buscarPorNome")
    public ResponseEntity<Page<ProdutoDetalhamentoDto>> listarTodosPorNome(@RequestParam String nome, @PageableDefault Pageable paginacao) {
        Page<ProdutoDetalhamentoDto> produtos = service.listarProdutoPorNome(nome, paginacao);
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDetalhamentoDto> listarPorId(@PathVariable @NotNull Long id) {
        var categoriaDto = service.listPorId(id);
        return ResponseEntity.ok(categoriaDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDetalhamentoDto> atualizar(@PathVariable @NotNull Long id, @RequestBody @Valid ProdutoDetalhamentoDto dto) {
        var produtoDetalhamentoDtoDto = service.atualizar(id, dto);
        return ResponseEntity.ok(produtoDetalhamentoDtoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProdutoDetalhamentoDto> exluir(@PathVariable @NotNull Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
