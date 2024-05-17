package br.unitins.topicos2.model;

import jakarta.persistence.Entity;

@Entity
public class Produto extends DefaultEntity {

    private String nome;
    private String descricao;
    private Double valorUnidade;
    private String nomeImagem;
    private Integer quantidadeEstoque;

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Double getValorUnidade() {
        return valorUnidade;
    }
    public void setValorUnidade(Double valorUnidade) {
        this.valorUnidade = valorUnidade;
    }
    public String getNomeImagem() {
        return nomeImagem;
    }
    public void setNomeImagem(String nomeImagem) {
        this.nomeImagem = nomeImagem;
    }
    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }
    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    
}