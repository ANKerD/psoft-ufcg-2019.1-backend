package br.edu.ufcg.ccc.andersonjoao.projeto.rest.dao;

import br.edu.ufcg.ccc.andersonjoao.projeto.rest.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDAO extends JpaRepository<UserModel, String> {

    UserModel save(UserModel userModel);

    @Query(value="Select u from UserModel u where u.email=:pemail")
    UserModel findByEmail(@Param("pemail") String email);
}
