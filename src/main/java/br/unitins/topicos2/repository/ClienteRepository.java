package br.unitins.topicos2.repository;

import br.unitins.topicos2.model.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClienteRepository implements PanacheRepository<Cliente> {

    public PanacheQuery<Cliente> findByNome(String nome) {
        if (nome == null)
            return null;
        return find("SELECT p FROM Cliente p WHERE UPPER(p.pessoa.nome) LIKE ?1 ", "%" + nome.toUpperCase() + "%");
    }

    public PanacheQuery<Cliente> findByUsernameAndSenha(String username, String senha) {
        return find("pessoa.usuario.username = ?1 AND  pessoa.usuario.senha = ?2", username, senha);
    }

}