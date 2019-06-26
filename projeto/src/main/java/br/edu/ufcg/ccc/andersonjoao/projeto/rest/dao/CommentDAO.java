package br.edu.ufcg.ccc.andersonjoao.projeto.rest.dao;

import br.edu.ufcg.ccc.andersonjoao.projeto.rest.model.Comment;
import br.edu.ufcg.ccc.andersonjoao.projeto.rest.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;


@Repository
public interface CommentDAO<T, ID extends Serializable> extends JpaRepository<Comment, Long> {

    Comment findById(long id);

    @Query(value="Select c from Comment c where c.sujectId=:subjectId")
    ArrayList<Comment> findBySubject(@Param("subjectId") long subjectId);
}
