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
    private Long authorId;
    private Long answerTo;


    public Comment(String content, long authorId, long answerTo) {
        this.content = content;
        this.authorId = authorId;
        this.answerTo = answerTo;
    }

    public Comment(String content, long authorId) {
        this.content = content;
        this.authorId = authorId;
    }

    public Comment() {}
}