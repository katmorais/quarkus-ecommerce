package br.unitins.topicos2.resource;

import java.util.List;
import org.jboss.logging.Logger;
import br.unitins.topicos2.application.Result;
import br.unitins.topicos2.dto.CupomDTO;
import br.unitins.topicos2.dto.CupomResponseDTO;
import br.unitins.topicos2.service.CupomService;
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

@Path("/cupons")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CupomResource {

    @Inject
    CupomService cupomService;

    private static final Logger LOG = Logger.getLogger(CupomResource.class);

    @GET
    public List<CupomResponseDTO> getAll(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize) {

        LOG.info("Buscando todos os cupoms.");
        LOG.debug("ERRO DE DEBUG.");
        return cupomService.getAll(page, pageSize);
    }

    @GET
    @Path("/{id}")
    public CupomResponseDTO findById(@PathParam("id") Long id) {
        return cupomService.findById(id);
    }

    @GET
    @Path("/status/{id}")
    public Response getStatus(@PathParam("id") Long id) {
        try {
            return Response.ok().entity(cupomService.getStatus(id)).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @POST
    public Response insert(CupomDTO dto) {
        LOG.infof("Inserindo um cupom: %s", dto.codigo());

        CupomResponseDTO cupom = cupomService.create(dto);
        LOG.infof("Cupom (%d) criado com sucesso.", cupom.id());
        return Response.status(Status.CREATED).entity(cupom).build();

    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, CupomDTO dto) {
        try {
            CupomResponseDTO cupom = cupomService.update(id, dto);
            return Response.ok(cupom).build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        cupomService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @Path("/count")
    public long count() {
        return cupomService.count();
    }

    @GET
    @Path("/search/{nome}/count")
    public long count(@PathParam("nome") String nome) {
        return cupomService.countByNome(nome);
    }

    @GET
    @Path("/search/{nome}")
    public List<CupomResponseDTO> search(
            @PathParam("nome") String nome,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize) {
        return cupomService.findByNome(nome, page, pageSize);

    }
}
