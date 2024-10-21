public class Cartoes {
    public Integer partida_ID;
    public Integer rodada;
    public String clube;
    public String cartao;
    public String atleta ;
    public String numeroDaCamisa;
    public String posicao;
    public String minuto;

    public Cartoes(Integer partida_ID, Integer rodada, String clube, String cartao, String atleta, String numeroDaCamisa, String posicao, String minuto) {
        this.partida_ID = partida_ID;
        this.rodada = rodada;
        this.clube = clube;
        this.cartao = cartao;
        this.atleta = atleta;
        this.numeroDaCamisa = numeroDaCamisa;
        this.posicao = posicao;
        this.minuto = minuto;
    }

    @Override
    public String toString() {
        return "Cartoes{" +
                "partida_ID ='" + partida_ID + '\'' +
                "rodada = '" + rodada + '\'' +
                "clube = '" + clube + '\'' +
                "cartao = '" + cartao + '\'' +
                "atleta = '" + atleta + '\'' +
                "numeroDaCamisa = '" + numeroDaCamisa + '\'' +
                "posicao = '" + posicao + '\'' +
                "minuto = '" + minuto + '\'' +
                '}';
    }
}