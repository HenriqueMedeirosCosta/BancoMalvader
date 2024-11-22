package view;

import javax.swing.*;
import java.awt.*;

public class AlterarFuncionarioView extends JFrame {

    public AlterarFuncionarioView() {
        setTitle("Alterar Dados do Funcionário");
        setSize(400, 350); // Aumentado para acomodar o novo campo de senha
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        JLabel lblCodigo = new JLabel("Novo Código:");
        JTextField txtCodigo = new JTextField();

        JLabel lblCargo = new JLabel("Novo Cargo:");
        JTextField txtCargo = new JTextField();

        JLabel lblTelefone = new JLabel("Novo Telefone:");
        JTextField txtTelefone = new JTextField();

        JLabel lblEndereco = new JLabel("Novo Endereço:");
        JTextField txtEndereco = new JTextField();

        JLabel lblSenha = new JLabel("Senha do Funcionário:");
        JTextField txtSenha = new JTextField(); // Campo para senha do funcionário

        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        btnSalvar.addActionListener(e -> {
            // Lógica para salvar as alterações diretamente, sem verificação de senha
            JOptionPane.showMessageDialog(this, "Dados do funcionário alterados com sucesso!");
            dispose();
        });

        btnCancelar.addActionListener(e -> dispose());

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.add(lblCodigo);
        panel.add(txtCodigo);
        panel.add(lblCargo);
        panel.add(txtCargo);
        panel.add(lblTelefone);
        panel.add(txtTelefone);
        panel.add(lblEndereco);
        panel.add(txtEndereco);
        panel.add(lblSenha);
        panel.add(txtSenha); // Campo de senha ainda exibido, mas sem verificação
        panel.add(btnSalvar);
        panel.add(btnCancelar);

        add(panel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        new AlterarFuncionarioView().setVisible(true);
    }
}
