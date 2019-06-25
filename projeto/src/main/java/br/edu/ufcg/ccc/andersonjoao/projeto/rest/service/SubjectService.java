package br.edu.ufcg.ccc.andersonjoao.projeto.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.ufcg.ccc.andersonjoao.projeto.rest.dao.SubjectDAO;
import br.edu.ufcg.ccc.andersonjoao.projeto.rest.model.Subject;
import java.util.ArrayList;


@Service
public class SubjectService {

    @Autowired
    private SubjectDAO subjectDAO;

    public SubjectService() {}


    public SubjectService(SubjectDAO subjectDAO) {
        this.subjectDAO = subjectDAO;
    }

    public ArrayList<Subject> findBySubstring(String substring) { return this.subjectDAO.findBySubstring(substring.toUpperCase());}

    public Subject findById(long id) { return this.subjectDAO.findById(id);}
}
