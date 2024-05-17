package br.unitins.topicos2.resource;

import java.util.List;
import org.jboss.logging.Logger;
import br.unitins.topicos2.application.Result;
import br.unitins.topicos2.dto.FornecedorDTO;
import br.unitins.topicos2.dto.FornecedorResponseDTO;
import br.unitins.topicos2.service.FornecedorService;
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

@Path("/fornecedores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FornecedorResource {

    @Inject
    FornecedorService fornecedorService;

    private static final Logger LOG = Logger.getLogger(FornecedorResource.class);

    @GET
    public List<FornecedorResponseDTO> getAll(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize) {

        LOG.info("Buscando todos os fornecedores.");
        LOG.debug("ERRO DE DEBUG.");
        return fornecedorService.getAll(page, pageSize);
    }

    @GET
    @Path("/{id}")
    public FornecedorResponseDTO findById(@PathParam("id") Long id) {
        return fornecedorService.findById(id);
    }

    @POST
    public Response insert(FornecedorDTO dto) {
        LOG.infof("Inserindo um fornecedor: %s", dto.nome());

        FornecedorResponseDTO fornecedor = fornecedorService.create(dto);
        // LOG.infof("Fornecedor (%d) criado com sucesso.", fornecedor.id()); LOG DE
        // ERRO
        return Response.status(Status.CREATED).entity(fornecedor).build();

    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, FornecedorDTO dto) {
        try {
            FornecedorResponseDTO fornecedor = fornecedorService.update(id, dto);
            return Response.ok(fornecedor).build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        fornecedorService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @Path("/count")
    public long count() {
        return fornecedorService.count();
    }

    @GET
    @Path("/search/{nome}/count")
    public long count(@PathParam("nome") String nome) {
        return fornecedorService.countByNome(nome);
    }

    @GET
    @Path("/search/{nome}")
    public List<FornecedorResponseDTO> search(
            @PathParam("nome") String nome,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize) {
        return fornecedorService.findByNome(nome, page, pageSize);

    }
}
