package br.unitins.topicos2.resource;


import java.util.List;

import org.jboss.logging.Logger;

import br.unitins.topicos2.application.Result;
import br.unitins.topicos2.dto.CorDTO;
import br.unitins.topicos2.dto.CorResponseDTO;
import br.unitins.topicos2.service.CorService;
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

@Path("/cartoes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CorResource {

    @Inject
    CorService corService;

    private static final Logger LOG = Logger.getLogger(CorResource.class);

    @GET
    public List<CorResponseDTO> getAll(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize) {


        LOG.info("Buscando todos os cors.");
        LOG.debug("ERRO DE DEBUG.");
        return corService.getAll(page, pageSize);
    }

    @GET
    @Path("/{id}")
    public CorResponseDTO findById(@PathParam("id") Long id) {
        return corService.findById(id);
    }

    @POST
    public Response insert(CorDTO dto) {
        LOG.infof("Inserindo um cor: %s", dto.nome());

        CorResponseDTO cor = corService.create(dto);
        // LOG.infof("Cor (%d) criado com sucesso.", cor.id()); LOG DE ERRO
        return Response.status(Status.CREATED).entity(cor).build();

    }    

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, CorDTO dto) {
        try {
            CorResponseDTO cor = corService.update(id, dto);
            return Response.ok(cor).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }      
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        corService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @Path("/count")
    public long count(){
        return corService.count();
    }

    @GET
    @Path("/search/{nome}/count")
    public long count(@PathParam("nome") String nome){
        return corService.countByNome(nome);
    }

    @GET
    @Path("/search/{nome}")
    public List<CorResponseDTO> search(
            @PathParam("nome") String nome,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize) {
        return corService.findByNome(nome, page, pageSize);
        
    }
}
