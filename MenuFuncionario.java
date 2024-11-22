package Controller;

import Model.ContaPoupanca;
import java.util.Date;
import java.util.Scanner;

public class MenuFuncionario {

    // Método para exibir o menu e permitir que o funcionário preencha os dados da conta
    public void exibirMenuFuncionario() {
        Scanner scanner = new Scanner(System.in);

        // Variáveis para armazenar os dados preenchidos pelo funcionário
        System.out.println("Menu Funcionário:");

        // Coletando dados do funcionário
        System.out.print("Digite a Agência: ");
        String agencia = scanner.nextLine();

        System.out.print("Digite o Número da Conta: ");
        String numeroConta = scanner.nextLine();

        System.out.print("Digite o Nome do Cliente: ");
        String nomeCliente = scanner.nextLine();

        System.out.print("Digite o CPF do Cliente: ");
        String cpfCliente = scanner.nextLine();

        System.out.print("Digite a Data de Nascimento (dd/MM/yyyy): ");
        String dataNascimentoStr = scanner.nextLine();
        Date dataNascimento = new Date(dataNascimentoStr); // Aqui seria necessário tratar a data de forma adequada

        System.out.print("Digite o Telefone do Cliente: ");
        String telefone = scanner.nextLine();

        System.out.print("Digite o Endereço (CEP): ");
        String enderecoCep = scanner.nextLine();

        System.out.print("Digite o Número da Casa: ");
        String numeroCasa = scanner.nextLine();

        System.out.print("Digite o Bairro: ");
        String bairro = scanner.nextLine();

        System.out.print("Digite a Cidade: ");
        String cidade = scanner.nextLine();

        System.out.print("Digite o Estado: ");
        String estado = scanner.nextLine();

        System.out.print("Digite a Senha do Cliente: ");
        String senhaCliente = scanner.nextLine();

        // Criando a conta com os dados preenchidos
        ContaPoupanca conta = new ContaPoupanca(
                agencia,
                numeroConta,
                nomeCliente,
                cpfCliente,
                dataNascimento,
                telefone,
                enderecoCep,
                numeroCasa,
                bairro,
                cidade,
                estado,
                senhaCliente
        );

        // Exibir as informações preenchidas
        System.out.println("\nDados da Conta Criada:");
        conta.exibirInformacoes();

        // Menu de opções após o preenchimento
        System.out.println("\nMenu:");
        System.out.println("1 - Confirmar dados");
        System.out.println("2 - Cancelar");

        int opcao = scanner.nextInt();
        switch (opcao) {
            case 1:
                System.out.println("Conta criada com sucesso!");
                break;
            case 2:
                System.out.println("Operação cancelada.");
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }
}
