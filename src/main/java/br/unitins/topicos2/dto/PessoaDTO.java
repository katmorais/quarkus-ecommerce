package br.unitins.topicos2.dto;

import java.util.List;

public record PessoaDTO(
        Long id,
        String nome,
        String login,
        List<TelefoneDTO> listaTelefone,
        List<EnderecoDTO> listaEndereco,
        List<CartaoDTO> cartoes
) {
}
