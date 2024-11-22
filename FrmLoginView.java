package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmLoginView extends JFrame {

    private JTextField txtCpf;
    private JPasswordField txtSenha;
    private JButton btnLogin;

    public FrmLoginView() {
        setTitle("Banco Malvader - Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        txtCpf = new JTextField();
        txtSenha = new JPasswordField();
        btnLogin = new JButton("Entrar");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("CPF:"));
        panel.add(txtCpf);
        panel.add(new JLabel("Senha:"));
        panel.add(txtSenha);
        panel.add(btnLogin);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                autenticarUsuario();
            }
        });

        // Use add(panel) instead of setContentPane(null)
        add(panel); // Adiciona diretamente o painel ao JFrame

    }

    private void autenticarUsuario() {
        String cpf = txtCpf.getText();
        String senha = String.valueOf(txtSenha.getPassword());

        // A lógica de autenticação vai aqui

        JOptionPane.showMessageDialog(this, "Login bem-sucedido!");
        // Aqui você pode abrir a próxima tela após o login
    }

    public static void main(String[] args) {
        new FrmLoginView().setVisible(true);
    }
}
