package br.unitins.topicos2.model;

import java.util.List;

import br.unitins.topicos2.dto.MarcaDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Marca extends DefaultEntity{
    private String nome;
    private String descricao;

    public static Marca of(MarcaDTO dto) {
        Marca marca = new Marca();
        marca.setNome(dto.nome());
        marca.setDescricao(dto.descricao());
        return marca;
    }

    @OneToMany
    (cascade = CascadeType.ALL)
    private List<Camiseta> camisetas;


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
    
}
