package Dao;

import Model.Funcionario;
import Model.Relatorio;
import util.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {

    // Método para inserir funcionário no banco de dados
    public void inserirFuncionario(Funcionario funcionario) {
        String query = "INSERT INTO funcionario (nome, cpf, cargo, telefone, endereco, data_nascimento) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DataBaseConnection.getConnection();  // Usando o método estático da classe DataBaseConnection
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setString(3, funcionario.getCargo());
            stmt.setString(4, funcionario.getTelefone());
            stmt.setString(5, funcionario.getEndereco());
            stmt.setString(6, funcionario.getDataNascimento());  // Adicionando a data de nascimento

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para gerar relatório dos funcionários
    public List<Relatorio> gerarRelatorio() {
        List<Relatorio> relatorios = new ArrayList<>();
        String query = "SELECT * FROM funcionario";  // Altere a consulta conforme necessário

        try (Connection conn = DataBaseConnection.getConnection();  // Usando o método estático da classe DataBaseConnection
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            // Enquanto houver registros de funcionários, crie o relatório
            while (rs.next()) {
                Relatorio relatorio = new Relatorio(
                        rs.getString("nome"),          // Nome do funcionário
                        rs.getString("cpf"),           // CPF do funcionário
                        rs.getString("cargo"),         // Cargo do funcionário
                        rs.getString("telefone"),      // Telefone do funcionário
                        rs.getString("endereco"),      // Endereço do funcionário
                        rs.getString("data_nascimento") // Data de nascimento do funcionário
                );
                relatorios.add(relatorio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return relatorios;  // Retorna a lista de relatórios
    }
}
