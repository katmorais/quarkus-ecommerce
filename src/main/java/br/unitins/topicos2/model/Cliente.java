package br.unitins.topicos2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Cliente extends DefaultEntity {

    @OneToOne
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id", unique = true)
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "id_cidade")
    private Cidade naturalidade;

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

}
