package br.ifrn.edu.jeferson.ecommerce.service;

import br.ifrn.edu.jeferson.ecommerce.domain.Cliente;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ClienteRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ClienteResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.exception.BusinessException;
import br.ifrn.edu.jeferson.ecommerce.mapper.ClienteMapper;
import br.ifrn.edu.jeferson.ecommerce.repository.ClienteRepository;
import br.ifrn.edu.jeferson.ecommerce.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper mapper;

    // Salvar um novo cliente
    public ClienteResponseDTO salvar(ClienteRequestDTO clienteRequestDTO) {
        var cliente = mapper.toEntity(clienteRequestDTO);

        if (clienteRepository.existsByCpf(cliente.getCpf())) {
            throw new BusinessException("Já existe um cliente com esse CPF");
        }

        clienteRepository.save(cliente);
        return mapper.ToResponseDTO(cliente);
    }

    // Buscar cliente por ID
    public ClienteResponseDTO buscarPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Cliente não encontrado para o ID: " + id));
        return mapper.ToResponseDTO(cliente);
    }

    // Listar todos os clientes
    public List<ClienteResponseDTO> listarTodos() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(mapper::ToResponseDTO)
                .collect(Collectors.toList());
    }

    // Excluir cliente por ID
    public void deletar(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new BusinessException("Cliente não encontrado para o ID: " + id);
        }
        clienteRepository.deleteById(id);
    }

    public ClienteResponseDTO atualizar(Long id, ClienteRequestDTO clienteRequestDTO) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Cliente não encontrado"));

        // Atualiza os dados do cliente com os dados recebidos no clienteRequestDTO
        cliente.setNome(clienteRequestDTO.getNome());
        cliente.setEmail(clienteRequestDTO.getEmail());
        cliente.setCpf(clienteRequestDTO.getCpf());
        cliente.setTelefone(clienteRequestDTO.getTelefone());

        // Salva o cliente atualizado
        clienteRepository.save(cliente);

        // Retorna o DTO com os dados atualizados
        return mapper.ToResponseDTO(cliente);
    }

}
