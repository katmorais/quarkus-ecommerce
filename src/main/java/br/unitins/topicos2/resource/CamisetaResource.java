package br.unitins.topicos2.resource;

import java.util.List;

import br.unitins.topicos2.form.CamisetaImageForm;
import br.unitins.topicos2.service.FileService;
import jakarta.ws.rs.*;
import org.jboss.logging.Logger;
import br.unitins.topicos2.application.Result;
import br.unitins.topicos2.dto.CamisetaDTO;
import br.unitins.topicos2.dto.CamisetaResponseDTO;
import br.unitins.topicos2.service.CamisetaService;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

@Path("/camisetas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CamisetaResource {

    @Inject
    CamisetaService camisetaService;

    @Inject
    FileService fileService;

    private static final Logger LOG = Logger.getLogger(CamisetaResource.class);

    @GET
    public List<CamisetaResponseDTO> getAll(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize,
            @QueryParam("categoria") String categoria) {

        LOG.info("Buscando todos os camisetas.");
        LOG.debug("ERRO DE DEBUG.");
        return camisetaService.getAll(page, pageSize, categoria);
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

    @PATCH
    @Path("/image/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response salvarImagem(@MultipartForm CamisetaImageForm form) {
        try {
            fileService.salvar(form.getId(), form.getNomeImagem(), form.getImagem());
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro ao salvar imagem").build();
        }
    }

    @GET
    @Path("/image/download/{nomeImagem}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("nomeImagem") String nomeImagem) {
        ResponseBuilder response = Response.ok(fileService.download(nomeImagem));
        response.header("Content-Disposition", "attachment;filename=" + nomeImagem);
        return response.build();
    }

}
