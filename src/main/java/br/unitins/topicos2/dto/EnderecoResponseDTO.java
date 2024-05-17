package br.unitins.topicos2.dto;

import br.unitins.topicos2.model.Cidade;
import br.unitins.topicos2.model.Endereco;

public record EnderecoResponseDTO(
        Long id,
        String rua,
        String numero,
        String complemento,
        String bairro,
        String cep,
        Cidade cidade) {
            
    public static EnderecoResponseDTO valueOf(Endereco endereco) {
        return new EnderecoResponseDTO(
                endereco.getId(),
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getCep(),
                endereco.getCidade());
    }
}
