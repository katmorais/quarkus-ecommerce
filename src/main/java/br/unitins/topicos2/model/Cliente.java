package br.unitins.topicos2.model;

import jakarta.persistence.*;

@Entity
public class Cliente extends DefaultEntity {

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id", unique = true)
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "id_cidade")
    private Cidade naturalidade;

    private Cargo cargo;

    public Cidade getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(Cidade naturalidade) {
        this.naturalidade = naturalidade;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
}
