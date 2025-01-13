package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para resposta do item de um produto")
public class ItemPartialResponseDTO {
    private Long id;

    private String nome;

    private BigDecimal preco;
}