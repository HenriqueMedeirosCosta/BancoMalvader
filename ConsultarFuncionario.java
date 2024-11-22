package Controller;

import util.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultarFuncionario {

    public String exibirInfoFuncionario(String cpf) {
        String resultado = "";
        String query = """
            SELECT 
                u.nome, u.cpf, u.telefone, u.tipo_usuario, DATE_FORMAT(u.data_nascimento, '%d/%m/%Y') AS data_nascimento,
                f.codigo_funcionario, f.cargo,
                IFNULL(CONCAT(e.local, ', ', e.numero_casa, ', ', e.bairro, ', ', e.cidade, ', ', e.estado), 'Endereço não cadastrado') AS endereco_completo
            FROM usuario u
            INNER JOIN funcionario f ON u.id_usuario = f.id_usuario
            LEFT JOIN endereco e ON u.id_usuario = e.id_usuario
            WHERE TRIM(u.cpf) = ?
        """;

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, cpf.trim()); // Remove espaços extras no CPF
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String tipoUsuario = rs.getString("tipo_usuario");
                String dataNascimento = rs.getString("data_nascimento");
                String codigoFuncionario = rs.getString("codigo_funcionario");
                String cargo = rs.getString("cargo");
                String enderecoCompleto = rs.getString("endereco_completo");

                resultado = String.format("""
                        Nome: %s
                        CPF: %s
                        Data de Nascimento: %s
                        Telefone: %s
                        Tipo de Usuário: %s
                        Código do Funcionário: %s
                        Cargo: %s
                        Endereço Completo: %s
                        """,
                        nome, cpf, dataNascimento, telefone, tipoUsuario, codigoFuncionario, cargo, enderecoCompleto);
            } else {
                resultado = "Funcionário não encontrado.";
            }
        } catch (SQLException e) {
            resultado = "Erro ao consultar funcionário: " + e.getMessage();
        }
        return resultado;
    }

    public void setVisible(boolean b) {
    }
}
