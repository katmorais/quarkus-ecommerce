package br.unitins.topicos2.dto;

import br.unitins.topicos2.model.Fornecedor;

public record FornecedorResponseDTO(
        Long id,
        String nome,
        String dataContrato

) {
    public static FornecedorResponseDTO valueOf(Fornecedor fornecedor) {
        return new FornecedorResponseDTO(
                fornecedor.getId(),
                fornecedor.getNome(),
                fornecedor.getDataContrato());
    }

}