package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ConsultarSubmenuView extends JFrame {
    public ConsultarSubmenuView() {
        // Configuração da janela
        setTitle("Submenu - Consultar Dados");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 10, 10));

        // Botões do submenu
        JButton btnConsultarConta = new JButton("Consultar Conta");
        JButton btnConsultarFuncionario = new JButton("Consultar Funcionário");
        JButton btnConsultarCliente = new JButton("Consultar Cliente");
        JButton btnVoltar = new JButton("Voltar ao Menu Principal");

        // Adicionando os botões à janela
        add(btnConsultarConta);
        add(btnConsultarFuncionario);
        add(btnConsultarCliente);
        add(btnVoltar);

        // Ações dos botões
        btnConsultarConta.addActionListener((ActionEvent e) -> {
            // Chama a tela de consulta de conta (precisa ser implementada)
            JOptionPane.showMessageDialog(this, "Consulta de conta não implementada.");
        });

        btnConsultarFuncionario.addActionListener((ActionEvent e) -> {
            // Chama a tela de consulta de funcionário (precisa ser implementada)
            JOptionPane.showMessageDialog(this, "Consulta de funcionário não implementada.");
        });

        btnConsultarCliente.addActionListener((ActionEvent e) -> {
            // Abre a tela ConsultarDadosView para consulta de cliente
            new view.ConsultarDadosView().setVisible(true);
            dispose(); // Fecha o submenu
        });

        btnVoltar.addActionListener((ActionEvent e) -> {
            // Fecha o submenu
            dispose();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ConsultarSubmenuView().setVisible(true));
    }
}
