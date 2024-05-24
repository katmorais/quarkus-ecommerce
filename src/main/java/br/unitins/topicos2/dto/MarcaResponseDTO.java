package br.unitins.topicos2.dto;

import br.unitins.topicos2.model.Marca;

public record MarcaResponseDTO(

        Long id,
        String nome,
        String descricao

) {

    public static MarcaResponseDTO valueOf(Marca marca) {
        return new MarcaResponseDTO(
                marca.getId(),
                marca.getNome(),
                marca.getDescricao());

    }

}
