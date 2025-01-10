package br.ifrn.edu.jeferson.ecommerce.repository;

import br.ifrn.edu.jeferson.ecommerce.domain.Produto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByCategorias_Id(Long id);
}