package br.com.alura.forun.controller.requestBody;

import br.com.alura.forun.model.Curso;
import br.com.alura.forun.model.Topico;
import br.com.alura.forun.repository.CursoRepository;

public class TopicoRequestBody {

    private String titulo;
    private String mensagem;
    private String nomeCurso;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagens() {
        return mensagem;
    }

    public void setMensagens(String mensagem) {
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