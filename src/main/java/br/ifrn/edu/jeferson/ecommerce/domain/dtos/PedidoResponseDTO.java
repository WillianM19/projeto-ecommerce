package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import br.ifrn.edu.jeferson.ecommerce.domain.enums.StatusPedido;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para resposta de pedido")
public class PedidoResponseDTO {
    @Schema(description = "ID do pedido")
    private Long id;

    @Schema(description = "Data do pedido")
    private LocalDateTime dataPedido;

    @Schema(description = "Valor total do pedido")
    private BigDecimal valorTotal;

    @Schema(description = "Status do pedido")
    private StatusPedido status;

    @Schema(description = "ID do cliente do pedido")
    private Long clienteId;

    @Schema(description = "Itens do pedido")
    private List<ItemPedidoResponseDTO> itens;
}