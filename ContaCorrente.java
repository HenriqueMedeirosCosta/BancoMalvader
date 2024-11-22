package model;

import java.util.Date;

public class ContaCorrente {

    // Atributos da conta corrente
    private String agencia;
    private String numeroConta;
    private double limiteConta;
    private Date dataVencimento;
    private String nomeCliente;
    private String cpfCliente;
    private Date dataNascimento;
    private String telefone;
    private String enderecoCep;
    private String numeroCasa;
    private String bairro;
    private String cidade;
    private String estado;
    private String senhaCliente;

    // Construtor para inicializar a classe com os dados do cliente
    public ContaCorrente(String agencia, String numeroConta, double limiteConta, Date dataVencimento,
                         String nomeCliente, String cpfCliente, Date dataNascimento, String telefone,
                         String enderecoCep, String numeroCasa, String bairro, String cidade, String estado, String senhaCliente) {
        this.agencia = agencia;
        this.numeroConta = numeroConta;
        this.limiteConta = limiteConta;
        this.dataVencimento = dataVencimento;
        this.nomeCliente = nomeCliente;
        this.cpfCliente = cpfCliente;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.enderecoCep = enderecoCep;
        this.numeroCasa = numeroCasa;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.senhaCliente = senhaCliente;
    }

    // Getters e Setters para acessar e modificar os atributos

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public double getLimiteConta() {
        return limiteConta;
    }

    public void setLimiteConta(double limiteConta) {
        this.limiteConta = limiteConta;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEnderecoCep() {
        return enderecoCep;
    }

    public void setEnderecoCep(String enderecoCep) {
        this.enderecoCep = enderecoCep;
    }

    public String getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(String numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSenhaCliente() {
        return senhaCliente;
    }

    public void setSenhaCliente(String senhaCliente) {
        this.senhaCliente = senhaCliente;
    }

    // Método para exibir as informações da conta corrente
    public void exibirInformacoes() {
        System.out.println("Agência: " + agencia);
        System.out.println("Número da Conta: " + numeroConta);
        System.out.println("Limite da Conta: " + limiteConta);
        System.out.println("Data de Vencimento: " + dataVencimento);
        System.out.println("Nome do Cliente: " + nomeCliente);
        System.out.println("CPF do Cliente: " + cpfCliente);
        System.out.println("Data de Nascimento: " + dataNascimento);
        System.out.println("Telefone: " + telefone);
        System.out.println("Endereço: " + enderecoCep + ", " + numeroCasa + ", " + bairro + ", " + cidade + ", " + estado);
        System.out.println("Senha do Cliente: " + senhaCliente);
    }
}
