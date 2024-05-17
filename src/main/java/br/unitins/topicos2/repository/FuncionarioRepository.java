package br.unitins.topicos2.repository;

import br.unitins.topicos2.model.Funcionario;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FuncionarioRepository implements PanacheRepository<Funcionario> {

    public PanacheQuery<Funcionario> findByNome(String nome) {
        if (nome == null)
            return null;
        return find("SELECT p FROM Funcionario p WHERE UPPER(p.pessoa.nome) LIKE ?1 ", "%" + nome.toUpperCase() + "%");
    }

}
