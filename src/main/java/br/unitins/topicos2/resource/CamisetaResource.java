package br.unitins.topicos2.resource;

import java.util.List;
import org.jboss.logging.Logger;
import br.unitins.topicos2.application.Result;
import br.unitins.topicos2.dto.CamisetaDTO;
import br.unitins.topicos2.dto.CamisetaResponseDTO;
import br.unitins.topicos2.service.CamisetaService;
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

@Path("/camisetas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CamisetaResource {

    @Inject
    CamisetaService camisetaService;

    private static final Logger LOG = Logger.getLogger(CamisetaResource.class);

    @GET
    public List<CamisetaResponseDTO> getAll(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize) {

        LOG.info("Buscando todos os camisetas.");
        LOG.debug("ERRO DE DEBUG.");
        return camisetaService.getAll(page, pageSize);
    }

    @GET
    @Path("/{id}")
    public CamisetaResponseDTO findById(@PathParam("id") Long id) {
        return camisetaService.findById(id);
    }

    @POST
    public Response insert(CamisetaDTO dto) {
        LOG.infof("Inserindo uma camiseta: %s", dto.nome());

        CamisetaResponseDTO camiseta = camisetaService.create(dto);
        LOG.infof("Camiseta (%d) criada com sucesso.", camiseta.id());
        return Response.status(Status.CREATED).entity(camiseta).build();

    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, CamisetaDTO dto) {
        try {
            CamisetaResponseDTO camiseta = camisetaService.update(id, dto);
            return Response.ok(camiseta).build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        camisetaService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @Path("/count")
    public long count() {
        return camisetaService.count();
    }

    @GET
    @Path("/search/{nome}/count")
    public long count(@PathParam("nome") String nome) {
        return camisetaService.countByNome(nome);
    }

    @GET
    @Path("/search/{nome}")
    public List<CamisetaResponseDTO> search(
            @PathParam("nome") String nome,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize) {
        return camisetaService.findByNome(nome, page, pageSize);

    }
}
