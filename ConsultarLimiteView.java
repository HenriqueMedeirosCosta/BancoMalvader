package view;

import Controller.ContaController;
import Model.Conta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConsultarLimiteView extends JFrame {

    private JTextArea txtLimite;
    private JButton btnConsultar;
    private String cpfClienteLogado; // CPF do cliente logado

    // Construtor modificado para aceitar o CPF do cliente logado
    public ConsultarLimiteView(String cpfClienteLogado) {
        this.cpfClienteLogado = cpfClienteLogado; // Armazena o CPF do cliente logado
        setTitle("Consultar Limite");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        txtLimite = new JTextArea(5, 30);
        txtLimite.setEditable(false);
        btnConsultar = new JButton("Consultar Limite");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        panel.add(btnConsultar);
        panel.add(new JScrollPane(txtLimite));

        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultarLimite();
            }
        });

        add(panel);
    }

    private void consultarLimite() {
        // Usa o CPF do cliente logado para realizar a consulta
        ContaController contaController = new ContaController();
        Conta conta = contaController.consultarContaPorCpf(cpfClienteLogado); // Método específico para buscar por CPF

        if (conta != null && "CORRENTE".equals(conta.getTipoConta())) {
            txtLimite.setText("Limite disponível: R$ " + conta.getLimite());
        } else if (conta != null && "POUPANCA".equals(conta.getTipoConta())) {
            txtLimite.setText("Conta Poupança não possui limite.");
        } else {
            txtLimite.setText("Conta não encontrada.");
        }
    }

    public static void main(String[] args) {
        // Exemplo de CPF do cliente logado
        String cpfClienteLogado = "12345678900";
        new ConsultarLimiteView(cpfClienteLogado).setVisible(true);
    }
}
