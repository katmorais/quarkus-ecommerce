package br.unitins.topicos2.dto;

import br.unitins.topicos2.model.Cor;

public record CorResponseDTO(
        Long id,
        String nome

) {
    public static CorResponseDTO valueOf(Cor cor) {
        return new CorResponseDTO(
                cor.getId(),
                cor.getNome());

    }

}
