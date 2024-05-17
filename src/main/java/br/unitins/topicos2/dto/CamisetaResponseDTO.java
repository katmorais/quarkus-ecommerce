package br.unitins.topicos2.dto;

import java.util.List;

import br.unitins.topicos2.model.Camiseta;

public record CamisetaResponseDTO(
        Long id,
        String nome,
        String descricao,
        Integer estoque,
        Double preco,
        String estampa,
        String tecido,
        FornecedorResponseDTO fornecedor,
        TipoCamisetaResponseDTO tipoCamiseta,
        MarcaResponseDTO marca,
        List<CorResponseDTO> cores

) {

    public static CamisetaResponseDTO valueOf(Camiseta camiseta) {
        List<CorResponseDTO> cor = camiseta.getCor()
        .stream()
        .map(CorResponseDTO::valueOf)
        .toList();
        return new CamisetaResponseDTO(
                camiseta.getId(),
                camiseta.getNome(),
                camiseta.getDescricao(),
                camiseta.getEstoque(),
                camiseta.getPreco(),
                camiseta.getEstampa(),
                camiseta.getTecido(),
                FornecedorResponseDTO.valueOf(camiseta.getFornecedor()),
                TipoCamisetaResponseDTO.valueOf(camiseta.getTipoCamiseta()),
                MarcaResponseDTO.valueOf(camiseta.getMarca()),
                cor);
                
               

    }
}
