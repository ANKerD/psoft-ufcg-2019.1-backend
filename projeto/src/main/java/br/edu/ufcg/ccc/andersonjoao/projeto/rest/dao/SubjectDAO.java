package br.edu.ufcg.ccc.andersonjoao.projeto.rest.dao;

import br.edu.ufcg.ccc.andersonjoao.projeto.rest.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;


@Repository
public interface SubjectDAO<T, ID extends Serializable> extends JpaRepository<Subject, Long> {

    Subject findById(long id);

    @Query(value="Select s from Subject s where s.nome like %?1%")
    ArrayList<Subject> findBySubstring(@Param("substring") String substring);
}
