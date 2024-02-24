package org.fiap.com.br.dao;

import org.fiap.com.br.entity.User;

import java.util.List;

// vamos criar uma interface UserDAO que vai estabelecer um contrato para que o DAO implemente os métodos
// quem for implementar essa interface UserDAO vai ter que obrigatoriamente implementar seus métodos
public interface UserDAO {
    void create (User user);
    List<User> userList();
    User searchUserCode (int code);
    void update (User user);
    void remove (int code);
    String login(String email, String password);

}

// Agora crie um arquivo chamado UserDAOImpl e implemente a interface UserDAO
