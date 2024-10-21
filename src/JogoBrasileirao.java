import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class JogoBrasileirao {
    public Integer ID;
    public Integer rodada;
    public LocalDate data;
    public LocalTime horario;
    public String dia;
    public String mandante;
    public String visitante;
    public String formacaoDoMandante;
    public String formacaoDoVisitante;
    public String tecnicoDoMandante;
    public String tecnicoDoVisitante;
    public String vencedor;
    public String arena;
    public Integer PlacarDoMandante;
    public Integer PlacardoVisitante;
    public String estadoMandante;
    public String estadoVisitante;
    public String estadoVencedor;

    public JogoBrasileirao(
            Integer ID, Integer rodada, String data, String horario, String mandante, String visitante, String formacaoDoMandante, String formacaoDoVisitante, String tecnicoDoMandante, String tecnicoDoVisitante, String vencedor, String arena, Integer PlacarDoMandante, Integer PlacardoVisitante, String estadoMandante, String estadoVisitante)
    {
        this.ID = ID;
        this.rodada = rodada;
        this.data = LocalDate.parse(data, DateTimeFormatter.ofPattern("d/M/yyyy"));
        this.horario = LocalTime.parse(horario, DateTimeFormatter.ofPattern("H:mm"));
        this.dia = this.data.getDayOfWeek().toString();
        this.mandante = mandante;
        this.visitante = visitante;
        this.formacaoDoMandante = formacaoDoMandante;
        this.formacaoDoVisitante = formacaoDoVisitante;
        this.tecnicoDoMandante = tecnicoDoMandante;
        this.tecnicoDoVisitante = tecnicoDoVisitante;
        this.vencedor = vencedor;
        this.arena = arena;
        this.PlacarDoMandante = PlacarDoMandante;
        this.PlacardoVisitante = PlacardoVisitante;
        this.estadoMandante = estadoMandante;
        this.estadoVisitante = estadoVisitante;
        this.estadoVencedor = estadoMandante;
    }

    @Override
    public String toString() {
        return "JogoBrasileirao{" +
                "ID='" + ID + '\'' +
                "rodada='" + rodada + '\'' +
                "data='" + data + '\'' +
                "horario='" + horario + '\'' +
                "dia='" + dia + '\'' +
                "mandante='" + mandante + '\'' +
                "visitante='" + visitante + '\'' +
                "formacaoDoMandante='" + formacaoDoMandante + '\'' +
                "formacaoDoVisitante='" + formacaoDoVisitante + '\'' +
                "tecnicoDoMandante='" + tecnicoDoMandante + '\'' +
                "tecnicoDoVisitante='" + tecnicoDoVisitante + '\'' +
                "vencedor='" + vencedor + '\'' +
                "arena='" + arena + '\'' +
                "PlacarDoMandante='" + PlacarDoMandante + '\'' +
                "PlacardoVisitante='" + PlacardoVisitante + '\'' +
                "estadoMandante='" + estadoMandante + '\'' +
                "estadoVisitante='" + estadoVisitante + '\'' +
                "estadoVencedor='" + estadoVencedor + '\'' +
                '}';
    }
}