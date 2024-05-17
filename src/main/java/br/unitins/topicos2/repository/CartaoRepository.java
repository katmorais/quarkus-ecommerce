package br.unitins.topicos2.repository;

import br.unitins.topicos2.model.Cartao;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class CartaoRepository implements PanacheRepository<Cartao>{
    public PanacheQuery<Cartao> findByNome(String nome){
        if(nome == null)
            return null;
        return find("UPPER(cep) LIKE ?1 ", "%" + nome.toUpperCase() + "%");
    }
}
