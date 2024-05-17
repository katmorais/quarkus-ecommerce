package br.unitins.topicos2.resource;

import java.util.List;
import org.jboss.logging.Logger;
import br.unitins.topicos2.application.Result;
import br.unitins.topicos2.dto.TransportadoraDTO;
import br.unitins.topicos2.dto.TransportadoraResponseDTO;
import br.unitins.topicos2.service.TransportadoraService;
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

@Path("/transportadoras")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TransportadoraResource {

    @Inject
    TransportadoraService transportadoraService;

    private static final Logger LOG = Logger.getLogger(TransportadoraResource.class);

    @GET
    public List<TransportadoraResponseDTO> getAll(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize) {

        LOG.info("Buscando todos os transportadoras.");
        LOG.debug("ERRO DE DEBUG.");
        return transportadoraService.getAll(page, pageSize);
    }

    @GET
    @Path("/{id}")
    public TransportadoraResponseDTO findById(@PathParam("id") Long id) {
        return transportadoraService.findById(id);
    }

    @POST
    public Response insert(TransportadoraDTO dto) {
        LOG.infof("Inserindo um transportadora: %s", dto.nome());

        TransportadoraResponseDTO transportadora = transportadoraService.create(dto);
        // LOG.infof("Transportadora (%d) criado com sucesso.", transportadora.id());
        // LOG DE ERRO
        return Response.status(Status.CREATED).entity(transportadora).build();

    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, TransportadoraDTO dto) {
        try {
            TransportadoraResponseDTO transportadora = transportadoraService.update(id, dto);
            return Response.ok(transportadora).build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        transportadoraService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @Path("/count")
    public long count() {
        return transportadoraService.count();
    }

    @GET
    @Path("/search/{nome}/count")
    public long count(@PathParam("nome") String nome) {
        return transportadoraService.countByNome(nome);
    }

    @GET
    @Path("/search/{nome}")
    public List<TransportadoraResponseDTO> search(
            @PathParam("nome") String nome,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize) {
        return transportadoraService.findByNome(nome, page, pageSize);

    }
}
