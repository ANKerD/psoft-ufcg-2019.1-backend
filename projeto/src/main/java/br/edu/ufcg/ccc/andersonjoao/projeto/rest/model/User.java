package br.edu.ufcg.ccc.andersonjoao.projeto.rest.model;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class User {

    @Id
    @ApiModelProperty(example = "a.kleber.d@gmail.com")
    private String email;
    @ApiModelProperty(example = "Dantas")
    private String lastName;
    @ApiModelProperty(example = "Anderson")
    private String firstName;
    @ApiModelProperty(example = "123")
    private String password;

    public User() {}

    public User(String email, String lastName, String firstName, String password) {
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
        this.password = password;
    }
}
