package br.unitins.topicos2.model;

import jakarta.persistence.Entity;

@Entity
public class Cor extends DefaultEntity {
    private String nome;

    protected Cor() {
    }

    public Cor(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
