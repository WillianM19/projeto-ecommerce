package br.ifrn.edu.jeferson.ecommerce.service;

import br.ifrn.edu.jeferson.ecommerce.domain.Cliente;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ClienteRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ClienteResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.exception.BusinessException;
import br.ifrn.edu.jeferson.ecommerce.mapper.CategoriaMapper;
import br.ifrn.edu.jeferson.ecommerce.mapper.ClienteMapper;
import br.ifrn.edu.jeferson.ecommerce.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper mapper;


    public ClienteResponseDTO salvar(ClienteRequestDTO clienteRequestDTO) {

        var cliente = mapper.toEntity(clienteRequestDTO);

        if (clienteRepository.existsByCpf(cliente.getCpf())) {
            throw new BusinessException("Já existe um cliente com esse CPF");
        }

        clienteRepository.save(cliente);

        return mapper.ToResponseDTO(cliente);
    }


}
