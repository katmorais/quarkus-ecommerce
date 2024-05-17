package br.unitins.topicos2.service;

import br.unitins.topicos2.dto.UsuarioResponseDTO;

public interface JwtService {
    public String generateJwt(UsuarioResponseDTO dto);
}
