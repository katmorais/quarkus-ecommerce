package br.unitins.topicos2.model;

import br.unitins.topicos2.dto.CorDTO;
import jakarta.persistence.Entity;

@Entity
public class Cor extends DefaultEntity {
    private String nome;

    public static Cor of(CorDTO dto) {
        Cor cor = new Cor();
        cor.setNome(dto.nome());
        return cor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
