package br.edu.ufcg.ccc.andersonjoao.projeto.rest.controller;

import lombok.Data;
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

    @GetMapping("/")
    public List<String> allSubjects() {
        return new ArrayList<String>();
    }

    @GetMapping("/find/{substr}")
    public List<String> findSubject(@PathVariable String substr) {
        return new ArrayList<String>();
    }

    @GetMapping("/{id}")
    public String findSubjectByid(@PathVariable String id) {
        return "Work in progress dude";
    }

    @Data
    private class Subject {

        private String nome;
        private long id;

        public Subject(String nome, long id) {
            this.nome = nome;
            this.id = id;
        }
    }

    @Data
    private class SubjectProfile {

        private String nome;
        private long id;
        private long likes;
        private long dislikes;
        private double rating;
        private List<Comment> comments;

        public SubjectProfile(String nome, long id, long likes, long dislikes, double rating, List<Comment> comments) {
            this.nome = nome;
            this.id = id;
            this.likes = likes;
            this.dislikes = dislikes;
            this.rating = rating;
            this.comments = comments;
        }
    }

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
    }
}
