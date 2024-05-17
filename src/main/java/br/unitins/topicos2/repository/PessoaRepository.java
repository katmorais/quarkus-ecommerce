package br.unitins.topicos2.repository;

import br.unitins.topicos2.model.Pessoa;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PessoaRepository implements PanacheRepository<Pessoa> {

    public PanacheQuery<Pessoa> findByName(String nome) {
        if (nome == null)
            return null;
        return find("UPPER(nome) LIKE ?1 ", "%" + nome.toUpperCase() + "%");
    }

    public PanacheQuery<Pessoa> findByCpf(String cpf) {
        if (cpf == null)
            return null;
        return find("UPPER(cpf) = ?1 ", cpf );
    }

}