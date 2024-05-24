package br.unitins.topicos2.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Camiseta extends DefaultEntity{
    private String nome;
    private String descricao;
    private Integer estoque;
    private Double preco;
    private String estampa;
    private String tecido;
    private Tamanho tamanho;
    private String nomeImagem;

    @JoinColumn(name = "id_fornecedor")
    @ManyToOne
    private Fornecedor fornecedor;

    @JoinColumn(name = "id_tipoCamiseta")
    @ManyToOne
    private TipoCamiseta tipoCamiseta;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_marca")
    private Marca marca;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_cor")
    private List<Cor> cor;


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
    public Integer getEstoque() {
        return estoque;
    }
    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }
    public Double getPreco() {
        return preco;
    }
    public void setPreco(Double preco) {
        this.preco = preco;
    }
    public String getEstampa() {
        return estampa;
    }
    public void setEstampa(String estampa) {
        this.estampa = estampa;
    }
    public String getTecido() {
        return tecido;
    }
    public Fornecedor getFornecedor() {
        return fornecedor;
    }
    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
    public void setTecido(String tecido) {
        this.tecido = tecido;
    }
    public Tamanho getTamanho() {
        return tamanho;
    }
    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }
    public List<Cor> getCor() {
        return cor;
    }
    public void setCor(List<Cor> cor) {
        this.cor = cor;
    }
    public Marca getMarca() {
        return marca;
    }
    public void setMarca(Marca marca) {
        this.marca = marca;
    }
    public TipoCamiseta getTipoCamiseta() {
        return tipoCamiseta;
    }
    public void setTipoCamiseta(TipoCamiseta tipoCamiseta) {
        this.tipoCamiseta = tipoCamiseta;
    }
    public String getNomeImagem() {
        return nomeImagem;
    }
    public void setNomeImagem(String nomeImagem) {
        this.nomeImagem = nomeImagem;
    }

    
    
}
