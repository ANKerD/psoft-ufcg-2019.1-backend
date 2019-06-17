package br.edu.ufcg.ccc.andersonjoao.projeto.rest.dao;

import br.edu.ufcg.ccc.andersonjoao.projeto.rest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface UserDAO<T, ID extends Serializable> extends JpaRepository<User, Long> {

    User save(User user);

    User findByEmail(String email);
}
