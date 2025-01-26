package br.ifrn.edu.jeferson.ecommerce.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@RequestMapping("/api/produtos")
@Tag(name = "Produtos", description = "API de gerenciamento de produtos")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @Operation(summary = "Criar um novo produto")
    @CacheEvict(value = "produtos", allEntries = true)
    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> salvar(@RequestBody ProdutoRequestDTO produtoDto) {
        return ResponseEntity.ok(produtoService.salvar(produtoDto));
    }

    @Operation(summary = "Listar produtos")
    @GetMapping
    @Cacheable(value = "produtos", key = "#nome + '-' + #precoMaiorQue + '-' + #precoMenorQue + '-' + #pageable.pageNumber + '-' + #pageable.pageSize")
    public ResponseEntity<Page<ProdutoResponseDTO>> lista(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) BigDecimal precoMaiorQue,
            @RequestParam(required = false) BigDecimal precoMenorQue,
            Pageable pageable
    ) {
        return ResponseEntity.ok(produtoService.lista(pageable, nome, precoMaiorQue, precoMenorQue));
    }

    @Operation(summary = "Buscar produto por id")
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarPorId(Long id) {
        return ResponseEntity.ok(produtoService.buscarPorId(id));
    }

    @Operation(summary = "Deletar produto")
    @CacheEvict(value = "produtos", allEntries = true)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(Long id) {
        produtoService.deletar(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Atualizar produto")
    @CacheEvict(value = "produtos", allEntries = true)
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizar(Long id, @RequestBody ProdutoRequestDTO produtoDto) {
        return ResponseEntity.ok(produtoService.atualizar(id, produtoDto));
    }

    @Operation(summary = "Atualizar estoque")
    @PatchMapping("/{id}/estoque")
    public ResponseEntity<ProdutoResponseDTO> atualizarEstoque(Long id, Integer quantidade) {
        return ResponseEntity.ok(produtoService.atualizarEstoque(id, quantidade));
    }

    @Operation(summary = "Listar por categoria")
    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<ProdutoResponseDTO>> buscarPorCategoria(Long categoriaId) {
        return ResponseEntity.ok(produtoService.buscarPorCategoria(categoriaId));
    }
}