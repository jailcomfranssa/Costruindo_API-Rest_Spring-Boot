package br.com.alura.forun.controller;

import br.com.alura.forun.controller.requestBody.AtualizacaoTopicoRequestBody;
import br.com.alura.forun.controller.requestBody.TopicoRequestBody;
import br.com.alura.forun.dto.DetalheTopicoDto;
import br.com.alura.forun.dto.TopicoDto;
import br.com.alura.forun.model.Topico;
import br.com.alura.forun.repository.CursoRepository;
import br.com.alura.forun.repository.TopicosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<List<TopicoDto> >listaTodos(String nomeCurso) {
        if (nomeCurso == null) {
            List<Topico> topicos = topicosRepository.findAll();
            return  ResponseEntity.status(HttpStatus.OK).body( TopicoDto.converter(topicos));
        } else {
            List<Topico> topicos = topicosRepository.findByCurso_Nome(nomeCurso);
            return  ResponseEntity.status(HttpStatus.OK).body( TopicoDto.converter(topicos));
        }
    }
    @PostMapping
    @Transactional
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoRequestBody topicoRequestBody, UriComponentsBuilder uriBuilder){
        Topico topico = topicoRequestBody.converter(cursoRepository);
        topicosRepository.save(topico);
        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDto(topico));

    }
    @GetMapping("/{id}")
    public ResponseEntity<DetalheTopicoDto> detalhar(@PathVariable Long id){
        Optional<Topico> optionalTopico = topicosRepository.findById(id);
//        topicosRepository.getReferenceById(id);
        if(optionalTopico.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Topico topico = optionalTopico.get();
        return ResponseEntity.status(HttpStatus.OK).body(new DetalheTopicoDto(topico));
    }
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id,
                                               @RequestBody @Valid AtualizacaoTopicoRequestBody topicoRequestBody ){
        Topico topico = topicoRequestBody.atualizar(id, topicosRepository);
        return ResponseEntity.ok(new TopicoDto(topico));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id){
        topicosRepository.deleteById(id);
        return ResponseEntity.ok().body("Sucesso Deletado !!");

    }
}
