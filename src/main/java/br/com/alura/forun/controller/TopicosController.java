package br.com.alura.forun.controller;

import br.com.alura.forun.controller.requestBody.TopicoRequestBody;
import br.com.alura.forun.dto.TopicoDto;
import br.com.alura.forun.model.Topico;
import br.com.alura.forun.repository.CursoRepository;
import br.com.alura.forun.repository.TopicosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    private final TopicosRepository topicosRepository;
    private final CursoRepository cursoRepository;

    @Autowired
    public TopicosController(TopicosRepository topicosRepository, CursoRepository cursoRepository) {
        this.topicosRepository = topicosRepository;
        this.cursoRepository = cursoRepository;
    }

    @GetMapping
    public List<TopicoDto> listaTodos(String nomeCurso) {
        if (nomeCurso == null) {
            List<Topico> topicos = topicosRepository.findAll();
            return TopicoDto.converter(topicos);
        } else {
            List<Topico> topicos = topicosRepository.findByCurso_Nome(nomeCurso);
            return TopicoDto.converter(topicos);
        }
    }
    @PostMapping
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody TopicoRequestBody topicoRequestBody, UriComponentsBuilder uriBuilder){
        Topico topico = topicoRequestBody.converter(cursoRepository);
        topicosRepository.save(topico);
        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDto(topico));

    }
}
