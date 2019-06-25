package br.edu.ufcg.ccc.andersonjoao.projeto.rest.controller;

import br.edu.ufcg.ccc.andersonjoao.projeto.rest.model.Subject;
import br.edu.ufcg.ccc.andersonjoao.projeto.rest.service.SubjectService;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectsController {

    @Autowired
    private SubjectService subjectService;

    @ApiOperation(value="Pega todas as disciplinas")
    @GetMapping("/find/")
    public ArrayList<Subject> allSubjects() {
        return this.findSubject("");
    }

    @ApiOperation(value="Pega disciplinas que possuem substr como substring")
    @GetMapping("/find/{substr}")
    public ArrayList<Subject> findSubject(@PathVariable String substr) {
        return subjectService.findBySubstring(substr);
    }

    @ApiOperation(value="Pega disciplina por id")
    @GetMapping("/byId/{id}")
    public Subject findSubjectByid(@PathVariable long id) {
        return subjectService.findById(id);
    }
    /*
    @Data
    private class Comment {

        private long id;
        private String content;
        private String author;
        private List<Comment> comments;
        private Date when;


        public Comment(long id, String content, String author, List<Comment> comments, Date when) {
            this.id = id;
            this.content = content;
            this.author = author;
            this.comments = comments;
            this.when = when;
        }
    }*/
}
