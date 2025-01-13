package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para resposta de item de pedido")
public class ItemPedidoResponseDTO {
    @Schema(description = "Quantidade")
    private Integer quantidade;

    @Schema(description = "Produto do item")
    private ItemPartialResponseDTO produto;
}