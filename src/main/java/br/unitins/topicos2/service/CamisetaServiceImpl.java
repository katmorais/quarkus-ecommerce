package br.unitins.topicos2.service;

import br.unitins.topicos2.dto.CamisetaDTO;
import br.unitins.topicos2.dto.CamisetaResponseDTO;
import br.unitins.topicos2.model.Camiseta;
import br.unitins.topicos2.model.Tamanho;
import br.unitins.topicos2.repository.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class CamisetaServiceImpl implements CamisetaService {

    @Inject
    FornecedorRepository fornecedorRepository;

    @Inject
    CamisetaRepository camisetaRepository;

    @Inject
    TipoCamisetaRepository tipoCamisetaRepository;

    @Inject
    CorRepository corRepository;

    @Inject
    MarcaRepository marcaRepository;

    @Inject
    Validator validator;

    @Override
    public List<CamisetaResponseDTO> getAll(int page, int pageSize) {

        List<Camiseta> list = camisetaRepository.findAll().page(page, pageSize).list();
        return list.stream().map(CamisetaResponseDTO::valueOf).collect(Collectors.toList());
    }

    @Override
    public CamisetaResponseDTO findById(Long id) {
        Camiseta camiseta = camisetaRepository.findById(id);
        if (camiseta == null)
            throw new NotFoundException("Camiseta não encontrada.");
        return CamisetaResponseDTO.valueOf(camiseta);
    }

    @Override
    @Transactional
    public CamisetaResponseDTO create(@Valid CamisetaDTO camisetaDTO) throws ConstraintViolationException {
        // validar(camisetaDTO);

        Camiseta entity = new Camiseta();

        entity.setNome(camisetaDTO.nome());
        entity.setDescricao(camisetaDTO.descricao());
        entity.setEstoque(camisetaDTO.estoque());
        entity.setPreco(camisetaDTO.preco());
        entity.setEstampa(camisetaDTO.estampa());
        entity.setTecido(camisetaDTO.tecido());
        entity.setTamanho(Tamanho.valueOf(camisetaDTO.tamanho()));
        entity.setFornecedor(fornecedorRepository.findById(camisetaDTO.fornecedor()));
        entity.setTipoCamiseta(tipoCamisetaRepository.findById(camisetaDTO.tipoCamiseta()));
        entity.setMarca(marcaRepository.findById(camisetaDTO.marca()));

//        entity.setCor(new ArrayList<Cor>());
//
//        if (camisetaDTO.cores() != null) {
//            for (CorDTO cor : camisetaDTO.cores()) {
//                Cor c = new Cor();
//                c.setNome(cor.nome());
//                entity.getCor().add(c);
//            }
//        }
        camisetaRepository.persist(entity);

        return CamisetaResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public CamisetaResponseDTO update(Long id, CamisetaDTO camisetaDTO) throws ConstraintViolationException {
        validar(camisetaDTO);

        Camiseta entity = camisetaRepository.findById(id);

        entity.setNome(camisetaDTO.nome());
        entity.setDescricao(camisetaDTO.descricao());
        entity.setEstoque(camisetaDTO.estoque());
        entity.setPreco(camisetaDTO.preco());
        entity.setEstampa(camisetaDTO.estampa());
        entity.setTecido(camisetaDTO.tecido());
        entity.setTamanho(Tamanho.valueOf(camisetaDTO.tamanho()));
        entity.setTipoCamiseta(tipoCamisetaRepository.findById(camisetaDTO.tipoCamiseta()));
        entity.setFornecedor(fornecedorRepository.findById(camisetaDTO.fornecedor()));
        entity.setMarca(marcaRepository.findById(camisetaDTO.marca()));
        // apagando os cores antigas
        entity.getCor().clear();

//        for (CorDTO cor : camisetaDTO.cores()) {
//            Cor c = new Cor();
//            c.setNome(cor.nome());
//            entity.getCor().add(c);
//        }
        return CamisetaResponseDTO.valueOf(entity);
    }

    private void validar(CamisetaDTO camisetaDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<CamisetaDTO>> violations = validator.validate(camisetaDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        camisetaRepository.deleteById(id);
    }

    @Override
    public List<CamisetaResponseDTO> findByNome(String nome, int page, int pageSize) {
        List<Camiseta> list = camisetaRepository.findByNome(nome).page(page, pageSize).list();
        return list.stream().map(CamisetaResponseDTO::valueOf).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return camisetaRepository.count();
    }

    @Override
    public long countByNome(String nome) {
        return camisetaRepository.findByNome(nome).count();
    }
}
