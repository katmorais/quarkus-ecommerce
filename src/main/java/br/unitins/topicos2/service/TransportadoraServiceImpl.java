package br.unitins.topicos2.service;

import br.unitins.topicos2.dto.TransportadoraDTO;
import br.unitins.topicos2.dto.TransportadoraResponseDTO;
import br.unitins.topicos2.model.Telefone;
import br.unitins.topicos2.model.Transportadora;
import br.unitins.topicos2.repository.TelefoneRepository;
import br.unitins.topicos2.repository.TransportadoraRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class TransportadoraServiceImpl implements TransportadoraService {

    @Inject
    TransportadoraRepository transportadoraRepository;

    @Inject
    TelefoneRepository telefoneRepository;

    @Inject
    Validator validator;

    @Override
    public List<TransportadoraResponseDTO> getAll(int page, int pageSize) {

        List<Transportadora> list = transportadoraRepository.findAll().page(page, pageSize).list();
        return list.stream().map(TransportadoraResponseDTO::valueOf).toList();
    }

    @Override
    public TransportadoraResponseDTO findById(Long id) {
        Transportadora transportadora = transportadoraRepository.findById(id);
        if (transportadora == null)
            throw new NotFoundException("Transportadora não encontrado.");
        return TransportadoraResponseDTO.valueOf(transportadora);
    }

    @Override
    @Transactional
    public TransportadoraResponseDTO create(@Valid TransportadoraDTO transportadoraDTO)
            throws ConstraintViolationException {
        // validar(transportadoraDTO);

        Transportadora entity = new Transportadora();

        entity.setNome(transportadoraDTO.nome());
        entity.setCapacidade(transportadoraDTO.capacidade());
        entity.setEstadoServico(transportadoraDTO.estadoServico());
        entity.setTarifa(transportadoraDTO.tarifa());
        entity.setTelefone(new Telefone(transportadoraDTO.codigoArea(), transportadoraDTO.numeroTelefone()));
        ;
        transportadoraRepository.persist(entity);

        return TransportadoraResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public TransportadoraResponseDTO update(Long id, TransportadoraDTO transportadoraDTO)
            throws ConstraintViolationException {
        validar(transportadoraDTO);

        Transportadora entity = transportadoraRepository.findById(id);

        entity.setNome(transportadoraDTO.nome());
        entity.setCapacidade(transportadoraDTO.capacidade());
        entity.setEstadoServico(transportadoraDTO.estadoServico());
        entity.setTarifa(transportadoraDTO.tarifa());

        if (!Objects.equals(entity.getTelefone().getNumero(), transportadoraDTO.numeroTelefone())) {
            entity.setTelefone(new Telefone(transportadoraDTO.numeroTelefone(), transportadoraDTO.codigoArea()));
        }

        return TransportadoraResponseDTO.valueOf(entity);
    }

    private void validar(TransportadoraDTO transportadoraDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<TransportadoraDTO>> violations = validator.validate(transportadoraDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        transportadoraRepository.deleteById(id);
    }

    @Override
    public List<TransportadoraResponseDTO> findByNome(String nome, int page, int pageSize) {
        List<Transportadora> list = transportadoraRepository.findByNome(nome).page(page, pageSize).list();
        return list.stream().map(e -> TransportadoraResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return transportadoraRepository.count();
    }

    @Override
    public long countByNome(String nome) {
        return transportadoraRepository.findByNome(nome).count();
    }
}