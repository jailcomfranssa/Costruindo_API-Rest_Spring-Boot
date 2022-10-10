package br.com.alura.forun.repository;

import br.com.alura.forun.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicosRepository extends JpaRepository<Topico,Long> {


    List<Topico> findByCurso_Nome(String nomeCurso);

    //JPQL
//    @Query("SELECT t FROM Topico  t WHERE  t.curso.nome = :nomeCurso")
//    List<Topico> carregarPorNomeDoCurso (@Param("nomeCurso") String nomeCurso);
}
