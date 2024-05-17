package br.unitins.topicos2.resource;

import java.util.List;
import org.jboss.logging.Logger;
import br.unitins.topicos2.application.Result;
import br.unitins.topicos2.dto.TipoCamisetaDTO;
import br.unitins.topicos2.dto.TipoCamisetaResponseDTO;
import br.unitins.topicos2.service.TipoCamisetaService;
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

@Path("/tipocamisetas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TipoCamisetaResource {

    @Inject
    TipoCamisetaService tipocamisetaService;

    private static final Logger LOG = Logger.getLogger(TipoCamisetaResource.class);

    @GET
    public List<TipoCamisetaResponseDTO> getAll(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize) {

        LOG.info("Buscando todos os tipocamisetas.");
        LOG.debug("ERRO DE DEBUG.");
        return tipocamisetaService.getAll(page, pageSize);
    }

    @GET
    @Path("/{id}")
    public TipoCamisetaResponseDTO findById(@PathParam("id") Long id) {
        return tipocamisetaService.findById(id);
    }

    @POST
    public Response insert(TipoCamisetaDTO dto) {
        LOG.infof("Inserindo um tipocamiseta: %s", dto.nome());

        TipoCamisetaResponseDTO tipocamiseta = tipocamisetaService.create(dto);
        // LOG.infof("TipoCamiseta (%d) criado com sucesso.", tipocamiseta.id()); LOG DE
        // ERRO
        return Response.status(Status.CREATED).entity(tipocamiseta).build();

    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, TipoCamisetaDTO dto) {
        try {
            TipoCamisetaResponseDTO tipocamiseta = tipocamisetaService.update(id, dto);
            return Response.ok(tipocamiseta).build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        tipocamisetaService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @Path("/count")
    public long count() {
        return tipocamisetaService.count();
    }

    @GET
    @Path("/search/{nome}/count")
    public long count(@PathParam("nome") String nome) {
        return tipocamisetaService.countByNome(nome);
    }

    @GET
    @Path("/search/{nome}")
    public List<TipoCamisetaResponseDTO> search(
            @PathParam("nome") String nome,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize) {
        return tipocamisetaService.findByNome(nome, page, pageSize);

    }

}
