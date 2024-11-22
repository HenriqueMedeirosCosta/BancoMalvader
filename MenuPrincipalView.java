package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipalView extends JFrame {

    public MenuPrincipalView() {
        setTitle("Banco Malvader - Menu Principal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        // Criação dos botões
        JButton btnCadastroCliente = new JButton("Cadastrar Cliente");
        JButton btnTransacao = new JButton("Realizar Transação");
        JButton btnRelatorio = new JButton("Gerar Relatório");

        // Ações dos botões
        btnCadastroCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Chama a tela de cadastro de cliente
                JOptionPane.showMessageDialog(MenuPrincipalView.this, "Abrindo Cadastro de Cliente...");
            }
        });

        btnTransacao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Chama a tela de transação
                JOptionPane.showMessageDialog(MenuPrincipalView.this, "Abrindo Transação...");
            }
        });

        btnRelatorio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Chama a tela de relatórios
                JOptionPane.showMessageDialog(MenuPrincipalView.this, "Gerando Relatório...");
            }
        });

        // Layout para organizar os botões
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10)); // Usando espaçamento entre os botões
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Espaçamento interno
        panel.add(btnCadastroCliente);
        panel.add(btnTransacao);
        panel.add(btnRelatorio);

        // Adiciona o painel na janela
        add(panel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        new MenuPrincipalView().setVisible(true);
    }
}
