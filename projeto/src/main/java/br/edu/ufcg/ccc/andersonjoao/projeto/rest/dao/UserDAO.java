package br.edu.ufcg.ccc.andersonjoao.projeto.rest.dao;

import br.edu.ufcg.ccc.andersonjoao.projeto.rest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDAO extends JpaRepository<User, String> {

    User save(User user);

    @Query(value="Select u from User u where u.email = pEmail")
    User findByEmail(@Param("pEmail") String email);
}
