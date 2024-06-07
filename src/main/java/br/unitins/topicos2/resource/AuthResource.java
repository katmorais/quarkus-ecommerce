package br.unitins.topicos2.resource;

import br.unitins.topicos2.dto.AuthUsuarioDTO;
import br.unitins.topicos2.dto.UsuarioResponseDTO;
import br.unitins.topicos2.service.ClienteService;
import br.unitins.topicos2.service.HashService;
import br.unitins.topicos2.service.HashServiceImpl;
import br.unitins.topicos2.service.JwtService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    HashService hashService;

    @Inject
    ClienteService clienteService;

    @Inject
    JwtService jwtService;

    @POST
    public Response login(AuthUsuarioDTO authDTO) {
        String hash = hashService.getHashSenha(authDTO.senha());

        UsuarioResponseDTO usuario = null;
        if (authDTO.perfil() == 1)
            usuario = clienteService.findByUsernameAndSenha(authDTO.login(), hash);
        else if (authDTO.perfil() == 2) {
            // busca de usuario não ADM
        } else {
            return Response.status(Status.NOT_FOUND)
                    .entity("Username ou senha inválido").build();
        }
        return Response.ok(usuario)
                .header("Authorization", jwtService.generateJwt(usuario))
                .build();

    }

    public static void main(String[] args) {
        HashService h = new HashServiceImpl();
        String hash = h.getHashSenha("111");
        System.out.println(hash);
    }

}
