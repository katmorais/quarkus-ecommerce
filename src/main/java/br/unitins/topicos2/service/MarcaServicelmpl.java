package br.unitins.topicos2.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import br.unitins.topicos2.dto.MarcaDTO;
import br.unitins.topicos2.dto.MarcaResponseDTO;
import br.unitins.topicos2.model.Marca;
import br.unitins.topicos2.repository.MarcaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class MarcaServicelmpl implements MarcaService {

    @Inject
    MarcaRepository marcaRepository;

    @Inject
    Validator validator;

    @Override
    public List<MarcaResponseDTO> getAll(int page, int pageSize) {

        List<Marca> list = marcaRepository.findAll().page(page, pageSize).list();
        return list.stream().map(e -> MarcaResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public MarcaResponseDTO findById(Long id) {
        Marca marca = marcaRepository.findById(id);
        if (marca == null)
            throw new NotFoundException("Marca não encontrado.");
        return MarcaResponseDTO.valueOf(marca);
    }

    @Override
    @Transactional
    public MarcaResponseDTO create(@Valid MarcaDTO marcaDTO) throws ConstraintViolationException {
        // validar(marcaDTO);

        Marca entity = new Marca();

        entity.setNome(marcaDTO.nome());
        entity.setDescricao(marcaDTO.descricao());

        marcaRepository.persist(entity);

        return MarcaResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public MarcaResponseDTO update(Long id, MarcaDTO marcaDTO) throws ConstraintViolationException {
        validar(marcaDTO);

        Marca entity = marcaRepository.findById(id);

        entity.setNome(marcaDTO.nome());
        entity.setDescricao(marcaDTO.descricao());

        return MarcaResponseDTO.valueOf(entity);
    }

    private void validar(MarcaDTO marcaDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<MarcaDTO>> violations = validator.validate(marcaDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        marcaRepository.deleteById(id);
    }

    @Override
    public List<MarcaResponseDTO> findByNome(String nome, int page, int pageSize) {
        List<Marca> list = marcaRepository.findByNome(nome).page(page, pageSize).list();
        return list.stream().map(e -> MarcaResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return marcaRepository.count();
    }

    @Override
    public long countByNome(String nome) {
        return marcaRepository.findByNome(nome).count();
    }

}
