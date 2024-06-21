package br.unitins.topicos2.resource;

import br.unitins.topicos2.dto.ClienteDTO;
import br.unitins.topicos2.dto.ClienteResponseDTO;
import br.unitins.topicos2.service.ClienteService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import org.jboss.logging.Logger;

@Path("/cliente")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource {

    @Inject
    ClienteService service;

    private static final Logger LOG = Logger.getLogger(ClienteResource.class);

    @POST
    public Response create(ClienteDTO dto) {
        ClienteResponseDTO retorno = service.create(dto);
        return Response.status(201).entity(retorno).build();
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Response update(ClienteDTO dto, @PathParam("id") Long id) {
        service.update(id, dto);
        return Response.status(Status.NO_CONTENT).build();
    }

    @PUT
    @Transactional
    @Path("/alterar-senha")
    public Response update(ClienteDTO dto) {
        service.alterarSenha(dto);
        return Response.status(Status.NO_CONTENT).build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }
}