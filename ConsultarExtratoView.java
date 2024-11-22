package View;

import util.DataBaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultarExtratoView extends JFrame {

    private JTextField txtCpf;
    private JTextArea txtExtrato;
    private JButton btnVisualizarExtrato;

    // Construtor
    public ConsultarExtratoView() {
        setTitle("Visualizar Extrato");
        setSize(600, 400);  // Tamanho ajustado para melhor visualização
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        // Aumentando o tamanho do campo de CPF
        txtCpf = new JTextField(20);  // Campo com mais espaço para o CPF
        txtExtrato = new JTextArea(15, 50);  // Aumentando o campo do extrato
        txtExtrato.setEditable(false);  // O extrato não deve ser editável
        btnVisualizarExtrato = new JButton("Visualizar Extrato");

        // Painel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        // Painel para o CPF
        JPanel cpfPanel = new JPanel();
        cpfPanel.add(new JLabel("Digite o CPF:"));
        cpfPanel.add(txtCpf);

        // Adiciona o painel de CPF, área de extrato e botão de ação ao painel principal
        panel.add(cpfPanel);
        panel.add(new JScrollPane(txtExtrato)); // Scroll para o extrato
        panel.add(btnVisualizarExtrato);

        // Ação do botão
        btnVisualizarExtrato.addActionListener(e -> visualizarExtrato());

        add(panel);
    }

    private void visualizarExtrato() {
        String cpfClienteLogado = txtCpf.getText().trim();

        if (cpfClienteLogado.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira o CPF.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        StringBuilder extrato = new StringBuilder();
        extrato.append("Extrato bancário para o CPF: ").append(cpfClienteLogado).append("\n");

        try (Connection connection = DataBaseConnection.getConnection()) {
            // 1. Buscar o ID do cliente com base no CPF fornecido
            String queryCliente = "SELECT id_cliente FROM cliente WHERE cpf = ?";
            int idCliente = -1;
            try (PreparedStatement stmtCliente = connection.prepareStatement(queryCliente)) {
                stmtCliente.setString(1, cpfClienteLogado);
                try (ResultSet rsCliente = stmtCliente.executeQuery()) {
                    if (rsCliente.next()) {
                        idCliente = rsCliente.getInt("id_cliente");
                        System.out.println("Cliente encontrado: id_cliente = " + idCliente); // Debugging
                    } else {
                        System.out.println("Cliente não encontrado com o CPF: " + cpfClienteLogado);  // Debugging
                    }
                }
            }

            // Verifica se o cliente foi encontrado
            if (idCliente == -1) {
                JOptionPane.showMessageDialog(this, "Cliente não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 2. Buscar as contas associadas ao cliente (conta corrente ou poupança)
            String queryContas = "SELECT id_conta, numero_conta, tipo_conta, saldo FROM conta WHERE id_cliente = ?";
            try (PreparedStatement stmtContas = connection.prepareStatement(queryContas)) {
                stmtContas.setInt(1, idCliente);
                try (ResultSet rsContas = stmtContas.executeQuery()) {
                    if (!rsContas.next()) {
                        System.out.println("Nenhuma conta encontrada para o cliente id_cliente = " + idCliente);  // Debugging
                    }
                    do {
                        int idConta = rsContas.getInt("id_conta");
                        String numeroConta = rsContas.getString("numero_conta");
                        String tipoConta = rsContas.getString("tipo_conta");
                        double saldo = rsContas.getDouble("saldo");

                        extrato.append("\nConta: ").append(numeroConta).append(" - Tipo: ").append(tipoConta).append("\n");
                        extrato.append("Saldo atual: R$ ").append(String.format("%.2f", saldo)).append("\n");
                        extrato.append("----------------------------------\n");

                        // 3. Buscar transações para essa conta
                        String queryTransacoes = "SELECT tipo_transacao, valor, data_hora FROM transacao WHERE id_conta = ? ORDER BY data_hora DESC";
                        try (PreparedStatement stmtTransacoes = connection.prepareStatement(queryTransacoes)) {
                            stmtTransacoes.setInt(1, idConta);
                            try (ResultSet rsTransacoes = stmtTransacoes.executeQuery()) {
                                if (!rsTransacoes.next()) {
                                    System.out.println("Nenhuma transação encontrada para a conta: " + numeroConta);  // Debugging
                                }
                                do {
                                    String tipoTransacao = rsTransacoes.getString("tipo_transacao");
                                    double valor = rsTransacoes.getDouble("valor");
                                    String dataHora = rsTransacoes.getString("data_hora");

                                    extrato.append("Data: ").append(dataHora).append("\n");
                                    extrato.append("Tipo: ").append(tipoTransacao).append("\n");
                                    extrato.append("Valor: R$ ").append(String.format("%.2f", valor)).append("\n");
                                    extrato.append("----------------------------------\n");
                                } while (rsTransacoes.next());
                            }
                        }
                    } while (rsContas.next());
                }
            }

            // Caso não haja transações ou contas, exibe uma mensagem
            if (extrato.length() == 0) {
                extrato.append("Nenhuma transação encontrada.");
            }

            txtExtrato.setText(extrato.toString());

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar extrato.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Exibe a interface para o usuário informar o CPF
        new ConsultarExtratoView().setVisible(true);
    }
}
