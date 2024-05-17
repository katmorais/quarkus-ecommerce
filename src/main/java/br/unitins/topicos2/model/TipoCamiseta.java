package br.unitins.topicos2.model;

import java.util.List;

import br.unitins.topicos2.dto.TipoCamisetaDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class TipoCamiseta extends DefaultEntity{
  
    private String nome;

    public static TipoCamiseta of(TipoCamisetaDTO dto) {
        TipoCamiseta tipoCamiseta = new TipoCamiseta();
        tipoCamiseta.setNome(dto.nome());
        return tipoCamiseta;
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

    public List<Camiseta> getCamisetas() {
        return camisetas;
    }

    public void setCamisetas(List<Camiseta> camisetas) {
        this.camisetas = camisetas;
    }

   
    
}
