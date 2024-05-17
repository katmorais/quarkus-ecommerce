package br.unitins.topicos2.repository;

import br.unitins.topicos2.model.Endereco;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class EnderecoRepository implements PanacheRepository<Endereco>{
    public PanacheQuery<Endereco> findByCep(String cep){
        if(cep == null)
            return null;
        return find("UPPER(cep) LIKE ?1 ", "%" + cep.toUpperCase() + "%");
    }
}
