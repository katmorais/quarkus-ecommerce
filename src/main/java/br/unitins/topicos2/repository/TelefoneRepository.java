package br.unitins.topicos2.repository;

import br.unitins.topicos2.model.Telefone;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TelefoneRepository implements PanacheRepository<Telefone>{

    public PanacheQuery<Telefone> findByNome(String numero) {
        if (numero == null)
            return null;
        return find("UPPER(nome) LIKE ?1 ", "%" + numero.toUpperCase() + "%");
    }
}