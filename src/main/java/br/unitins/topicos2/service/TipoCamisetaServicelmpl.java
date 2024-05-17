package br.unitins.topicos2.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import br.unitins.topicos2.dto.TipoCamisetaDTO;
import br.unitins.topicos2.dto.TipoCamisetaResponseDTO;
import br.unitins.topicos2.model.TipoCamiseta;
import br.unitins.topicos2.repository.TipoCamisetaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class TipoCamisetaServicelmpl implements TipoCamisetaService {

    @Inject
    TipoCamisetaRepository tipocamisetaRepository;

    @Inject
    Validator validator;

    @Override
    public List<TipoCamisetaResponseDTO> getAll(int page, int pageSize) {

        List<TipoCamiseta> list = tipocamisetaRepository.findAll().page(page, pageSize).list();
        return list.stream().map(e -> TipoCamisetaResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public TipoCamisetaResponseDTO findById(Long id) {
        TipoCamiseta tipocamiseta = tipocamisetaRepository.findById(id);
        if (tipocamiseta == null)
            throw new NotFoundException("TipoCamiseta não encontrado.");
        return TipoCamisetaResponseDTO.valueOf(tipocamiseta);
    }

    @Override
    @Transactional
    public TipoCamisetaResponseDTO create(@Valid TipoCamisetaDTO tipocamisetaDTO) throws ConstraintViolationException {
        // validar(tipocamisetaDTO);

        TipoCamiseta entity = new TipoCamiseta();

        entity.setNome(tipocamisetaDTO.nome());

        tipocamisetaRepository.persist(entity);

        return TipoCamisetaResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public TipoCamisetaResponseDTO update(Long id, TipoCamisetaDTO tipocamisetaDTO)
            throws ConstraintViolationException {
        validar(tipocamisetaDTO);

        TipoCamiseta entity = tipocamisetaRepository.findById(id);

        entity.setNome(tipocamisetaDTO.nome());

        return TipoCamisetaResponseDTO.valueOf(entity);
    }

    private void validar(TipoCamisetaDTO tipocamisetaDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<TipoCamisetaDTO>> violations = validator.validate(tipocamisetaDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        tipocamisetaRepository.deleteById(id);
    }

    @Override
    public List<TipoCamisetaResponseDTO> findByNome(String nome, int page, int pageSize) {
        List<TipoCamiseta> list = tipocamisetaRepository.findByNome(nome).page(page, pageSize).list();
        return list.stream().map(e -> TipoCamisetaResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return tipocamisetaRepository.count();
    }

    @Override
    public long countByNome(String nome) {
        return tipocamisetaRepository.findByNome(nome).count();
    }

}
