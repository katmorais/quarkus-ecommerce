package br.unitins.topicos2.dto;

import br.unitins.topicos2.model.Marca;

public record MarcaDTO (
        String nome,
        String descricao
) {
    public static MarcaDTO valueOf(Marca marca){
        return new MarcaDTO(
            marca.getNome(), 
            marca.getDescricao()
        );
    }
    
}
