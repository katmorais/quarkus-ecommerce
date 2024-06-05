package br.unitins.topicos2.dto;

import java.util.List;
import java.util.stream.Collectors;
import br.unitins.topicos2.model.Camiseta;
import br.unitins.topicos2.model.Tamanho;

public record CamisetaResponseDTO(
    Long id,
    String nome,
    String descricao,
    Integer estoque,
    Double preco,
    String estampa,
    String tecido,
    Tamanho tamanho,
    FornecedorResponseDTO fornecedor,
    TipoCamisetaResponseDTO tipoCamiseta,
    MarcaResponseDTO marca,
    List<CorResponseDTO> cores,
    String nomeImagem
) {

    public static CamisetaResponseDTO valueOf(Camiseta camiseta) {
        List<CorResponseDTO> cor = camiseta.getCor()
                .stream()
                .map(CorResponseDTO::valueOf)
                .collect(Collectors.toList());
        return new CamisetaResponseDTO(
                camiseta.getId(),
                camiseta.getNome(),
                camiseta.getDescricao(),
                camiseta.getEstoque(),
                camiseta.getPreco(),
                camiseta.getEstampa(),
                camiseta.getTecido(),
                camiseta.getTamanho(),
                FornecedorResponseDTO.valueOf(camiseta.getFornecedor()),
                TipoCamisetaResponseDTO.valueOf(camiseta.getTipoCamiseta()),
                MarcaResponseDTO.valueOf(camiseta.getMarca()),
                cor,
                camiseta.getNomeImagem()
        );
    }
}
