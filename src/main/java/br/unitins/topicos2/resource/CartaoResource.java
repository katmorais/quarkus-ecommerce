package br.unitins.topicos2.resource;

import java.util.List;
import org.jboss.logging.Logger;
import br.unitins.topicos2.application.Result;
import br.unitins.topicos2.dto.CartaoDTO;
import br.unitins.topicos2.dto.CartaoResponseDTO;
import br.unitins.topicos2.service.CartaoService;
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
public class CartaoResource {

    @Inject
    CartaoService cartaoService;

    private static final Logger LOG = Logger.getLogger(CartaoResource.class);

    @GET
    public List<CartaoResponseDTO> getAll(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize) {

        LOG.info("Buscando todos os cartaos.");
        LOG.debug("ERRO DE DEBUG.");
        return cartaoService.getAll(page, pageSize);
    }

    @GET
    @Path("/{id}")
    public CartaoResponseDTO findById(@PathParam("id") Long id) {
        return cartaoService.findById(id);
    }

    @POST
    public Response insert(CartaoDTO dto) {
        LOG.infof("Inserindo um cartao: %s", dto.nome());

        CartaoResponseDTO cartao = cartaoService.create(dto);
        // LOG.infof("Cartao (%d) criado com sucesso.", cartao.id()); LOG DE ERRO
        return Response.status(Status.CREATED).entity(cartao).build();

    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, CartaoDTO dto) {
        try {
            CartaoResponseDTO cartao = cartaoService.update(id, dto);
            return Response.ok(cartao).build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        cartaoService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @Path("/count")
    public long count() {
        return cartaoService.count();
    }

    @GET
    @Path("/search/{nome}/count")
    public long count(@PathParam("nome") String nome) {
        return cartaoService.countByNome(nome);
    }

    @GET
    @Path("/search/{nome}")
    public List<CartaoResponseDTO> search(
            @PathParam("nome") String nome,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize) {
        return cartaoService.findByNome(nome, page, pageSize);

    }
}
