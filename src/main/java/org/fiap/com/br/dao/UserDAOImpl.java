package org.fiap.com.br.dao;
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
    private final Connection connection;

    public UserDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User user) {
        if (connection == null) {
            throw new IllegalStateException("Conexão é nula");
        }

        try (PreparedStatement pstmt = connection.prepareStatement(
                "INSERT INTO USUARIO (cd_usuario, email, password, nm_usuario, sobrenome_usuario, nr_rg, nr_cpf, " +
                        "dt_nascimento, nr_celular, nr_telefone, nr_celular1, nr_telefone1) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            pstmt.setInt(1, user.getCode());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getName());
            pstmt.setString(5, user.getSurname());
            pstmt.setString(6, user.getRg());
            pstmt.setString(7, user.getCpf());
            pstmt.setDate(8, new java.sql.Date(user.getDateOfBirth().getTime()));
            pstmt.setString(9, user.getCellPhone());
            pstmt.setString(10, user.getTelephone());
            pstmt.setString(11, user.getCellPhone1());
            pstmt.setString(12, user.getTelephone1());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar usuário", e);
        }
    }

    @Override
    public List<User> userList() {
        List<User> userList = new ArrayList<>();

        if (this.connection == null) {
            System.out.println("Conexão é nula");
            return userList;
        }

        try (PreparedStatement pstmt = this.connection.prepareStatement("SELECT * FROM USUARIO");
             ResultSet resultSet = pstmt.executeQuery()) {

            while (resultSet.next()) {
                User user = extractUserFromResultSet(resultSet);
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar lista de usuários", e);
        }
        return userList;
    }

    @Override
    public User searchUserCode(int code) {
        User user = null;

        if (connection == null) {
            throw new IllegalStateException("Conexão é nula");
        }

        try (PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM USUARIO WHERE cd_usuario = ?")) {
            pstmt.setInt(1, code);

            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    user = extractUserFromResultSet(resultSet);
                } else {
                    System.out.println("Usuário não encontrada para o código: " + code);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao procurar usuário", e);
        }
        return user;
    }


    @Override
    public void update(User user) {
        if (connection == null) {
            throw new IllegalStateException("Conexão é nula");
        }

        try (PreparedStatement pstmt = connection.prepareStatement(
                "UPDATE USUARIO SET email = ?, password = ?, nm_usuario = ?, sobrenome_usuario = ?, " +
                        "nr_rg = ?, nr_cpf = ?, dt_nascimento = ?, nr_celular = ?, nr_telefone = ?, " +
                        "nr_celular1 = ?, nr_telefone1 = ? WHERE cd_usuario = ?")) {

            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getSurname());
            pstmt.setString(5, user.getRg());
            pstmt.setString(6, user.getCpf());
            pstmt.setDate(7, new java.sql.Date(user.getDateOfBirth().getTime()));
            pstmt.setString(8, user.getCellPhone());
            pstmt.setString(9, user.getTelephone());
            pstmt.setString(10, user.getCellPhone1());
            pstmt.setString(11, user.getTelephone1());
            pstmt.setInt(12, user.getCode());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar usuário", e);
        }
    }


    @Override
    public void remove(int code) {
        if (connection == null) {
            throw new IllegalStateException("Conexão é nula");
        }

        try (PreparedStatement pstmt = connection.prepareStatement("DELETE FROM USUARIO WHERE cd_usuario = ?")) {
            pstmt.setInt(1, code);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover usuário", e);
        }
    }

    @Override
    public String login(String email, String password) {
        if (connection == null) {
            throw new IllegalStateException("Conexão é nula");
        }

        try (PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM USUARIO WHERE email = ? AND password = ?")) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);

            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    String storedEmail = resultSet.getString("email");
                    String storedPassword = resultSet.getString("password");
                    if (storedEmail.equals(email) && storedPassword.equals(password)) {
                        return "Login bem-sucedido";
                    } else {
                        return "Credenciais inválidas";
                    }
                } else {
                    return "Credenciais inválidas";
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro durante o login";
        }
    }

    private User extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setCode(resultSet.getInt("cd_usuario"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setName(resultSet.getString("nm_usuario"));
        user.setSurname(resultSet.getString("sobrenome_usuario"));
        user.setRg(resultSet.getString("nr_rg"));
        user.setCpf(resultSet.getString("nr_cpf"));
        user.setDateOfBirth(resultSet.getDate("dt_nascimento"));
        user.setCellPhone(resultSet.getString("nr_celular"));
        user.setTelephone(resultSet.getString("nr_telefone"));
        user.setCellPhone1(resultSet.getString("nr_celular1"));
        user.setTelephone1(resultSet.getString("nr_telefone1"));
        return user;
    }
}
