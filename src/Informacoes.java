public class Informacoes {
    public Integer partida_ID;
    public Integer rodada;
    public String Clube;
    public Integer chutes;
    public Integer chutesAGol;
    public String posseDeBola;
    public Integer passes;
    public String precisaoDosPasses;
    public Integer faltasCometidas;
    public Integer cartaoAmarelo;
    public Integer cartaoVermelho;
    public Integer impedimentos;
    public Integer escanteios;

    public Informacoes(
            Integer partida_ID, Integer rodada, String clube, Integer chutes, Integer chutesAGol, String posseDeBola, Integer passes, String precisaoDosPasses, Integer faltasCometidas, Integer cartaoAmarelo, Integer cartaoVermelho, Integer impedimentos, Integer escanteios) {
        this.partida_ID = partida_ID;
        this.rodada = rodada;
        Clube = clube;
        this.chutes = chutes;
        this.chutesAGol = chutesAGol;
        this.posseDeBola = posseDeBola;
        this.passes = passes;
        this.precisaoDosPasses = precisaoDosPasses;
        this.faltasCometidas = faltasCometidas;
        this.cartaoAmarelo = cartaoAmarelo;
        this.cartaoVermelho = cartaoVermelho;
        this.impedimentos = impedimentos;
        this.escanteios = escanteios;
    }

    @Override
    public String toString() {
        return "Informacoes{" +
                "partida_ID = '" + partida_ID + '\'' +
                "rodada = '" + rodada + '\'' +
                "Clube = '" + Clube + '\'' +
                "chutes = '" + chutes + '\'' +
                "chutesAGol = '" + chutesAGol + '\'' +
                "posseDeBola = '" + posseDeBola + '\'' +
                "passes = '" + passes + '\'' +
                "precisaoDosPasses = '" + precisaoDosPasses + '\'' +
                "faltasCometidas = '" + faltasCometidas + '\'' +
                "cartaoAmarelo = '" + cartaoAmarelo + '\'' +
                "cartaoVermelho = '" + cartaoVermelho + '\'' +
                "impedimentos = '" + impedimentos + '\'' +
                "escanteios = '" + escanteios + '\'' +
                '}';
    }
}