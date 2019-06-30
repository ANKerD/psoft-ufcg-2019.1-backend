package br.edu.ufcg.ccc.andersonjoao.projeto.rest.dao;

import br.edu.ufcg.ccc.andersonjoao.projeto.rest.model.Comment;
import br.edu.ufcg.ccc.andersonjoao.projeto.rest.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Repository
public interface CommentDAO<T, ID extends Serializable> extends JpaRepository<Comment, Long> {

    Comment findById(long id);

    List<Comment> findBySubjectId(Long subjectId);

}
