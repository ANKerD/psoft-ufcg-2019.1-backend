package br.edu.ufcg.ccc.andersonjoao.projeto.rest.service;

import br.edu.ufcg.ccc.andersonjoao.projeto.rest.dao.CommentDAO;
import br.edu.ufcg.ccc.andersonjoao.projeto.rest.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class CommentService {

    @Autowired
    private CommentDAO commentDAO;

    public CommentService() {}


    public CommentService(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    public ArrayList<Comment> findBySubject(long subjectId) { return this.commentDAO.findBySubject(subjectId);}

    public Comment findById(long id) { return this.commentDAO.findById(id);}
}
