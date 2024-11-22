package view;

import javax.swing.*;
import java.awt.*;

public class ConsultarSaqueView extends JFrame {

    public ConsultarSaqueView() {
        setTitle("Realizar Saque");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        // Componentes da tela de saque
        JLabel lblCpf = new JLabel("CPF: ");
        JTextField txtCpf = new JTextField(11);
        JLabel lblValor = new JLabel("Valor do Saque: ");
        JTextField txtValor = new JTextField(10);
        JButton btnSacar = new JButton("Sacar");

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(lblCpf);
        panel.add(txtCpf);
        panel.add(lblValor);
        panel.add(txtValor);
        panel.add(new JLabel("")); // Campo vazio para alinhamento
        panel.add(btnSacar);

        // Adicionando ação para o botão "Sacar"
        btnSacar.addActionListener(e -> {
            String cpf = txtCpf.getText();
            double valor;

            try {
                valor = Double.parseDouble(txtValor.getText());

                if (cpf.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Por favor, insira o CPF!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (valor <= 0) {
                    JOptionPane.showMessageDialog(this, "O valor do saque deve ser maior que zero!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Aqui você pode adicionar a lógica para realizar o saque no banco de dados.
                // Por exemplo, chamando o controller ou DAO responsável.
                // Exemplo fictício:
                // boolean sucesso = ContaController.realizarSaque(cpf, valor);
                // if (sucesso) {
                JOptionPane.showMessageDialog(this, "Saque de R$ " + valor + " realizado com sucesso!");
                // } else {
                //     JOptionPane.showMessageDialog(this, "Erro ao realizar o saque. Verifique os dados inseridos.", "Erro", JOptionPane.ERROR_MESSAGE);
                // }

                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Insira um valor numérico válido para o saque!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(panel);
    }

    public static void main(String[] args) {
        new ConsultarSaqueView().setVisible(true);
    }
}
