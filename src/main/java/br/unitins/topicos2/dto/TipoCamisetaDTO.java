package br.unitins.topicos2.dto;

import br.unitins.topicos2.model.TipoCamiseta;

public record TipoCamisetaDTO(
        String nome
        
        ) {

    public static TipoCamisetaDTO valueOf(TipoCamiseta tipoCamiseta) {
        return new TipoCamisetaDTO(
                tipoCamiseta.getNome()

        );
    }
}
