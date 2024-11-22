package view;

import javax.swing.*;
import java.awt.*;

public class AlterarClienteView extends JFrame {

    public AlterarClienteView() {
        setTitle("Alterar Dados do Cliente");
        setSize(400, 350); // Aumentado para acomodar o campo de senha
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        JLabel lblTelefone = new JLabel("Novo Telefone:");
        JTextField txtTelefone = new JTextField();

        JLabel lblEndereco = new JLabel("Novo Endereço:");
        JTextField txtEndereco = new JTextField();

        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        btnSalvar.addActionListener(e -> {
            // Salvar diretamente sem verificar a senha
            JOptionPane.showMessageDialog(this, "Dados do cliente alterados com sucesso!");
            dispose();
        });

        btnCancelar.addActionListener(e -> dispose());

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10)); // Ajustado para 3 linhas
        panel.add(lblTelefone);
        panel.add(txtTelefone);
        panel.add(lblEndereco);
        panel.add(txtEndereco);
        panel.add(btnSalvar);
        panel.add(btnCancelar);

        add(panel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        new AlterarClienteView().setVisible(true);
    }
}
