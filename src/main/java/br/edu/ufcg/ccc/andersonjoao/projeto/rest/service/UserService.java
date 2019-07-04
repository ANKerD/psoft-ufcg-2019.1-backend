package br.edu.ufcg.ccc.andersonjoao.projeto.rest.service;

import br.edu.ufcg.ccc.andersonjoao.projeto.rest.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.ufcg.ccc.andersonjoao.projeto.rest.dao.UserDAO;


@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public UserService() {}


    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserModel findByEmail(String email) { return this.userDAO.findByEmail(email);}

    public UserModel save(UserModel userModel) { return this.userDAO.save(userModel);}
}
