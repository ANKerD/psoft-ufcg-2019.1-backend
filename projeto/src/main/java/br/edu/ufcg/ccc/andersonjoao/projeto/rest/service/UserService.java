package br.edu.ufcg.ccc.andersonjoao.projeto.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.ufcg.ccc.andersonjoao.projeto.rest.dao.UserDAO;
import br.edu.ufcg.ccc.andersonjoao.projeto.rest.model.User;


@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public UserService() {}


    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User findByEmail(String email) { return this.userDAO.findByEmail(email);}

    public User save(User user) { return this.userDAO.save(user);}
}
