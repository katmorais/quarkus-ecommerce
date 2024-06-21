package br.unitins.topicos2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CamisetaDTO(
        @NotBlank(message = "O campo nome deve ser informado.")
        @Size(max = 60, message = "O campo nome deve possuir no máximo 60 caracteres.")
        String nome,
        String descricao,
        Integer estoque,
        Double preco,
        String estampa,
        String tecido,
        Integer tamanho,
        Integer sexo,

        @NotNull(message = "O campo idFornecedor deve ser informado.")
        Long fornecedor,

        @NotNull(message = "O campo idTipoCamiseta deve ser informado.")
        Long tipoCamiseta,

        @NotNull(message = "O campo idTipoCamiseta deve ser informado.")
        Long marca,

        @NotNull(message = "O campo cores deve ser informado.")
        List<String> cores

) {
}
