package br.ifrn.edu.jeferson.ecommerce.controller;

import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ClienteRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ClienteResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.PedidoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Clientes", description = "API de gerenciamento de clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Operation(summary = "Cadastrar um novo cliente")
    @PostMapping
    public ResponseEntity<ClienteResponseDTO> salvar(@RequestBody ClienteRequestDTO clienteDto) {
        return ResponseEntity.ok(clienteService.salvar(clienteDto));
    }

    @Operation(summary = "Buscar cliente por id")
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscarPorId(Long id) {
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    @Operation(summary = "Listar todos os clientes")
    @GetMapping
    public ResponseEntity<Page<ClienteResponseDTO>> lista(
            Pageable pageable
    ) {
        return ResponseEntity.ok(clienteService.lista(pageable));
    }

    @Operation(summary = "Atualizar dados do cliente")
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizar(@PathVariable Long id, @RequestBody ClienteRequestDTO clienteDto) {
        return ResponseEntity.ok(clienteService.atualizar(id, clienteDto));
    }

    @Operation(summary = "Excluir cliente por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        clienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Listar pedidos do cliente")
    @GetMapping("/{id}/pedidos")
    public ResponseEntity<List<PedidoResponseDTO>> listarPedidosDoCliente(Long id) {
        return ResponseEntity.ok(clienteService.listarPedidosDoCliente(id));
    }
}
