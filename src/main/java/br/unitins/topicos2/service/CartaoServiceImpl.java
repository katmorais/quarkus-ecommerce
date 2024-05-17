package br.unitins.topicos2.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import br.unitins.topicos2.dto.CartaoDTO;
import br.unitins.topicos2.dto.CartaoResponseDTO;
import br.unitins.topicos2.model.Cartao;
import br.unitins.topicos2.repository.CartaoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class CartaoServiceImpl implements CartaoService {

    @Inject
    CartaoRepository cartaoRepository;

    @Inject
    Validator validator;

    @Override
    public List<CartaoResponseDTO> getAll(int page, int pageSize) {

        List<Cartao> list = cartaoRepository.findAll().page(page, pageSize).list();
        return list.stream().map(e -> CartaoResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public CartaoResponseDTO findById(Long id) {
        Cartao cartao = cartaoRepository.findById(id);
        if (cartao == null)
            throw new NotFoundException("Cartao não encontrado.");
        return CartaoResponseDTO.valueOf(cartao);
    }

    @Override
    @Transactional
    public CartaoResponseDTO create(@Valid CartaoDTO cartaoDTO) throws ConstraintViolationException {
        // validar(cartaoDTO);

        Cartao entity = new Cartao();
        entity.setNome(cartaoDTO.nome());
        entity.setNumeroCartao(cartaoDTO.numeroCartao());
        entity.setDataVencimento(cartaoDTO.dataVencimento());

        cartaoRepository.persist(entity);

        return CartaoResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public CartaoResponseDTO update(Long id, CartaoDTO cartaoDTO) throws ConstraintViolationException {
        validar(cartaoDTO);

        Cartao entity = cartaoRepository.findById(id);

        entity.setNome(cartaoDTO.nome());
        entity.setNumeroCartao(cartaoDTO.numeroCartao());
        entity.setDataVencimento(cartaoDTO.dataVencimento());

        return CartaoResponseDTO.valueOf(entity);
    }

    private void validar(CartaoDTO cartaoDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<CartaoDTO>> violations = validator.validate(cartaoDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        cartaoRepository.deleteById(id);
    }

    @Override
    public List<CartaoResponseDTO> findByNome(String nome, int page, int pageSize) {
        List<Cartao> list = cartaoRepository.findByNome(nome).page(page, pageSize).list();
        return list.stream().map(e -> CartaoResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return cartaoRepository.count();
    }

    @Override
    public long countByNome(String nome) {
        return cartaoRepository.findByNome(nome).count();
    }
}