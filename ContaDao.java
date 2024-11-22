package Dao;

import Model.Conta;
import util.DataBaseConnection;

import java.sql.*;

public class ContaDao {

    // Método para buscar a conta por CPF
    public Conta buscarContaPorCpf(String cpf) {
        String query = "SELECT c.id_conta, c.numero_conta, c.tipo_conta, c.saldo, c.id_cliente " +
                "FROM conta c " +
                "JOIN cliente cl ON c.id_cliente = cl.id_cliente " +
                "JOIN usuario u ON cl.id_usuario = u.id_usuario " +
                "WHERE u.cpf = ?";
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, cpf); // Define o CPF como parâmetro
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Conta conta = new Conta();
                    conta.setIdConta(rs.getInt("id_conta"));
                    conta.setNumero(rs.getString("numero_conta"));
                    conta.setTipoConta(rs.getString("tipo_conta"));
                    conta.setSaldo(rs.getDouble("saldo"));
                    conta.setIdCliente(rs.getInt("id_cliente"));
                    return conta;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Retorna null caso não encontre a conta
    }

    // Método para consultar saldo (mantido conforme seu código anterior)
    public double consultarSaldo(String cpf, String numeroConta) {
        String query = "SELECT saldo FROM conta WHERE numero_conta = ? AND id_cliente = (SELECT id_cliente FROM cliente cl JOIN usuario u ON cl.id_usuario = u.id_usuario WHERE u.cpf = ?)";
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, numeroConta); // Define o número da conta como parâmetro
            stmt.setString(2, cpf); // Define o CPF como parâmetro
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("saldo");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Retorna -1 se não encontrar o saldo
    }

    // Método para realizar depósito
    public void realizarDeposito(String numeroConta, double valor) {
        String query = "UPDATE conta SET saldo = saldo + ? WHERE numero_conta = ?";
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDouble(1, valor);
            stmt.setString(2, numeroConta);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para realizar saque
    public void realizarSaque(String numeroConta, double valor) {
        String query = "UPDATE conta SET saldo = saldo - ? WHERE numero_conta = ?";
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDouble(1, valor);
            stmt.setString(2, numeroConta);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para atualizar dados da conta
    public void atualizarConta(Conta conta) {
        String query = "UPDATE conta SET tipo_conta = ?, limite = ?, data_vencimento = ? WHERE numero_conta = ?";
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, conta.getTipoConta());
            stmt.setDouble(2, conta.getLimite());
            stmt.setString(3, conta.getDataVencimento());
            stmt.setString(4, conta.getNumero());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
