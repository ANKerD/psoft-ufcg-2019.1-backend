package br.edu.ufcg.ccc.andersonjoao.projeto.rest.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@Data
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private String authorEmail;
    private Long subjectId;
    private Long answerTo;

    @Column(columnDefinition = "boolean default true")
    private Boolean active;

    public Comment(String content, String authorId, long subjectId, long answerTo) {
        this.content = content;
        this.authorEmail = authorEmail;
        this.subjectId = subjectId;
        this.answerTo = answerTo;
    }

    public Comment(String content, String authorEmail, long subjectId) {
        this.content = content;
        this.authorEmail = authorEmail;
        this.subjectId = subjectId;
    }

    public Comment() {
    }
}