public class Gols {
    public Integer partida_ID;
    public String rodada;
    public String clube;
    public String atleta;
    public String minuto;
    public String tipoDeGol;

    @Override
    public String toString() {
        return "Gols{" +
                "partida_ID = '" + partida_ID + '\'' +
                "rodada = '" + rodada + '\'' +
                "clube = '" + clube + '\'' +
                "atleta = '" + atleta + '\'' +
                "minuto = '" + minuto + '\'' +
                "tipoDeGol = '" + tipoDeGol + '\'' +
                '}';
    }

    public Gols(Integer partida_ID, String rodada, String clube, String atleta, String minuto, String tipoDeGol) {
        this(partida_ID, rodada, clube, atleta, minuto);
        this.tipoDeGol = tipoDeGol;
    }

    public Gols(Integer partida_ID, String rodada, String clube, String atleta, String minuto) {
        this.partida_ID = partida_ID;
        this.rodada = rodada;
        this.clube = clube;
        this.atleta = atleta;
        this.minuto = minuto;
    }
}