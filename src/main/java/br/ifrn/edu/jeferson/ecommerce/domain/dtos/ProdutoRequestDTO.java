package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para requisição de produto")
public class ProdutoRequestDTO {
    @Schema(description = "Nome")
    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @Schema(description = "Descrição")
    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;

    @Schema(description = "Valor")
    @NotNull(message = "O valor é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "O preço não pode ser negativo")
    @Digits(integer = Integer.MAX_VALUE, fraction = 2, message = "O limite de preço foi excedido")
    private BigDecimal preco;

    @Schema(description = "Quantidade em estoque")
    @NotNull(message = "A quantidade no estoque é obrigatória")
    @Min(value = 0, message = "Estoque não pode ser negativo")
    private Integer estoque;
}