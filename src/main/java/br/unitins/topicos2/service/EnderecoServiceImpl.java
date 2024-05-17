package br.unitins.topicos2.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import br.unitins.topicos2.dto.EnderecoDTO;
import br.unitins.topicos2.dto.EnderecoResponseDTO;
import br.unitins.topicos2.model.Endereco;
import br.unitins.topicos2.repository.CidadeRepository;
import br.unitins.topicos2.repository.EnderecoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class EnderecoServiceImpl implements EnderecoService {

    @Inject
    CidadeRepository cidadeRepository;

    @Inject
    EnderecoRepository enderecoRepository;

    @Inject
    Validator validator;

    @Override
    public List<EnderecoResponseDTO> getAll(int page, int pageSize) {

        List<Endereco> list = enderecoRepository.findAll().page(page, pageSize).list();
        return list.stream().map(e -> EnderecoResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public EnderecoResponseDTO findById(Long id) {
        Endereco endereco = enderecoRepository.findById(id);
        if (endereco == null)
            throw new NotFoundException("Endereco não encontrado.");
        return EnderecoResponseDTO.valueOf(endereco);
    }

    @Override
    @Transactional
    public EnderecoResponseDTO create(@Valid EnderecoDTO enderecoDTO) throws ConstraintViolationException {
        // validar(enderecoDTO);

        Endereco entity = new Endereco();
        entity.setRua(enderecoDTO.rua());
        entity.setNumero(enderecoDTO.numero());
        entity.setComplemento(enderecoDTO.complemento());
        entity.setBairro(enderecoDTO.bairro());
        entity.setCep(enderecoDTO.cep());
        entity.setCidade(cidadeRepository.findById(enderecoDTO.idCidade()));

        enderecoRepository.persist(entity);

        return EnderecoResponseDTO.valueOf(entity);

    }

    @Override
    @Transactional
    public EnderecoResponseDTO update(Long id, EnderecoDTO enderecoDTO) throws ConstraintViolationException {
        validar(enderecoDTO);

        Endereco entity = enderecoRepository.findById(id);

        entity.setRua(enderecoDTO.rua());
        entity.setNumero(enderecoDTO.numero());
        entity.setComplemento(enderecoDTO.complemento());
        entity.setBairro(enderecoDTO.bairro());
        entity.setCep(enderecoDTO.cep());
        entity.setCidade(cidadeRepository.findById(enderecoDTO.idCidade()));

        return EnderecoResponseDTO.valueOf(entity);
    }

    private void validar(EnderecoDTO enderecoDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<EnderecoDTO>> violations = validator.validate(enderecoDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        enderecoRepository.deleteById(id);
    }

    @Override
    public List<EnderecoResponseDTO> findByNome(String nome, int page, int pageSize) {
        List<Endereco> list = enderecoRepository.findByCep(nome).page(page, pageSize).list();
        return list.stream().map(e -> EnderecoResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return enderecoRepository.count();
    }

    @Override
    public long countByNome(String nome) {
        return enderecoRepository.findByCep(nome).count();
    }
}