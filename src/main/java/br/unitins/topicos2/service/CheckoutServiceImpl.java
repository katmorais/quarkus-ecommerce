package br.unitins.topicos2.service;

import br.unitins.topicos2.dto.CheckoutDTO;
import br.unitins.topicos2.model.Checkout;
import br.unitins.topicos2.repository.CheckoutRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

@ApplicationScoped
public class CheckoutServiceImpl implements CheckoutService {

    @Inject
    CheckoutRepository repository;

    @Inject
    ClienteService clienteService;

//    @Inject
//    Validator validator;

//    @Override
//    public CheckoutResponseDTO findById(Long id) {
//        Checkout checkout = repository.findById(id);
//        if (nonNull(checkout)) {
//            throw new NotFoundException("Checkout não encontrado.");
//        }
//        return CheckoutResponseDTO.valueOf(checkout);
//    }

    @Override
    @Transactional
    public void create(@Valid CheckoutDTO dto) throws ConstraintViolationException {
        // validar(dto);

        Checkout entity = new Checkout();
        entity.setAddress(dto.address());
        entity.setCardNumber(dto.cardNumber());
        entity.setExpiryDate(dto.expiryDate());
        entity.setCvv(dto.cvv());
        entity.setCliente(clienteService.findById(dto.idCliente()));

        repository.persist(entity);
    }
}