package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClienteRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    @NotBlank(message = "E-mail é obrigatório")
    private String email;

    @NotBlank(message = "CPF é obrigatório")
    private String cpf;

    private String telefone;

}
