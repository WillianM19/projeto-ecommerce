package br.ifrn.edu.jeferson.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.ifrn.edu.jeferson.ecommerce.domain.dtos.AtualizarStatusRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.PedidoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.PedidoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.cache.annotation.Cacheable;

@Controller
@RequestMapping("/api/pedidos")
@Tag(name = "Pedidos", description = "API de gerenciamento de pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Operation(summary = "Criar um novo pedido")
    @CacheEvict(value = "pedidos", allEntries = true)
    @PostMapping
    public ResponseEntity<PedidoResponseDTO> salvar(@RequestBody PedidoRequestDTO pedidoDto) {
        return ResponseEntity.ok(pedidoService.salvar(pedidoDto));
    }

    @Operation(summary = "Listar pedidos")
    @Cacheable(value = "pedidos", key = "#pageable.pageNumber + '-' + #pageable.pageSize")
    @GetMapping
    public ResponseEntity<Page<PedidoResponseDTO>> listar(
            Pageable pageable
    ) {
        return ResponseEntity.ok(pedidoService.lista(pageable));
    }

    @Operation(summary = "Deletar um pedido")
    @CacheEvict(value = "pedidos", allEntries = true)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pedidoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualizar status do pedido")
    @CacheEvict(value = "pedidos", allEntries = true)
    @PutMapping("/{id}/status")
    public ResponseEntity<PedidoResponseDTO> atualizarStatusPedido(@PathVariable Long id, @RequestBody AtualizarStatusRequestDTO statusPedidoDto) {
        return ResponseEntity.ok(pedidoService.atualizarStatusPedido(id, statusPedidoDto.getStatusPedido()));
    }

    @Operation(summary = "Buscar pedido por id")
    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.buscarPorId(id));
    }

    @Operation(summary = "Listar pedidos do cliente")
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<PedidoResponseDTO>> listarPedidosPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(pedidoService.listarPedidosPorCliente(clienteId));
    }
}