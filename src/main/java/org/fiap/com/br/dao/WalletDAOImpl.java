package org.fiap.com.br.dao;

import org.fiap.com.br.entity.Wallet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WalletDAOImpl implements WalletDAO {
    private final Connection connection;

    public WalletDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Wallet wallet) {
        if (connection == null) {
            throw new IllegalStateException("Conexão é nula");
        }

        try (PreparedStatement pstmt = connection.prepareStatement(
                "INSERT INTO CARTEIRA (cd_carteira, usuario_cd_usuario, nm_carteira, saldo, gastos, economia) VALUES (?, ?, ?, ?, ?, ?)")) {
            pstmt.setInt(1, wallet.getCode());
            pstmt.setInt(2, wallet.getUserCode());
            pstmt.setString(3, wallet.getName());
            pstmt.setDouble(4, wallet.getBalance());
            pstmt.setDouble(5, wallet.getExpenses());
            pstmt.setDouble(6, wallet.getEconomy());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar carteira", e);
        }
    }

    @Override
    public List<Wallet> walletList() {
        List<Wallet> walletList = new ArrayList<>();

        if (connection == null) {
            throw new IllegalStateException("Conexão é nula");
        }

        try (PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM CARTEIRA");
             ResultSet resultSet = pstmt.executeQuery()) {

            while (resultSet.next()) {
                Wallet wallet = extractWalletFromResultSet(resultSet);
                walletList.add(wallet);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar lista de carteiras", e);
        }
        return walletList;
    }

    @Override
    public Wallet searchWalletCode(int code) {
        Wallet wallet = null;

        if (connection == null) {
            throw new IllegalStateException("Conexão é nula");
        }

        try (PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM CARTEIRA WHERE cd_carteira = ?")) {
            pstmt.setInt(1, code);

            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    wallet = extractWalletFromResultSet(resultSet);
                } else {
                    System.out.println("Carteira não encontrada para o código: " + code);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao procurar carteira", e);
        }
        return wallet;
    }

    @Override
    public void update(Wallet wallet) {
        if (connection == null) {
            throw new IllegalStateException("Conexão é nula");
        }

        try (PreparedStatement pstmt = connection.prepareStatement(
                "UPDATE CARTEIRA SET usuario_cd_usuario = ?, nm_carteira = ?, saldo = ?, gastos = ?, economia = ? WHERE cd_carteira = ?"
        )) {

            pstmt.setInt(1, wallet.getUserCode());
            pstmt.setString(2, wallet.getName());
            pstmt.setDouble(3, wallet.getBalance());
            pstmt.setDouble(4, wallet.getExpenses());
            pstmt.setDouble(5, wallet.getEconomy());
            pstmt.setInt(6, wallet.getCode());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar carteira", e);
        }
    }

    @Override
    public void remove(int code) {
        if (connection == null) {
            throw new IllegalStateException("Conexão é nula");
        }

        try (PreparedStatement pstmt = connection.prepareStatement("DELETE FROM CARTEIRA WHERE cd_carteira = ?")) {
            pstmt.setInt(1, code);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover carteira", e);
        }
    }

    private Wallet extractWalletFromResultSet(ResultSet resultSet) throws SQLException {
        Wallet wallet = new Wallet();
        wallet.setCode(resultSet.getInt("cd_carteira"));
        wallet.setUserCode(resultSet.getInt("usuario_cd_usuario"));
        wallet.setName(resultSet.getString("nm_carteira"));
        wallet.setBalance(resultSet.getDouble("saldo"));
        wallet.setExpenses(resultSet.getDouble("gastos"));
        wallet.setEconomy(resultSet.getDouble("economia"));
        return wallet;
    }
}
