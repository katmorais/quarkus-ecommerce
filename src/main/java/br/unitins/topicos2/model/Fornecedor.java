package br.unitins.topicos2.model;

import jakarta.persistence.Entity;

@Entity
public class Fornecedor extends DefaultEntity {

    private String nome;
    private String dataContrato;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataContrato() {
        return dataContrato;
    }

    public void setDataContrato(String dataContrato) {
        this.dataContrato = dataContrato;
    }
}