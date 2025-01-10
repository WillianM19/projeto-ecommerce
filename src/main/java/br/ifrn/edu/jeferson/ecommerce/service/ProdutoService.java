package br.ifrn.edu.jeferson.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifrn.edu.jeferson.ecommerce.domain.Produto;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.exception.ResourceNotFoundException;
import br.ifrn.edu.jeferson.ecommerce.mapper.ProdutoMapper;
import br.ifrn.edu.jeferson.ecommerce.repository.ProdutoRepository;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoMapper produtoMapper;

    public ProdutoResponseDTO salvar(ProdutoRequestDTO produtoDto) {
        var produto =  produtoMapper.toEntity(produtoDto);
        produtoRepository.save(produto);
        return produtoMapper.toResponseDTO(produto);
    }

    public List<ProdutoResponseDTO> lista(){
        List<Produto> produtos = produtoRepository.findAll();
        return produtoMapper.toDTOList(produtos);
    }

    public void deletar(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Produto não encontrado");
        }
        produtoRepository.deleteById(id);
    }

    public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO produtoDto) {
        Produto produto = produtoRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Produto não encontrado"));

        produtoMapper.updateEntityFromDTO(produtoDto, produto);
        var produtoAlterado = produtoRepository.save(produto);

        return produtoMapper.toResponseDTO(produtoAlterado);
    }

    public ProdutoResponseDTO buscarPorId(Long id) {
        Produto produto = produtoRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Produto não encontrado"));
        return produtoMapper.toResponseDTO(produto);
    }

    public ProdutoResponseDTO atualizarEstoque(Long id, Integer quantidade) {
        Produto produto = produtoRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Produto não encontrado"));
        produto.setEstoque(quantidade);
        var produtoAlterado = produtoRepository.save(produto);

        return produtoMapper.toResponseDTO(produtoAlterado);
    }

    public List<ProdutoResponseDTO> buscarPorCategoria(Long id) {
        List<Produto> produtos = produtoRepository.findByCategorias_Id(id);
        return produtoMapper.toDTOList(produtos);
    }
}