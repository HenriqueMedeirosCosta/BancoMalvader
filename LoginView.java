package view;

import Controller.UsuarioController;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {

    private JTextField txtCpf;
    private JPasswordField txtSenha;
    private JButton btnLogin;

    public LoginView() {
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

        add(panel);
    }

    private void autenticarUsuario() {
        String cpf = txtCpf.getText();
        String senha = String.valueOf(txtSenha.getPassword());
        String tipo = btnLogin.getText();

        // Chama o controlador para autenticar
        UsuarioController controller = new UsuarioController();
        boolean autenticado = controller.autenticar(cpf, senha, tipo); // Verifica se o login foi bem-sucedido

        if (autenticado) {
            JOptionPane.showMessageDialog(this, "Login bem-sucedido!");
            new view.MenuPrincipalView().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "CPF ou Senha incorretos.");
        }
    }

    public static void main(String[] args) {
        new LoginView().setVisible(true);
    }
}
