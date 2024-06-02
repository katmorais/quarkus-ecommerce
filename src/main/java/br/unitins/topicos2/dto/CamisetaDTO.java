package br.unitins.topicos2.dto;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CamisetaDTO (
    @NotBlank(message = "O campo nome deve ser informado.")
    @Size(max = 60, message = "O campo nome deve possuir no máximo 60 caracteres.")
    String nome,
    String descricao,
    Integer estoque,
    Double preco,
    String estampa,
    String tecido,
    Integer tamanho,
 
    @NotNull(message = "O campo idFornecedor deve ser informado.")
    Long idFornecedor,
    @NotNull(message = "O campo idTipoCamiseta deve ser informado.")
    Long idTipoCamiseta,
    @NotNull(message = "O campo idTipoCamiseta deve ser informado.")
    Long idMarca,

    List<CorDTO> cores
    
){
    // Construtor para inicializar a lista cores
    public CamisetaDTO {
        if (cores == null) {
            cores = new ArrayList<>();
        }
    }
}
