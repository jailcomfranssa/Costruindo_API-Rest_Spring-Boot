package br.com.alura.forun.controller.requestBody;

import br.com.alura.forun.model.Curso;
import br.com.alura.forun.model.Topico;
import br.com.alura.forun.repository.CursoRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

public class TopicoRequestBody {
    @NotNull
    @NotEmpty
    @Size(min = 5)
    private String titulo;

    @NotNull
    @NotEmpty
    @Size(min = 5)
    private String mensagem;

    @NotNull
    @NotEmpty
    @Min(5) @Max(100)
    private String nomeCurso;

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

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public Topico converter(CursoRepository cursoRepository) {
        Curso curso = cursoRepository.findByNome(nomeCurso);
        return new Topico(titulo, mensagem, curso);
    }
}
