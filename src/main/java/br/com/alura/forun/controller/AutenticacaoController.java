package br.com.alura.forun.controller;

import br.com.alura.forun.controller.dto.LonginDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @PostMapping
    public ResponseEntity<?> autenticar(@RequestBody @Valid LonginDto longinDto){
        System.out.println("E-mail: "+longinDto.getEmail());
        System.out.println("Senha: "+longinDto.getSenha());

        return ResponseEntity.ok().build();

    }
}
