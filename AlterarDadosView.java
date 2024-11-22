package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlterarDadosView extends JFrame {

    private JButton btnAlterarConta;
    private JButton btnAlterarCliente;
    private JButton btnAlterarFuncionario;
    private JButton btnVoltar;

    public AlterarDadosView() {
        setTitle("Banco Malvader - Alterar Dados");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Solicita a senha ao abrir a tela
        String senha = JOptionPane.showInputDialog(this, "Digite a senha para acessar a tela de alteração de dados:");

        if ("senha123".equals(senha)) {
            initComponents(); // Se a senha for válida, inicializa os componentes
        } else {
            JOptionPane.showMessageDialog(this, "Senha incorreta. Acesso negado.", "Erro", JOptionPane.ERROR_MESSAGE);
            dispose(); // Fecha a janela caso a senha seja incorreta
        }
    }

    private void initComponents() {
        btnAlterarConta = new JButton("Alterar Conta");
        btnAlterarCliente = new JButton("Alterar Cliente");
        btnAlterarFuncionario = new JButton("Alterar Funcionário");
        btnVoltar = new JButton("Voltar");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        panel.add(btnAlterarConta);
        panel.add(btnAlterarCliente);
        panel.add(btnAlterarFuncionario);
        panel.add(btnVoltar);

        // Ação para o botão "Alterar Conta"
        btnAlterarConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solicitarSenha(() -> {
                    new view.AlterarContaView().setVisible(true);
                    dispose();
                });
            }
        });

        // Ação para o botão "Alterar Cliente"
        btnAlterarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solicitarSenha(() -> {
                    new view.AlterarClienteView().setVisible(true);
                    dispose();
                });
            }
        });

        // Ação para o botão "Alterar Funcionário"
        btnAlterarFuncionario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solicitarSenha(() -> {
                    new view.AlterarFuncionarioView().setVisible(true);
                    dispose();
                });
            }
        });

        // Ação para o botão "Voltar"
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new view.FuncionarioMenuView().setVisible(true);
                dispose();
            }
        });

        add(panel);
    }

    /**
     * Solicita a senha ao usuário e executa a ação caso a senha seja válida.
     *
     * @param acao A ação a ser executada caso a senha seja validada.
     */
    private void solicitarSenha(Runnable acao) {
        String senha = JOptionPane.showInputDialog(this, "Digite a senha para continuar:");
        if ("senha123".equals(senha)) {
            acao.run(); // Executa a ação passada como parâmetro
        } else {
            JOptionPane.showMessageDialog(this, "Senha incorreta. Acesso negado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AlterarDadosView().setVisible(true));
    }
}
