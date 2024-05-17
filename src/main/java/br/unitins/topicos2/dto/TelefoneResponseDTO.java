package br.unitins.topicos2.dto;

import br.unitins.topicos2.model.Telefone;

public record TelefoneResponseDTO(
    Long codigoArea,
    Long numero
) {
    public static TelefoneResponseDTO valueOf(Telefone telefone){
        return new TelefoneResponseDTO(
            telefone.getCodigoArea(), 
            telefone.getNumero()
        );
    }
}