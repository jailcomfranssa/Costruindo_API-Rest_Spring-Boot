package br.com.alura.forum.repository;

import br.com.alura.forum.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicosRepository extends JpaRepository<Topico,Long> {


    Page<Topico> findByCurso_Nome(String nomeCurso, Pageable pageable);

    //JPQL
//    @Query("SELECT t FROM Topico  t WHERE  t.curso.nome = :nomeCurso")
//    List<Topico> carregarPorNomeDoCurso (@Param("nomeCurso") String nomeCurso);
}
