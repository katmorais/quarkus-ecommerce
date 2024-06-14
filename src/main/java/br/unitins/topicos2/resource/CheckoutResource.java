package br.unitins.topicos2.resource;

import br.unitins.topicos2.dto.CheckoutDTO;
import br.unitins.topicos2.service.CheckoutService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/checkout")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CheckoutResource {

    @Inject
    CheckoutService service;

//    private static final Logger LOG = Logger.getLogger(CheckoutResource.class);

//    @GET
//    @Path("/{id}")
//    public CartaoResponseDTO findById(@PathParam("id") Long id) {
//        return service.findById(id);
//    }

    @POST
    public Response insert(CheckoutDTO dto) {
        service.create(dto);
        return Response.status(Status.CREATED).build();
    }
}
