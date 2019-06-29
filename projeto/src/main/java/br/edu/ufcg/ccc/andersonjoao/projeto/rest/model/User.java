package br.edu.ufcg.ccc.andersonjoao.projeto.rest.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class User {

    @Id
    private String email;

    private String lastName;
    private String firstName;
    private String password;

    public User() {}

    public User(String email, String lastName, String firstName, String password) {
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
        this.password = password;
    }
}
