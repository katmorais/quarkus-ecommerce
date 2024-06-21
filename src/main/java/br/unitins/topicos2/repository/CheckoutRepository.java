package br.unitins.topicos2.repository;

import br.unitins.topicos2.model.Checkout;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;


@ApplicationScoped
public class CheckoutRepository implements PanacheRepository<Checkout>{
    public List<Checkout> findByClienteId(Long id) {
        return list("cliente.id", id);
    }
}
