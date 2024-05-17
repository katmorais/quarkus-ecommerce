package br.unitins.topicos2.repository;

import br.unitins.topicos2.model.Transportadora;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TransportadoraRepository implements PanacheRepository<Transportadora>{

    public PanacheQuery<Transportadora> findByNome(String numero) {
        if (numero == null)
            return null;
        return find("UPPER(nome) LIKE ?1 ", "%" + numero.toUpperCase() + "%");
    }
}