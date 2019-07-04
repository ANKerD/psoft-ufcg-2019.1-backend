package br.edu.ufcg.ccc.andersonjoao.projeto.rest.service;

import br.edu.ufcg.ccc.andersonjoao.projeto.rest.dao.CommentDAO;
import br.edu.ufcg.ccc.andersonjoao.projeto.rest.model.Comment;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CommentService {

    @Autowired
    private CommentDAO commentDAO;

    public CommentService() {}

    public CommentService(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    public List<Comment> findBySubject(long subjectId) { return this.commentDAO.findBySubjectId(subjectId);}

    public Comment findById(long id) { return this.commentDAO.findById(id);}

    public Comment save(Comment comment) {
        return this.commentDAO.save(comment);
    }

}
