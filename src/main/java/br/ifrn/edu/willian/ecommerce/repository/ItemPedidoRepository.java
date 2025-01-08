package br.ifrn.edu.willian.ecommerce.repository;

import br.ifrn.edu.willian.ecommerce.domain.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
}
