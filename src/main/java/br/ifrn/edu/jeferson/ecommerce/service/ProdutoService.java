package br.ifrn.edu.jeferson.ecommerce.service;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import br.ifrn.edu.jeferson.ecommerce.specification.ProdutoSpecification;


import br.ifrn.edu.jeferson.ecommerce.domain.Produto;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.exception.ResourceNotFoundException;
import br.ifrn.edu.jeferson.ecommerce.mapper.ProdutoMapper;
import br.ifrn.edu.jeferson.ecommerce.repository.ProdutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoMapper produtoMapper;

    private static final Logger logger = LoggerFactory.getLogger(ProdutoService.class);

    public ProdutoResponseDTO salvar(ProdutoRequestDTO produtoDto) {
        logger.info("Salvando um novo produto...");
        var produto =  produtoMapper.toEntity(produtoDto);
        produtoRepository.save(produto);
        logger.info("Produto salvo!");
        return produtoMapper.toResponseDTO(produto);
    }

    public Page<ProdutoResponseDTO> lista(
            Pageable pageable,
            String nome,
            BigDecimal precoMaiorQue,
            BigDecimal precoMenorQue
    ){
        logger.info("Listando produtos...");
        Specification<Produto> spec = Specification.where(ProdutoSpecification.comNomeContendo(nome))
                .and(ProdutoSpecification.comPrecoMaiorQue(precoMaiorQue))
                .and(ProdutoSpecification.comPrecoMenorQue(precoMenorQue));
        Page<Produto> produtos = produtoRepository.findAll(spec, pageable);
        return produtoMapper.toDTOPage(produtos);
    }

    public void deletar(Long id) {
        logger.info("Deletando produto: {}", id);
        if (!produtoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Produto não encontrado");
        }
        logger.info("Produto deletado!");
        produtoRepository.deleteById(id);
    }

    public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO produtoDto) {
        logger.info("Atualizando dados do produto: {}", id);
        Produto produto = produtoRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Produto não encontrado"));

        produtoMapper.updateEntityFromDTO(produtoDto, produto);
        var produtoAlterado = produtoRepository.save(produto);

        logger.info("Produto atualizado!");
        return produtoMapper.toResponseDTO(produtoAlterado);
    }

    public ProdutoResponseDTO buscarPorId(Long id) {
        logger.info("Buscando produto por id: {}", id);
        Produto produto = produtoRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Produto não encontrado"));
        return produtoMapper.toResponseDTO(produto);
    }

    public ProdutoResponseDTO atualizarEstoque(Long id, Integer quantidade) {
        logger.info("Atualizando estoque do produto: {}", id);
        Produto produto = produtoRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Produto não encontrado"));
        produto.setEstoque(quantidade);
        var produtoAlterado = produtoRepository.save(produto);
        logger.info("Estoque atualizado!");
        return produtoMapper.toResponseDTO(produtoAlterado);
    }

    public List<ProdutoResponseDTO> buscarPorCategoria(Long id) {
        logger.info("Buscando produtos por categoria de id: {}", id);
        List<Produto> produtos = produtoRepository.findByCategorias_Id(id);
        return produtoMapper.toDTOList(produtos);
    }
}