package util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConexaoBanco {

    // URL de conexão, usuário e senha do banco de dados
    private static final String URL = "jdbc:mysql://localhost:3306/bancomalvader";  // Altere para o seu banco
    private static final String USER = "root";  // Usuário do banco
    private static final String PASSWORD = "Enzo1537";  // Senha do banco (altere conforme necessário)

    // Método para obter a conexão com o banco de dados
    public static Connection getConnection() {
        try {
            // Estabelecendo a conexão com o banco de dados
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            return null;  // Retorna null caso ocorra algum erro na conexão
        }
    }

    // Método para desconectar do banco de dados (fecha a conexão)
    public static void desconectar(Connection conn) {
        if (conn != null) {
            try {
                conn.close();  // Fecha a conexão com o banco
            } catch (SQLException e) {
                System.out.println("Erro ao desconectar do banco de dados: " + e.getMessage());
            }
        }
    }

    // Método para inserir dados no banco
    public static void inserirFuncionario(String nome, String cpf, String cargo, String telefone, String endereco) {
        String query = "INSERT INTO funcionario (nome, cpf, cargo, telefone, endereco) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.setString(3, cargo);
            stmt.setString(4, telefone);
            stmt.setString(5, endereco);

            stmt.executeUpdate();
            System.out.println("Funcionário inserido com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir funcionário: " + e.getMessage());
        }
    }

    // Método para consultar todos os funcionários no banco
    public static List<String> consultarFuncionarios() {
        List<String> funcionarios = new ArrayList<>();
        String query = "SELECT * FROM funcionario";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String cargo = rs.getString("cargo");
                String telefone = rs.getString("telefone");
                String endereco = rs.getString("endereco");

                funcionarios.add("Nome: " + nome + ", CPF: " + cpf + ", Cargo: " + cargo +
                        ", Telefone: " + telefone + ", Endereço: " + endereco);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar funcionários: " + e.getMessage());
        }
        return funcionarios;
    }

    // Método para exibir todos os funcionários inseridos no banco
    public static void exibirFuncionarios() {
        List<String> funcionarios = consultarFuncionarios();

        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário encontrado.");
        } else {
            for (String funcionario : funcionarios) {
                System.out.println(funcionario);
            }
        }
    }

    // Método para atualizar dados de um funcionário no banco
    public static void atualizarFuncionario(int codigo, String nome, String cpf, String cargo, String telefone, String endereco) {
        String query = "UPDATE funcionario SET nome = ?, cpf = ?, cargo = ?, telefone = ?, endereco = ? WHERE codigo = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.setString(3, cargo);
            stmt.setString(4, telefone);
            stmt.setString(5, endereco);
            stmt.setInt(6, codigo);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Dados do funcionário atualizados com sucesso!");
            } else {
                System.out.println("Funcionário não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar dados do funcionário: " + e.getMessage());
        }
    }

    // Método para excluir um funcionário do banco
    public static void excluirFuncionario(int codigo) {
        String query = "DELETE FROM funcionario WHERE codigo = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, codigo);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Funcionário excluído com sucesso!");
            } else {
                System.out.println("Funcionário não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao excluir funcionário: " + e.getMessage());
        }
    }
}
