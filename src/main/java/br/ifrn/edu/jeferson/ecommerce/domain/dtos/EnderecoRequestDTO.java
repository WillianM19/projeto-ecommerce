package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para requisição de endereço")
public class EnderecoRequestDTO {
    @Schema(description = "Rua")
    @NotBlank(message = "Rua é obrigatória")
    private String rua;

    @Schema(description = "Número da casa")
    @NotBlank(message = "Número é obrigatório")
    private String numero;

    @Schema(description = "Bairro")
    @NotBlank(message = "Bairro é obrigatório")
    private String bairro;

    @Schema(description = "Cidade")
    @NotBlank(message = "Cidade é obrigatória")
    private String cidade;

    @Schema(description = "Estado")
    @NotBlank(message = "Estado é obrigatório")
    private String estado;

    @Schema(description = "CEP")
    @NotBlank(message = "CEP é obrigatório")
    private String cep;
}