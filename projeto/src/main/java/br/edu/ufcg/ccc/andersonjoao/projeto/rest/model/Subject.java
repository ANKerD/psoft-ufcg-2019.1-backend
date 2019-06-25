package br.edu.ufcg.ccc.andersonjoao.projeto.rest.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;

    public Subject() {}

    public Subject(String nome) {
        this.nome = nome;
    }
}