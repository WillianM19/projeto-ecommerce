package br.ifrn.edu.jeferson.ecommerce.service;

import br.ifrn.edu.jeferson.ecommerce.domain.Categoria;
import br.ifrn.edu.jeferson.ecommerce.domain.Cliente;
import br.ifrn.edu.jeferson.ecommerce.domain.Pedido;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.CategoriaResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ClienteRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ClienteResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.PedidoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.exception.BusinessException;
import br.ifrn.edu.jeferson.ecommerce.mapper.ClienteMapper;
import br.ifrn.edu.jeferson.ecommerce.mapper.PedidoMapper;
import br.ifrn.edu.jeferson.ecommerce.repository.ClienteRepository;
import br.ifrn.edu.jeferson.ecommerce.exception.ResourceNotFoundException;
import br.ifrn.edu.jeferson.ecommerce.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    @Autowired
    private PedidoMapper pedidoMapper;

    @Autowired
    private PedidoRepository pedidoRepository;

    private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);

    private void verificarExistenciaDeCpf(String cpf) {
        if (clienteRepository.existsByCpf(cpf)) {
            throw new BusinessException(String.format("Cliente com CPF %s já cadastrado", cpf));
        }
    }

    private void verificarExistenciaDeEmail(String email) {
        if (clienteRepository.existsByEmail(email)) {
            throw new BusinessException(String.format("O email %s já está sendo usado", email));
        }
    }

    private void validaCliente(ClienteRequestDTO clienteDto) {
        verificarExistenciaDeCpf(clienteDto.getCpf());
        verificarExistenciaDeEmail(clienteDto.getEmail());
    }

    // Salvar um novo cliente
    public ClienteResponseDTO salvar(ClienteRequestDTO clienteRequestDTO) {
        logger.info("Salvando uma novo cliente...");
        validaCliente(clienteRequestDTO);
        var cliente = clienteMapper.toEntity(clienteRequestDTO);

        if (clienteRepository.existsByCpf(cliente.getCpf())) {
            throw new BusinessException("Já existe um cliente com esse CPF");
        }

        clienteRepository.save(cliente);
        logger.info("Cliente Salvo! {}", cliente.getId());
        return clienteMapper.toResponseDTO(cliente);
    }

    // Buscar cliente por ID
    public ClienteResponseDTO buscarPorId(Long id) {
        logger.info("Buscando clientes por id: {}", id);
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Cliente não encontrado para o ID: " + id));
        return clienteMapper.toResponseDTO(cliente);
    }

    // Listar todos os clientes
    public Page<ClienteResponseDTO> lista(
            Pageable pageable
    ){
        logger.info("Listando clientes...");
        Page<Cliente> clientes = clienteRepository.findAll(pageable);
        return clienteMapper.toDTOPage(clientes);
    }

    // Excluir cliente por ID
    public void deletar(Long id) {
        logger.info("Deletando cliente: {}", id);
        if (!clienteRepository.existsById(id)) {
            throw new BusinessException("Cliente não encontrado para o ID: " + id);
        }

        List<Pedido> pedidos = pedidoRepository.findByClienteId(id);

        if (!pedidos.isEmpty()) {
            for (Pedido pedido : pedidos) {
                pedido.setCliente(null);
                pedidoRepository.save(pedido);
            }
        }
        logger.info("Cliente Deletado!");
        clienteRepository.deleteById(id);
    }

    public ClienteResponseDTO atualizar(Long id, ClienteRequestDTO clienteDto) {
        logger.info("Atualizando dados do cliente: {}", id);
        Cliente cliente = clienteRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Cliente não encontrado"));
        validaCliente(clienteDto);
        clienteMapper.updateEntityFromDTO(clienteDto, cliente);
        var clienteAlterado = clienteRepository.save(cliente);
        logger.info("Cliente Atualizado!");
        return clienteMapper.toResponseDTO(clienteAlterado);
    }

    public List<PedidoResponseDTO> listarPedidosDoCliente(Long id) {
        logger.info("Listando pedidos do cliente...");
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
        return pedidoMapper.toDTOList(cliente.getPedidos());
    }

}
