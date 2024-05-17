package br.unitins.topicos2.repository;

import br.unitins.topicos2.model.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {

    public PanacheQuery<Usuario> findByUsername(String username) {
        if (username == null)
            return null;

        return find("username = ?1 ", username);
    }

}
