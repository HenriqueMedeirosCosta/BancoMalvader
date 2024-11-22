package view;

import Controller.FuncionarioController;
import Model.Relatorio;


import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GerarRelatorioView extends JFrame {
    private JTextArea textAreaRelatorio;
    private FuncionarioController controller;

    public GerarRelatorioView() {
        setTitle("Gerar Relatório");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        controller = new FuncionarioController();

        textAreaRelatorio = new JTextArea();
        textAreaRelatorio.setEditable(false);
        add(new JScrollPane(textAreaRelatorio), BorderLayout.CENTER);

        gerarRelatorio();
    }

    private void gerarRelatorio() {
        List<Relatorio> relatorios = controller.gerarRelatorio(); // Chama o método do Controller
        StringBuilder sb = new StringBuilder();

        for (Relatorio rel : relatorios) {
            sb.append(rel.toString()).append("\n");
        }

        textAreaRelatorio.setText(sb.toString());
    }

    public static void main(String[] args) {
        new GerarRelatorioView().setVisible(true);
    }
}
