package br.com.alura.forun.controller.requestBody;

import br.com.alura.forun.model.Topico;
import br.com.alura.forun.repository.TopicosRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AtualizacaoTopicoRequestBody {

    @NotNull
    @NotEmpty
    @Size(min = 5)
    private String titulo;

    @NotNull
    @NotEmpty
    @Size(min = 5)
    private String mensagem;


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Topico atualizar(Long id, TopicosRepository topicosRepository) {
        Topico topico = topicosRepository.getReferenceById(id);
        topico.setTitulo(this.titulo);
        topico.setMensagem(this.mensagem);

        return topico;
    }
}
