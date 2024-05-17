package br.unitins.topicos2.repository;

import java.util.List;

import br.unitins.topicos2.model.Cidade;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CidadeRepository implements PanacheRepository<Cidade> {

    public List<Cidade> findByNome(String nome) {
        if (nome == null)
            return null;
        return find("UPPER(nome) LIKE ?1 ", "%" + nome.toUpperCase() + "%").list();
    }

    public List<Cidade> findAll2() {
        return find("SELECT c FROM Cidade c ORDER BY c.nome ").list();
    }

}
