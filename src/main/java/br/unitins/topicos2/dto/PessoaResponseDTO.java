package br.unitins.topicos2.dto;

import java.util.List;
import br.unitins.topicos2.model.Pessoa;

public record PessoaResponseDTO (
    Long id,
    String nome,
    List<EnderecoResponseDTO> listaEndereco,
    List<TelefoneResponseDTO> listaTelefone
) {
    public static PessoaResponseDTO valueOf(Pessoa pessoa){
        if (pessoa == null)
            return null;

        return new PessoaResponseDTO(
            pessoa.getId(), 
            pessoa.getNome(),
            pessoa.getListaEndereco().stream()
                .map(EnderecoResponseDTO::valueOf).toList(),
            pessoa.getListaTelefone().stream()
                .map(TelefoneResponseDTO::valueOf).toList()
        );
    }
}