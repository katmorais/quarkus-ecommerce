package br.unitins.topicos2.dto;

import br.unitins.topicos2.model.TipoCamiseta;

public record TipoCamisetaResponseDTO(

    Long id,
    String nome

) {
     public static TipoCamisetaResponseDTO valueOf(TipoCamiseta tipocamiseta) {
        return new TipoCamisetaResponseDTO(
                tipocamiseta.getId(),
                tipocamiseta.getNome());
        
    }
}