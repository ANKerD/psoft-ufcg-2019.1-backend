package br.edu.ufcg.ccc.andersonjoao.projeto.rest.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;

@Data
@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private HashSet<String> usersLiked;

    public Subject() {
        this.usersLiked = new HashSet<>();
    }
}