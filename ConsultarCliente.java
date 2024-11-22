package Controller;

import Model.Cliente;
import util.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultarCliente {
    private List<Cliente> clientes;

    public ConsultarCliente() {
        clientes = new ArrayList<>();
        inicializarClientes();
    }

    private void inicializarClientes() {
        String query = """
            SELECT u.nome, u.cpf, e.local, e.numero_casa, e.bairro, e.cidade, e.estado, u.telefone, c.saldo
            FROM usuario u
            INNER JOIN cliente cl ON u.id_usuario = cl.id_usuario
            INNER JOIN endereco e ON u.id_usuario = e.id_usuario
            LEFT JOIN conta c ON cl.id_cliente = c.id_cliente
        """;

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String endereco = String.format(
                        "%s, %d, %s, %s, %s",
                        rs.getString("local"),
                        rs.getInt("numero_casa"),
                        rs.getString("bairro"),
                        rs.getString("cidade"),
                        rs.getString("estado")
                );
                String telefone = rs.getString("telefone");
                double saldo = rs.getDouble("saldo");

                // Criação do objeto Cliente e adição à lista
                Cliente cliente = new Cliente(nome, cpf, endereco, telefone);
                cliente.setSaldo(saldo); // Atualiza o saldo do cliente
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao carregar clientes do banco de dados: " + e.getMessage());
        }
    }

    public Cliente consultarClientePorCPF(String cpf) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }

    public String exibirInfoCliente(String cpf) {
        Cliente cliente = consultarClientePorCPF(cpf);
        if (cliente != null) {
            return "Nome: " + cliente.getNome() + "\n" +
                    "CPF: " + cliente.getCpf() + "\n" +
                    "Endereço: " + cliente.getEndereco() + "\n" +
                    "Telefone: " + cliente.getTelefone() + "\n" +
                    "Saldo: R$ " + cliente.getSaldo();
        } else {
            return "Cliente não encontrado.";
        }
    }
}
