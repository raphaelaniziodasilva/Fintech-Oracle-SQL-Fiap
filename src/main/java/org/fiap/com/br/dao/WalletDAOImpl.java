package org.fiap.com.br.dao;
import org.fiap.com.br.database.ConnectionFactory;
import org.fiap.com.br.entity.Wallet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WalletDAOImpl implements WalletDAO {
    private Connection connection;
    private PreparedStatement pstmt = null;

    public WalletDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Wallet wallet) {
        try {
            // Preparando a consulta SQL para inserção: criação
            String sql = "INSERT INTO CARTEIRA (cd_carteira, usuario_cd_usuario, nm_carteira, saldo, gastos)" +
                    "VALUES (?, ?, ?, ?, ?)";

            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, wallet.getCode());
            pstmt.setInt(2, wallet.getUserCode());
            pstmt.setString(3, wallet.getName());
            pstmt.setDouble(4, wallet.getBalance());
            pstmt.setDouble(5, wallet.getExpenses());

            // Execute a consulta SQL para inserir a carteira
            pstmt.executeUpdate();
        } catch (SQLException e) {
            // Lidar com qualquer exceções de erro relacionadas ao banco de dados
            e.printStackTrace();
        } finally {
            // Fecha o PreparedStatement assim que a carteira for adicionada
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Fecha a conexão com o banco de dados assim que a carteira for adicionada
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Wallet> walletList() {
        List<Wallet> walletList = new ArrayList<>();

        try {
            // Preparando a consulta SQL para recuperar todas as carteiras
            String sql = "SELECT * FROM CARTEIRA";
            pstmt = connection.prepareStatement(sql);

            // Executando a consulta para recuperar os dados da carteira
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                // Criando uma carteira com as informações encontradas
                Wallet wallet = new Wallet();
                wallet.setCode(resultSet.getInt("cd_carteira"));
                wallet.setUserCode(resultSet.getInt("usuario_cd_usuario"));
                wallet.setName(resultSet.getString("nm_carteira"));
                wallet.setBalance(resultSet.getDouble("saldo"));
                wallet.setExpenses(resultSet.getDouble("gastos"));

                // Adicionar carteira na lista
                walletList.add(wallet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return walletList;
    }

    @Override
    public Wallet searchWalletCode(int code) {
        Wallet wallet = null;

        try {
            // Preparando a consulta SQL para recuperar uma carteira pelo código
            String sql = "SELECT * FROM CARTEIRA WHERE cd_carteira = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, code);

            // Executando a consulta para recuperar os dados da carteira
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                wallet = new Wallet();
                wallet.setCode(resultSet.getInt("cd_carteira"));
                wallet.setUserCode(resultSet.getInt("usuario_cd_usuario"));
                wallet.setName(resultSet.getString("nm_carteira"));
                wallet.setBalance(resultSet.getDouble("saldo"));
                wallet.setExpenses(resultSet.getDouble("gastos"));
            } else {
                // Se nenhuma carteira for encontrada
                System.out.println("Carteira não existe");
            }
        } catch (SQLException e) {
            // Lidar com quaisquer exceções relacionadas ao banco de dados
            e.printStackTrace();
        } finally {
            // Fechando o PreparedStatement e a conexão com o banco de dados
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return wallet;
    }

    @Override
    public void update(Wallet wallet) {
        try {
            // Preparando a consulta SQL para atualizar as informações de uma carteira
            String sql = "UPDATE CARTEIRA SET usuario_cd_usuario = ?, nm_carteira = ?, saldo = ?, gastos = ? WHERE cd_carteira = ?";
            pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, wallet.getUserCode());
            pstmt.setString(2, wallet.getName());
            pstmt.setDouble(3, wallet.getBalance());
            pstmt.setDouble(4, wallet.getExpenses());
            pstmt.setInt(5, wallet.getCode());

            // Executando a consulta para atualizar as informações do usuário
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void remove(int code) {
        try {
            // preparando a consulta SQL para excluir um usuário pelo seu código
            String sql = "DELETE FROM CARTEIRA WHERE cd_carteira = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, code);

            // Executa a consulta para excluir o usuário
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
