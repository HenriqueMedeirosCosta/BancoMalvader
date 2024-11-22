package Model;

public class Conta {
    private String numero;
    private double saldo;
    private double limite;
    private String tipoConta;
    private String dataNascimento;
    private String cpf;
    private String dataVencimento; // Novo campo
    private int idConta;  // Novo campo para o ID da conta
    private int idCliente; // Novo campo para o ID do cliente

    // Construtor sem parâmetros
    public Conta() {
    }

    // Construtor original com 9 parâmetros
    public Conta(String numero, double saldo, double limite, String tipoConta, String dataNascimento, String cpf, String dataVencimento, int idConta, int idCliente) {
        this.numero = numero;
        this.saldo = saldo;
        this.limite = limite;
        this.tipoConta = tipoConta;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.dataVencimento = dataVencimento; // Atribui o valor de dataVencimento
        this.idConta = idConta; // Atribui o ID da conta
        this.idCliente = idCliente; // Atribui o ID do cliente
    }

    // Construtor alternativo com 5 parâmetros (para compatibilidade com o seu código)
    public Conta(String numero, String tipoConta, double limite, String dataVencimento, String cpfCliente, int idConta, int idCliente) {
        this.numero = numero;
        this.tipoConta = tipoConta;
        // Atribui valores padrão aos outros campos
        this.saldo = 0.0;
        this.limite = 0.0;
        this.dataNascimento = "";
        this.cpf = cpfCliente;
        this.dataVencimento = dataVencimento;
        this.idConta = idConta; // Atribui o ID da conta
        this.idCliente = idCliente; // Atribui o ID do cliente
    }

    // Getters e Setters
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
