package br.edu.ufcg.ccc.andersonjoao.projeto.rest.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class Comment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private String authorEmail;
    private Long sujectId;
    private Long answerTo;


    public Comment(String content, String authorId, long sujectId, long answerTo) {
        this.content = content;
        this.authorEmail = authorEmail;
        this.sujectId = sujectId;
        this.answerTo = answerTo;
    }

    public Comment(String content, String authorEmail, long sujectId) {
        this.content = content;
        this.authorEmail = authorEmail;
        this.sujectId = sujectId;
    }

    public Comment() {}
}