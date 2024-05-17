package br.unitins.topicos2.repository;

import java.util.List;

import br.unitins.topicos2.model.Camiseta;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CamisetaRepository implements PanacheRepository<Camiseta> {
     public PanacheQuery<Camiseta> findByNome(String nome) {
        if (nome == null)
            return null;
        return find("UPPER(nome) LIKE ?1 ", "%" + nome.toUpperCase() + "%");
    }

       public List<Camiseta> findAll2() {
        return find("SELECT c FROM Camiseta c ORDER BY c.nome ").list();
    }

}


