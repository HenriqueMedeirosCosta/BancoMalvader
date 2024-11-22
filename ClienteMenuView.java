package view;

import util.DataBaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ClienteMenuView extends JFrame {

    private String cpfClienteLogado; // Armazena o CPF do cliente logado
    private String numeroConta; // Número da conta do cliente

    // Construtor padrão
    public ClienteMenuView() {
        setTitle("Banco Malvader - Menu Cliente");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    // Método para configurar o CPF do cliente logado
    public void setCpfClienteLogado(String cpfClienteLogado, String numeroConta) {
        this.cpfClienteLogado = cpfClienteLogado;
        this.numeroConta = numeroConta;
    }

    // Método para obter o CPF do cliente logado (caso necessário)
    public String getCpfClienteLogado() {
        return cpfClienteLogado;
    }

    // Inicialização dos componentes da interface gráfica
    private void initComponents() {
        JButton btnSaldo = new JButton("Consultar Saldo");
        JButton btnDeposito = new JButton("Realizar Depósito");
        JButton btnSaque = new JButton("Realizar Saque");
        JButton btnExtrato = new JButton("Consultar Extrato");
        JButton btnConsultarLimite = new JButton("Consultar Limite");
        JButton btnSair = new JButton("Sair");

        JPanel panel = new JPanel(new GridLayout(6, 1));
        panel.add(btnSaldo);
        panel.add(btnDeposito);
        panel.add(btnSaque);
        panel.add(btnExtrato);
        panel.add(btnConsultarLimite);
        panel.add(btnSair);

        // Ações para os botões
        btnSaldo.addActionListener(e -> consultarSaldo());
        btnDeposito.addActionListener(e -> realizarDeposito());
        btnSaque.addActionListener(e -> realizarSaque());
        btnExtrato.addActionListener(e -> consultarExtrato());
        btnConsultarLimite.addActionListener(e -> consultarLimite());
        btnSair.addActionListener(e -> sair());

        add(panel);
    }

    // Método para consultar saldo
    private void consultarSaldo() {
        String senha = JOptionPane.showInputDialog("Digite sua senha:");
        if ("senha123".equals(senha)) { // Validação fictícia para exemplo
            try (Connection conn = DataBaseConnection.getConnection()) {
                // Verifique a conexão
                if (conn == null) {
                    JOptionPane.showMessageDialog(this, "Falha na conexão com o banco de dados.");
                    return;
                }

                // Exibe dados para depuração
                System.out.println("Número da conta: " + numeroConta);
                System.out.println("CPF do cliente: " + cpfClienteLogado);

                // SQL ajustado, garantindo que o CPF seja o correto e o número da conta esteja formatado corretamente
                String sql = "SELECT saldo FROM conta c "
                        + "INNER JOIN cliente cl ON c.id_cliente = cl.id_cliente "
                        + "INNER JOIN usuario u ON cl.id_usuario = u.id_usuario "
                        + "WHERE c.numero_conta = ? AND u.cpf = ?";

                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, numeroConta.trim());  // Certifique-se de que o número da conta está correto
                    stmt.setString(2, cpfClienteLogado.trim());  // O CPF deve ser passado sem formatação

                    // Depuração para garantir que a consulta esteja sendo chamada corretamente
                    System.out.println("Executando consulta SQL: " + stmt.toString());

                    try (ResultSet rs = stmt.executeQuery()) {
                        if (rs.next()) {
                            double saldo = rs.getDouble("saldo");
                            JOptionPane.showMessageDialog(this, "Saldo da conta " + numeroConta + " (CPF: " + cpfClienteLogado + "): R$ " + saldo);
                        } else {
                            JOptionPane.showMessageDialog(this, "Conta não encontrada!");
                        }
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro ao consultar saldo: " + e.getMessage());
                e.printStackTrace();  // Mostra a stack trace para mais detalhes sobre o erro
            }
        } else {
            JOptionPane.showMessageDialog(this, "Senha inválida!");
        }
    }

    // Método para realizar depósito
    private void realizarDeposito() {
        String valorStr = JOptionPane.showInputDialog("Digite o valor do depósito na conta " + numeroConta + ":");
        try {
            double valor = Double.parseDouble(valorStr);
            if (valor > 0) {
                JOptionPane.showMessageDialog(this, "Depósito de R$ " + valor + " na conta " + numeroConta + " realizado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Valor inválido!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um valor numérico.");
        }
    }

    // Método para realizar saque
    private void realizarSaque() {
        String senha = JOptionPane.showInputDialog("Digite sua senha:");
        if ("senha123".equals(senha)) { // Validação fictícia para exemplo
            String valorStr = JOptionPane.showInputDialog("Digite o valor do saque na conta " + numeroConta + ":");
            try {
                double valor = Double.parseDouble(valorStr);
                if (valor > 0) {
                    JOptionPane.showMessageDialog(this, "Saque de R$ " + valor + " na conta " + numeroConta + " realizado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(this, "Valor inválido!");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Por favor, insira um valor numérico.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Senha inválida!");
        }
    }

    // Método para consultar extrato
    private void consultarExtrato() {
        String senha = JOptionPane.showInputDialog("Digite sua senha:");
        if ("senha123".equals(senha)) { // Validação fictícia para exemplo
            JOptionPane.showMessageDialog(this, "Exibindo extrato da conta " + numeroConta + " (CPF: " + cpfClienteLogado + ").");
        } else {
            JOptionPane.showMessageDialog(this, "Senha inválida!");
        }
    }

    // Método para consultar limite
    private void consultarLimite() {
        String senha = JOptionPane.showInputDialog("Digite sua senha:");
        if ("senha123".equals(senha)) { // Validação fictícia para exemplo
            JOptionPane.showMessageDialog(this, "Limite disponível da conta " + numeroConta + ": R$ 5000,00");
        } else {
            JOptionPane.showMessageDialog(this, "Senha inválida!");
        }
    }

    // Método para sair (voltar para o login)
    private void sair() {
        view.LoginView loginView = new view.LoginView();
        loginView.setVisible(true);
        dispose();
    }

    // Método principal para testes (pode ser removido em produção)
    public static void main(String[] args) {
        ClienteMenuView menu = new ClienteMenuView();
        menu.setCpfClienteLogado("98765432100", "789012"); // Simula o CPF do cliente logado e número da conta
        menu.setVisible(true);
    }
}
