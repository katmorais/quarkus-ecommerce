package br.unitins.topicos2.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import br.unitins.topicos2.dto.FornecedorDTO;
import br.unitins.topicos2.dto.FornecedorResponseDTO;
import br.unitins.topicos2.model.Fornecedor;
import br.unitins.topicos2.repository.EnderecoRepository;
import br.unitins.topicos2.repository.FornecedorRepository;
import br.unitins.topicos2.repository.TelefoneRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class FornecedorServiceImpl implements FornecedorService {

    @Inject
    FornecedorRepository fornecedorRepository;

    @Inject
    TelefoneRepository telefoneRepository;

    @Inject
    EnderecoRepository enderecoRepository;

    @Inject
    Validator validator;

    @Override
    public List<FornecedorResponseDTO> getAll(int page, int pageSize) {

        List<Fornecedor> list = fornecedorRepository.findAll().page(page, pageSize).list();
        return list.stream().map(e -> FornecedorResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public FornecedorResponseDTO findById(Long id) {
        Fornecedor fornecedor = fornecedorRepository.findById(id);
        if (fornecedor == null)
            throw new NotFoundException("Fornecedor não encontrado.");
        return FornecedorResponseDTO.valueOf(fornecedor);
    }

    @Override
    @Transactional
    public FornecedorResponseDTO create(@Valid FornecedorDTO fornecedorDTO) throws ConstraintViolationException {
        // validar(fornecedorDTO);
        Fornecedor entity = new Fornecedor();
        entity.setNome(fornecedorDTO.nome());
        entity.setDataContrato(fornecedorDTO.dataContrato());

        fornecedorRepository.persist(entity);

        return FornecedorResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public FornecedorResponseDTO update(Long id, FornecedorDTO fornecedorDTO) throws ConstraintViolationException {
        validar(fornecedorDTO);

        Fornecedor entity = fornecedorRepository.findById(id);

        entity.setNome(fornecedorDTO.nome());
        entity.setDataContrato(fornecedorDTO.dataContrato());

        return FornecedorResponseDTO.valueOf(entity);
    }

    private void validar(FornecedorDTO fornecedorDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<FornecedorDTO>> violations = validator.validate(fornecedorDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        fornecedorRepository.deleteById(id);
    }

    @Override
    public List<FornecedorResponseDTO> findByNome(String nome, int page, int pageSize) {
        List<Fornecedor> list = fornecedorRepository.findByNome(nome).page(page, pageSize).list();
        return list.stream().map(e -> FornecedorResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return fornecedorRepository.count();
    }

    @Override
    public long countByNome(String nome) {
        return fornecedorRepository.findByNome(nome).count();
    }
}