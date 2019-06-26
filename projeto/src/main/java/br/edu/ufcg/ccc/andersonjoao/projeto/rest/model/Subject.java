package br.edu.ufcg.ccc.andersonjoao.projeto.rest.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;

@Data
@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private ArrayList<Like> likes;
    private ArrayList<Double> ratings;
    private ArrayList<Comment> comments;

    public Subject() {
        this.likes = new ArrayList<>();
        this.ratings = new ArrayList<>();
        this.comments = new ArrayList<>();
    }
}