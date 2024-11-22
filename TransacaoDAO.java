package Model;

public class TransacaoDAO {
    private int id;
    private String tipoTransacao;
    private double valor;
    private int idConta;

    public TransacaoDAO(int id, String tipoTransacao, double valor, int idConta) {
        this.id = id;
        this.tipoTransacao = tipoTransacao;
        this.valor = valor;
        this.idConta = idConta;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(String tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }
}
