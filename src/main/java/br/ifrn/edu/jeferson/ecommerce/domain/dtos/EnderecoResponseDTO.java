package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para resposta de endereço")
public class EnderecoResponseDTO {
    @Schema(description = "ID")
    private Long id;

    @Schema(description = "Rua")
    private String rua;

    @Schema(description = "Número da casa")
    private String numero;

    @Schema(description = "Bairro")
    private String bairro;

    @Schema(description = "Cidade")
    private String cidade;

    @Schema(description = "Estado")
    private String estado;

    @Schema(description = "CEP")
    private String cep;
}