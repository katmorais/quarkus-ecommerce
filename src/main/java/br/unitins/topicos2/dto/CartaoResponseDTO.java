package br.unitins.topicos2.dto;




import br.unitins.topicos2.model.Cartao;

public record CartaoResponseDTO(
        Long id,
        String nome,
        String numeroCartao,
        String dataVencimento,
        Long pessoa
) {

    public static CartaoResponseDTO valueOf(Cartao cartao) {
        
        return new CartaoResponseDTO(
                cartao.getId(),
                cartao.getNome(),
                cartao.getNumeroCartao(),
                cartao.getDataVencimento(),
                cartao.getPessoa().getId());
    }
}