package br.com.alura.forun.controller;

import br.com.alura.forun.dto.TopicoDto;
import br.com.alura.forun.model.Topico;
import br.com.alura.forun.repository.TopicosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TopicosController {

    private final TopicosRepository topicosRepository;

    @Autowired
    public TopicosController(TopicosRepository topicosRepository) {
        this.topicosRepository = topicosRepository;
    }

    @RequestMapping("/topicos")
    public List<TopicoDto> listaTodos(String nomeCurso) {
        if (nomeCurso == null) {
            List<Topico> topicos = topicosRepository.findAll();
            return TopicoDto.converter(topicos);
        } else {
            List<Topico> topicos = topicosRepository.findByCurso_Nome(nomeCurso);
            return TopicoDto.converter(topicos);
        }
    }
}
