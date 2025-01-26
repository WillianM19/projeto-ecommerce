package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponseDTO {
    @Schema(description = "ID do cliente", example = "1")
    private Long id;

    @Schema(description = "Nome do cliente", example = "willian")
    private String nome;

    @Schema(description = "CPF", example = "000.000.000-00")
    private String cpf;

    @Schema(description = "E-mail", example = "willian@gmail.com")
    private String email;

    @Schema(description = "Telefone", example = "(84) 99999-9999")
    private String telefone;
}
