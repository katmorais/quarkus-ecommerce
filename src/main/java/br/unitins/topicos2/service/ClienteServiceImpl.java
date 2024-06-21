package br.unitins.topicos2.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import br.unitins.topicos2.dto.ClienteDTO;
import br.unitins.topicos2.dto.ClienteResponseDTO;
import br.unitins.topicos2.dto.ItemCompraResponseDTO;
import br.unitins.topicos2.dto.UsuarioResponseDTO;
import br.unitins.topicos2.model.*;
import br.unitins.topicos2.repository.*;
import br.unitins.topicos2.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

import static java.util.Objects.nonNull;

@ApplicationScoped
public class ClienteServiceImpl implements ClienteService {

    @Inject
    ClienteRepository clienteRepository;

    @Inject
    CheckoutRepository checkoutRepository;

    @Inject
    HashService hashService;

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
    public Cliente findById(Long id) {
        Cliente cliente = clienteRepository.findById(id);
        if (cliente == null)
            throw new NotFoundException("Cliente não encontrado.");
        return cliente;
    }

    @Override
    @Transactional
    public ClienteResponseDTO create(@Valid ClienteDTO clienteDTO) throws ConstraintViolationException {
//        validarDados(clienteDTO);

        Usuario usuario = new Usuario();
        usuario.setUsername(clienteDTO.username());
        usuario.setSenha(hashService.getHashSenha(clienteDTO.senha()));

        Pessoa pessoa = new Pessoa();
        pessoa.setCpf(clienteDTO.cpf());
        pessoa.setDataNascimento(clienteDTO.dataNascimento());
        pessoa.setNome(clienteDTO.nome());
        pessoa.setUsuario(usuario);

        Cidade cidade = new Cidade(clienteDTO.cidadeId());
        cidade.setEstado(new Estado(clienteDTO.estadoId()));

        Cliente cliente = new Cliente();
        cliente.setPessoa(pessoa);
        cliente.setNaturalidade(cidade);
        cliente.setCargo(Cargo.CLIENTE);
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
    public void alterarSenha(ClienteDTO dto) {
        Cliente cliente = clienteRepository.findByCpfAndLogin(dto.cpf(), dto.username());

        if (nonNull(cliente)) {
            String hashSenha = hashService.getHashSenha(dto.senha());
            cliente.getPessoa().getUsuario().setSenha(hashSenha);
        } else {
            throw new ValidationException("cpf", "Cliente não encontrado.");
        }
    }

    @Override
    public List<Checkout> getCompras(Long id) {
        return checkoutRepository.findByClienteId(id);
    }

    @Override
    public UsuarioResponseDTO findByUsernameAndSenha(String username, String senha) {
        Cliente cliente = clienteRepository.findByUsernameAndSenha(username, senha).firstResult();

        if (cliente == null)
            throw new ValidationException(username, "Username ou senha inválido");
        return UsuarioResponseDTO.valueOf(cliente);
    }


}