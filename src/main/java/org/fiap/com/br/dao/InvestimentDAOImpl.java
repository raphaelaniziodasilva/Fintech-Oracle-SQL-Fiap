package org.fiap.com.br.dao;

import org.fiap.com.br.entity.Investment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InvestimentDAOImpl implements InvestimentDAO {
    private final Connection connection;

    public InvestimentDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Investment investment) {
        if (connection == null) {
            throw new IllegalStateException("Conexão é nula");
        }

        try (PreparedStatement pstmt = connection.prepareStatement(
                "INSERT INTO INVESTIMENTO (cd_investimento, dt_investimento, nm_investimento, valor, porcentagem, lucro) VALUES (?, ?, ?, ?, ?, ?)")) {
            pstmt.setInt(1, investment.getCode());
            pstmt.setDate(2, new java.sql.Date(investment.getInvestmentDate().getTime()));
            pstmt.setString(3, investment.getName());
            pstmt.setDouble(4, investment.getValue());
            pstmt.setInt(5, investment.getPercentage());
            pstmt.setDouble(6, investment.getProfit());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar investimento", e);
        }

    }

    @Override
    public List<Investment> investmentList() {
        List<Investment> investmentList = new ArrayList<>();

        if (connection == null) {
            throw new IllegalStateException("Conexão é nula");
        }

        try (PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM INVESTIMENTO");
             ResultSet resultSet = pstmt.executeQuery()) {

            while (resultSet.next()) {
                Investment investment = extractInvestmentFromResultSet(resultSet);
                investmentList.add(investment);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar lista de investimentos", e);
        }
        return investmentList;
    }

    @Override
    public Investment searchInvestmentCode(int code) {
        Investment investment = null;

        if (connection == null) {
            throw new IllegalStateException("Conexão é nula");
        }

        try (PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM INVESTIMENTO WHERE cd_investimento = ?")) {
            pstmt.setInt(1, code);

            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    investment = extractInvestmentFromResultSet(resultSet);
                } else {
                    System.out.println("Investimento não encontrada para o código: " + code);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao procurar investimento", e);
        }
        return investment;
    }

    @Override
    public void update(Investment investment) {
        if (connection == null) {
            throw new IllegalStateException("Conexão é nula");
        }

        try (PreparedStatement pstmt = connection.prepareStatement(
                "UPDATE INVESTIMENTO SET dt_investimento = ?, nm_investimento = ?, valor = ?, porcentagem = ?, lucro = ? WHERE cd_investimento = ?")) {

            pstmt.setDate(1, new java.sql.Date(investment.getInvestmentDate().getTime()));
            pstmt.setString(2, investment.getName());
            pstmt.setDouble(3, investment.getValue());
            pstmt.setInt(4, investment.getPercentage());
            pstmt.setDouble(5, investment.getProfit());
            pstmt.setInt(6, investment.getCode());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar investimento", e);
        }
    }

    @Override
    public void remove(int code) {
        if (connection == null) {
            throw new IllegalStateException("Conexão é nula");
        }

        try (PreparedStatement pstmt = connection.prepareStatement("DELETE FROM INVESTIMENTO WHERE cd_investimento = ?")) {
            pstmt.setInt(1, code);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover carteira", e);
        }
    }

    private Investment extractInvestmentFromResultSet(ResultSet resultSet) throws SQLException {
        Investment investment = new Investment();
        investment.setCode(resultSet.getInt("cd_investimento"));
        investment.setInvestmentDate(resultSet.getDate("dt_investimento"));
        investment.setName(resultSet.getString("nm_investimento"));
        investment.setValue(resultSet.getDouble("valor"));
        investment.setPercentage(resultSet.getInt("porcentagem"));
        investment.setProfit(resultSet.getDouble("lucro"));
        return investment;
    }
}
