package br.unitins.topicos2.resource;

import java.util.List;
import org.jboss.logging.Logger;
import br.unitins.topicos2.application.Result;
import br.unitins.topicos2.dto.EnderecoDTO;
import br.unitins.topicos2.dto.EnderecoResponseDTO;
import br.unitins.topicos2.service.EnderecoService;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/enderecos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EnderecoResource {

    @Inject
    EnderecoService enderecoService;

    private static final Logger LOG = Logger.getLogger(EnderecoResource.class);

    @GET
    public List<EnderecoResponseDTO> getAll(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize) {

        LOG.info("Buscando todos os enderecos.");
        LOG.debug("ERRO DE DEBUG.");
        return enderecoService.getAll(page, pageSize);
    }

    @GET
    @Path("/{id}")
    public EnderecoResponseDTO findById(@PathParam("id") Long id) {
        return enderecoService.findById(id);
    }

    @POST
    public Response insert(EnderecoDTO dto) {
        LOG.infof("Inserindo um endereco: %s", dto.bairro());

        EnderecoResponseDTO endereco = enderecoService.create(dto);
        LOG.infof("Endereco (%d) criado com sucesso.", endereco.id());
        return Response.status(Status.CREATED).entity(endereco).build();

    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, EnderecoDTO dto) {
        try {
            EnderecoResponseDTO endereco = enderecoService.update(id, dto);
            return Response.ok(endereco).build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        enderecoService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @Path("/count")
    public long count() {
        return enderecoService.count();
    }

    @GET
    @Path("/search/{nome}/count")
    public long count(@PathParam("nome") String nome) {
        return enderecoService.countByNome(nome);
    }

    @GET
    @Path("/search/{nome}")
    public List<EnderecoResponseDTO> search(
            @PathParam("nome") String nome,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize) {
        return enderecoService.findByNome(nome, page, pageSize);

    }
}
