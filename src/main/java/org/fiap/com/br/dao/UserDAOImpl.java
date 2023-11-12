package org.fiap.com.br.dao;
import org.fiap.com.br.database.ConnectionFactory;
import org.fiap.com.br.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// vamos criar UserDAOImpl e implementar a interface UserDAO
// vai ser responsável por encapsular todas as questões que se tratam desde a conexão com o banco de dados,
// até a manipulação dos dados da tabela USUARIO no banco de dados

public class UserDAOImpl implements UserDAO {
    private Connection connection;

    public UserDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User user) {
        if (connection == null) {
            return;
        }

        try (PreparedStatement pstmt = connection.prepareStatement(
                "INSERT INTO USUARIO (cd_usuario, nm_usuario, sobrenome_usuario, nr_rg, nr_cpf, " +
                        "dt_nascimento, nr_celular, nr_telefone, nr_celular1, nr_telefone1) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            pstmt.setInt(1, user.getCode());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getSurname());
            pstmt.setString(4, user.getRg());
            pstmt.setString(5, user.getCpf());
            pstmt.setDate(6, new java.sql.Date(user.getDateOfBirth().getTime()));
            pstmt.setString(7, user.getCellPhone());
            pstmt.setString(8, user.getTelephone());
            pstmt.setString(9, user.getCellPhone1());
            pstmt.setString(10, user.getTelephone1());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> userList() {
        List<User> userList = new ArrayList<>();

        if (this.connection == null) {
            System.out.println("Connection is null");
            return userList;
        }

        try (PreparedStatement pstmt = this.connection.prepareStatement("SELECT * FROM USUARIO");
             ResultSet resultSet = pstmt.executeQuery()) {

            while (resultSet.next()) {
                User user = new User();
                user.setCode(resultSet.getInt("cd_usuario"));
                user.setName(resultSet.getString("nm_usuario"));
                user.setSurname(resultSet.getString("sobrenome_usuario"));
                user.setRg(resultSet.getString("nr_rg"));
                user.setCpf(resultSet.getString("nr_cpf"));
                user.setDateOfBirth(resultSet.getDate("dt_nascimento"));
                user.setCellPhone(resultSet.getString("nr_celular"));
                user.setTelephone(resultSet.getString("nr_telefone"));
                user.setCellPhone1(resultSet.getString("nr_celular1"));
                user.setTelephone1(resultSet.getString("nr_telefone1"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }


    @Override
    public User searchUserCode(int code) {
        User user = null;

        if (connection == null) {
            return user;
        }

        try (PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM USUARIO WHERE cd_usuario = ?")) {
            pstmt.setInt(1, code);

            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    user = new User();
                    user.setCode(resultSet.getInt("cd_usuario"));
                    user.setName(resultSet.getString("nm_usuario"));
                    user.setSurname(resultSet.getString("sobrenome_usuario"));
                    user.setRg(resultSet.getString("nr_rg"));
                    user.setCpf(resultSet.getString("nr_cpf"));
                    user.setDateOfBirth(resultSet.getDate("dt_nascimento"));
                    user.setCellPhone(resultSet.getString("nr_celular"));
                    user.setTelephone(resultSet.getString("nr_telefone"));
                    user.setCellPhone1(resultSet.getString("nr_celular1"));
                    user.setTelephone1(resultSet.getString("nr_telefone1"));
                } else {
                    System.out.println("Usuário não existe");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }


    @Override
    public void update(User user) {
        if (connection == null) {
            return;
        }

        try (PreparedStatement pstmt = connection.prepareStatement(
                "UPDATE USUARIO SET nm_usuario = ?, sobrenome_usuario = ?, nr_rg = ?, nr_cpf = ?, " +
                        "dt_nascimento = ?, nr_celular = ?, nr_telefone = ?, nr_celular1 = ?, nr_telefone1 = ? " +
                        "WHERE cd_usuario = ?")) {

            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getSurname());
            pstmt.setString(3, user.getRg());
            pstmt.setString(4, user.getCpf());
            pstmt.setDate(5, new java.sql.Date(user.getDateOfBirth().getTime()));
            pstmt.setString(6, user.getCellPhone());
            pstmt.setString(7, user.getTelephone());
            pstmt.setString(8, user.getCellPhone1());
            pstmt.setString(9, user.getTelephone1());
            pstmt.setInt(10, user.getCode());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void remove(int code) {
        if (connection == null) {
            return;
        }

        try (PreparedStatement pstmt = connection.prepareStatement("DELETE FROM USUARIO WHERE cd_usuario = ?")) {
            pstmt.setInt(1, code);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
