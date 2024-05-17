package br.unitins.topicos2.dto;


public record TransportadoraDTO(
     String nome,
     Integer capacidade,
     String estadoServico,
     Double tarifa,
     Long codigoArea,
     Long numeroTelefone
) {
    
}
