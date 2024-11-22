package view;

import Controller.UsuarioController;
import javax.swing.*;
import java.awt.*;

public class AutentificacaoView extends JFrame {

    private UsuarioController usuarioController;
    private JTextField cpfField;
    private JPasswordField senhaField;
    private JButton btnEntrarCliente;
    private JButton btnEntrarFuncionario;
    private JButton btnSair;

    public AutentificacaoView() {
        usuarioController = new UsuarioController();

        // Configuração da janela principal
        setTitle("Autenticação");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        // Rótulos e campos de entrada
        JLabel lblCpf = new JLabel("CPF:");
        cpfField = new JTextField(15);
        JLabel lblSenha = new JLabel("Senha:");
        senhaField = new JPasswordField(15);

        btnEntrarCliente = new JButton("Entrar como Cliente");
        btnEntrarFuncionario = new JButton("Entrar como Funcionário");
        btnSair = new JButton("Sair do Programa");

        // Adicionando componentes ao layout
        gbc.insets = new Insets(10, 10, 5, 10);

        gbc.gridx = 0; gbc.gridy = 0;
        add(lblCpf, gbc);
        gbc.gridx = 1;
        add(cpfField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(lblSenha, gbc);
        gbc.gridx = 1;
        add(senhaField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        add(btnEntrarCliente, gbc);

        gbc.gridy = 3;
        add(btnEntrarFuncionario, gbc);

        gbc.gridy = 4;
        add(btnSair, gbc);

        // Ações dos botões
        btnEntrarCliente.addActionListener(e -> autenticarUsuario("CLIENTE"));
        btnEntrarFuncionario.addActionListener(e -> autenticarUsuario("FUNCIONÁRIO"));
        btnSair.addActionListener(e -> System.exit(0));
    }

    // Método para autenticar o usuário (cliente ou funcionário)
    private void autenticarUsuario(String tipo) {
        String cpf = cpfField.getText().trim();
        String senha = new String(senhaField.getPassword()).trim();

        // Verificar se CPF ou senha estão vazios
        if (cpf.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.");
            return;
        }

        // Realizar a autenticação chamando o método adequado da classe UsuarioController
        boolean autenticado = usuarioController.autenticar(cpf, senha, tipo);  // Passa o tipo (CLIENTE ou FUNCIONÁRIO)

        if (autenticado) {
            JOptionPane.showMessageDialog(this, "Autenticação bem-sucedida!");

            // Abrir o menu apropriado após a autenticação
            if (tipo.equals("CLIENTE")) {
                new view.ClienteMenuView().setVisible(true);  // Exemplo, você pode ter outra verificação aqui se necessário
            } else if (tipo.equals("FUNCIONÁRIO")) {
                new view.FuncionarioMenuView().setVisible(true);  // Menu de funcionário
            }
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Credenciais inválidas.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AutentificacaoView tela = new AutentificacaoView();
            tela.setVisible(true);
        });
    }
}
