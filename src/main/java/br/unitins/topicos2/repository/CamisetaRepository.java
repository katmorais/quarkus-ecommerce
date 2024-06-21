package br.unitins.topicos2.repository;

import br.unitins.topicos2.model.Camiseta;
import br.unitins.topicos2.model.Sexo;
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

    public PanacheQuery<Camiseta> findByCategoria(String categoria) {
        if (categoria == null) return null;

        return find("tipoCamiseta.nome", categoria);
    }

    public PanacheQuery<Camiseta> findBySexo(Sexo sexo) {
        if (sexo == null) return null;

        return find("sexo", sexo);
    }
}


