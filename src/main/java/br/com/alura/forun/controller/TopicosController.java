package br.com.alura.forun.controller;

import br.com.alura.forun.controller.requestBody.AtualizacaoTopicoRequestBody;
import br.com.alura.forun.controller.requestBody.TopicoRequestBody;
import br.com.alura.forun.controller.dto.DetalheTopicoDto;
import br.com.alura.forun.controller.dto.TopicoDto;
import br.com.alura.forun.model.Topico;
import br.com.alura.forun.repository.CursoRepository;
import br.com.alura.forun.repository.TopicosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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
    @Cacheable(value = "listaDeTopicos")
    public ResponseEntity<Page<TopicoDto>> listaTodos(@RequestParam(required = false) String nomeCurso,
                                                      @PageableDefault(page = 0, size = 10) Pageable pageable){
//    public ResponseEntity<Page<TopicoDto>> listaTodos(@RequestParam(required = false) String nomeCurso,
//                                                      @RequestParam int pagina, @RequestParam int quantidade,
//                                                      @RequestParam(required = false) String ordenacao ) {
        //Pageable pageable = PageRequest.of(pagina,quantidade, Sort.Direction.ASC, ordenacao);
        Page<Topico> topicos;
        if (nomeCurso == null) {
            topicos = topicosRepository.findAll(pageable);
        } else {
            topicos = topicosRepository.findByCurso_Nome(nomeCurso, pageable);
        }
        return ResponseEntity.status(HttpStatus.OK).body(TopicoDto.converter(topicos));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoRequestBody topicoRequestBody, UriComponentsBuilder uriBuilder) {
        Topico topico = topicoRequestBody.converter(cursoRepository);
        topicosRepository.save(topico);
        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDto(topico));

    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalheTopicoDto> detalhar(@PathVariable Long id) {
        Optional<Topico> optionalTopico = topicosRepository.findById(id);
//        topicosRepository.getReferenceById(id);
        if (optionalTopico.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Topico topico = optionalTopico.get();
        return ResponseEntity.status(HttpStatus.OK).body(new DetalheTopicoDto(topico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id,
                                               @RequestBody @Valid AtualizacaoTopicoRequestBody topicoRequestBody) {
        Optional<Topico> optionalTopico = topicosRepository.findById(id);
        if (optionalTopico.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Topico topico = topicoRequestBody.atualizar(id, topicosRepository);
        return ResponseEntity.ok(new TopicoDto(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {
        Optional<Topico> optionalTopico = topicosRepository.findById(id);
        if (optionalTopico.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        topicosRepository.deleteById(id);
        return ResponseEntity.ok().body("Sucesso Deletado !!");

    }
}
