package br.com.alura.forun.controller;

import br.com.alura.forun.config.security.TokenService;
import br.com.alura.forun.controller.dto.LonginDto;
import br.com.alura.forun.controller.dto.TokenDto;
import br.com.alura.forun.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    Usuario usuario = new Usuario();

    @PostMapping
    public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LonginDto longinDto) {
        UsernamePasswordAuthenticationToken dadosLogin = longinDto.converter();

        try {
            Authentication authenticate = authenticationManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authenticate, longinDto.getEmail());

            return ResponseEntity.ok(new TokenDto(token,"Bearer"));

        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();

        }


    }

}
