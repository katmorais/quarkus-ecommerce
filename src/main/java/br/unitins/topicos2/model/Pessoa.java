package br.unitins.topicos2.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Pessoa extends DefaultEntity {
    @Column(unique = true)
    private String cpf;
    private String nome;
    private LocalDate dataNascimento;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", unique = true)
    private Usuario usuario;

     @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "usuario_cartao", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_cartao"))
    private List<Cartao> cartoes = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "usuario_endereco", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_endereco"))
    private List<Endereco> listaEndereco = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "usuario_telefone", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_telefone"))
    private List<Telefone> listaTelefone = new ArrayList<>();

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public List<Cartao> getCartoes() {
        return cartoes;
    }
    public void setCartoes(List<Cartao> cartoes) {
        this.cartoes = cartoes;
    }
    public List<Endereco> getListaEndereco() {
        return listaEndereco;
    }
    public void setListaEndereco(List<Endereco> listaEndereco) {
        this.listaEndereco = listaEndereco;
    }
    public List<Telefone> getListaTelefone() {
        return listaTelefone;
    }
    public void setListaTelefone(List<Telefone> listaTelefone) {
        this.listaTelefone = listaTelefone;
    }

    
}
