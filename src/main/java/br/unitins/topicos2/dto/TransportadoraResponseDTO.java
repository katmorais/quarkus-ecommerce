package br.unitins.topicos2.dto;

import br.unitins.topicos2.model.Transportadora;

public record TransportadoraResponseDTO(
    Long id,
    String nome,
    Integer capacidade,
    String estadoServico,
    Double tarifa,
    TelefoneResponseDTO telefone

) {
    public static TransportadoraResponseDTO valueOf(Transportadora transportadora) {
    return new TransportadoraResponseDTO(
        transportadora.getId(),
        transportadora.getNome(),
        transportadora.getCapacidade(),
        transportadora.getEstadoServico(),
        transportadora.getTarifa(),
        TelefoneResponseDTO.valueOf(transportadora.getTelefone()));
    }
    
}