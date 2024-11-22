package Dao;

import Model.Relatorio;
import util.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RelatorioDAO {

    // Método para buscar relatórios no banco de dados
    public List<Relatorio> buscarRelatorios() {
        List<Relatorio> relatorios = new ArrayList<>();
        String query = "SELECT * FROM relatorios"; // Consulta SQL para buscar os relatórios

        try (Connection conn = DataBaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                // Cria um novo objeto Relatório a partir dos dados do banco
                Relatorio relatorio = new Relatorio(
                        rs.getString("id"),              // Passando o id como String
                        rs.getString("descricao"),       // Passando a descrição como String
                        rs.getString("data_inicio"),    // Passando a data de início como String
                        rs.getString("data_fim"),       // Passando a data de fim como String
                        rs.getString("status"),          // Passando o status como String
                        rs.getString("data_nascimento"));

                relatorios.add(relatorio);  // Adiciona à lista de relatórios
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return relatorios;
    }
}
