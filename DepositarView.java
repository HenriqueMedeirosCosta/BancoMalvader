package view;

import Controller.ContaController;
import util.DataBaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepositarView extends JFrame {

    private JTextField txtValorDeposito;
    private String cpfClienteLogado; // CPF do cliente logado
    private String numeroConta; // Número da conta do cliente

    // Construtor
    public DepositarView() {
        // Obtém o cliente logado a partir do banco de dados
        carregarClienteLogado();

        setTitle("Realizar Depósito");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void carregarClienteLogado() {
        // Aqui você deve obter o CPF e número da conta do cliente logado do banco de dados
        try (Connection connection = DataBaseConnection.getConnection()) {
            String query = "SELECT cpf, numero_conta FROM clientes WHERE logado = 1"; // Exemplo de consulta
            try (PreparedStatement stmt = connection.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    this.cpfClienteLogado = rs.getString("cpf");
                    this.numeroConta = rs.getString("numero_conta");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar dados do cliente.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initComponents() {
        txtValorDeposito = new JTextField(20);

        JButton btnDepositar = new JButton("Depositar");
        btnDepositar.addActionListener(e -> realizarDeposito());

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        // Adiciona o campo de texto para o valor do depósito
        panel.add(new JLabel("Valor do Depósito:"));
        panel.add(txtValorDeposito);

        // Adiciona o botão de depósito
        panel.add(btnDepositar);

        // Adiciona o painel à janela
        add(panel);
    }

    private void realizarDeposito() {
        try {
            // Pega o valor digitado pelo cliente e tenta convertê-lo para um número
            double valor = Double.parseDouble(txtValorDeposito.getText());

            if (valor <= 0) {
                JOptionPane.showMessageDialog(this, "O valor do depósito deve ser maior que zero.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            ContaController contaController = new ContaController();
            contaController.realizarDeposito(cpfClienteLogado, numeroConta, valor); // Passando o CPF, número da conta e valor para o depósito

            JOptionPane.showMessageDialog(this, "Depósito realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            // Em caso de erro na conversão, avisa o usuário para inserir um número válido
            JOptionPane.showMessageDialog(this, "Valor inválido. Insira um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // A partir de agora, o cliente logado será carregado do banco de dados automaticamente
        new DepositarView().setVisible(true);
    }
}
