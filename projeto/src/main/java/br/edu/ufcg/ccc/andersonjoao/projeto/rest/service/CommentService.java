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

    public void save(Comment comment) {
        this.commentDAO.save(comment);
    }

    public void delete(Comment comment) {
        this.commentDAO.deleteById(comment.getId());
        List<Comment> comments = this.commentDAO.findByAnswerTo(comment.getId());
        for (Comment c : comments) {
            this.delete(c);
        }
    }
}
