package br.unitins.topicos2.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import br.unitins.topicos2.dto.ClienteDTO;
import br.unitins.topicos2.dto.ClienteResponseDTO;
import br.unitins.topicos2.dto.UsuarioResponseDTO;
import br.unitins.topicos2.model.Cliente;
import br.unitins.topicos2.model.Pessoa;
import br.unitins.topicos2.model.Usuario;
import br.unitins.topicos2.repository.CidadeRepository;
import br.unitins.topicos2.repository.ClienteRepository;
import br.unitins.topicos2.repository.PessoaRepository;
import br.unitins.topicos2.repository.UsuarioRepository;
import br.unitins.topicos2.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class ClienteServiceImpl implements ClienteService {

    @Inject
    ClienteRepository clienteRepository;

    @Inject
    CidadeRepository cidadeRepository;

    @Inject
    PessoaRepository pessoaRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    Validator validator;

    @Override
    public List<ClienteResponseDTO> getAll(int page, int pageSize) {
        List<Cliente> list = clienteRepository
                .findAll()
                .page(page, pageSize)
                .list();

        return list.stream().map(e -> ClienteResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public ClienteResponseDTO findById(Long id) {
        Cliente cliente = clienteRepository.findById(id);
        if (cliente == null)
            throw new NotFoundException("Cliente não encontrado.");
        return ClienteResponseDTO.valueOf(cliente);
    }

    @Override
    @Transactional
    public ClienteResponseDTO create(@Valid ClienteDTO clienteDTO) throws ConstraintViolationException {
        validarDados(clienteDTO);

        Usuario usuario = new Usuario();
        usuario.setUsername(clienteDTO.username());
        usuario.setSenha(clienteDTO.senha());
        usuarioRepository.persist(usuario);

        Pessoa pessoa = new Pessoa();
        pessoa.setCpf(clienteDTO.cpf());
        pessoa.setDataNascimento(clienteDTO.dataNascimento());
        pessoa.setNome(clienteDTO.nome());
        pessoa.setUsuario(usuario);
        pessoaRepository.persist(pessoa);

        Cliente cliente = new Cliente();
        cliente.setNaturalidade(cidadeRepository.findById(clienteDTO.idNaturalidade()));
        cliente.setPessoa(pessoa);
        clienteRepository.persist(cliente);

        return ClienteResponseDTO.valueOf(cliente);
    }

    private void validarDados(ClienteDTO clienteDTO) {
        if (usuarioRepository.findByUsername(clienteDTO.username()).firstResult() != null)
            throw new ValidationException("username", "Username indisponível.");

        if (pessoaRepository.findByCpf(clienteDTO.cpf()).firstResult() != null)
            throw new ValidationException("cpf", "Ja existe este cpf cadastrado.");

    }

    @Override
    @Transactional
    public ClienteResponseDTO update(Long id, ClienteDTO clienteDTO) throws ConstraintViolationException {
        validar(clienteDTO);

        Cliente entity = clienteRepository.findById(id);

        return ClienteResponseDTO.valueOf(entity);
    }

    private void validar(ClienteDTO clienteDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<ClienteDTO>> violations = validator.validate(clienteDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public List<ClienteResponseDTO> findByNome(String nome) {
        List<Cliente> list = clienteRepository.findByNome(nome).list();
        return list.stream().map(e -> ClienteResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return clienteRepository.count();
    }

    @Override
    public UsuarioResponseDTO findByUsernameAndSenha(String username, String senha) {
        Cliente cliente = clienteRepository.findByUsernameAndSenha(username, senha).firstResult();

        if (cliente == null)
            throw new ValidationException(username, "Username ou senha inválido");
        return UsuarioResponseDTO.valueOf(cliente.getPessoa());
    }

}