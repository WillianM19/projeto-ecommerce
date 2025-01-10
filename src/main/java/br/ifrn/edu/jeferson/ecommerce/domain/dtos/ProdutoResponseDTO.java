package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para resposta de produto")
public class ProdutoResponseDTO {
    @Schema(description = "ID do produto")
    private Long id;

    @Schema(description = "Nome")
    private String nome;

    @Schema(description = "Descrição")
    private String descricao;

    @Schema(description = "Valor")
    private String preco;

    @Schema(description = "Quantidade em estoque")
    private Integer estoque;

    @Schema(description = "Categorias")
    private List<CategoriaResponseDTO> categorias;
}