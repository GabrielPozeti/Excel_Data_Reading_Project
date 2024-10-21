import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Jogo> jogos = lerCsv("caminhodoarquivo");

        String timeMaisVitorias2008 = obterTimeMaisVitorias(jogos, 2008);
        String estadoMenosJogos = obterEstadoMenosJogos(jogos);
        String jogadorMaisGols = obterJogadorMaisGols(jogos);
        String jogadorMaisGolsPenaltis = obterJogadorMaisGolsPenaltis(jogos);
        String jogadorMaisGolsContra = obterJogadorMaisGolsContra(jogos);
        String jogadorMaisCartoesAmarelos = obterJogadorMaisCartoesAmarelos(jogos);
        String jogadorMaisCartoesVermelhos = obterJogadorMaisCartoesVermelhos(jogos);
        String placarMaisGols = obterPlacarMaisGols(jogos);

        System.out.println("Time que mais venceu jogos no ano 2008: " + timeMaisVitorias2008);
        System.out.println("Estado que teve menos jogos entre 2003 e 2022: " + estadoMenosJogos);
        System.out.println("Jogador que mais fez gols: " + jogadorMaisGols);
        System.out.println("Jogador que mais fez gols de pênaltis: " + jogadorMaisGolsPenaltis);
        System.out.println("Jogador que mais fez gols contras: " + jogadorMaisGolsContra);
        System.out.println("Jogador que mais recebeu cartões amarelos: " + jogadorMaisCartoesAmarelos);
        System.out.println("Jogador que mais recebeu cartões vermelhos: " + jogadorMaisCartoesVermelhos);
        System.out.println("Placar da partida com mais gols: " + placarMaisGols);
    }

    private static List<Jogo> lerCsv(String caminho) {
        List<Jogo> jogos = new ArrayList<>();
        try {
            List<String> linhas = Files.readAllLines(Paths.get(caminho));
            for (String linha : linhas) {
                String[] campos = linha.split(",");
                Jogo jogo = new Jogo();
                jogo.setTimeCasa(campos[0]);
                jogo.setTimeVisitante(campos[1]);
                jogo.setGolsCasa(Integer.parseInt(campos[2]));
                jogo.setGolsVisitante(Integer.parseInt(campos[3]));
                jogo.setEstado(campos[4]);
                jogo.setJogador(campos[5]);
                jogo.setAno(Integer.parseInt(campos[6]));
                jogo.setTipoGol(campos[7]);
                jogo.setTipoCartao(campos[8]);
                jogos.add(jogo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jogos;
    }

    private static String obterTimeMaisVitorias(List<Jogo> jogos, int ano) {
        return "";
    }

    private static String obterEstadoMenosJogos(List<Jogo> jogos) {
        return "";
    }

    private static String obterJogadorMaisGols(List<Jogo> jogos) {
        return "";
    }

    private static String obterJogadorMaisGolsPenaltis(List<Jogo> jogos) {
        return "";
    }

    private static String obterJogadorMaisGolsContra(List<Jogo> jogos) {
        return "";
    }

    private static String obterJogadorMaisCartoesAmarelos(List<Jogo> jogos) {
        return "";
    }

    private static String obterJogadorMaisCartoesVermelhos(List<Jogo> jogos) {
        return "";
    }

    private static String obterPlacarMaisGols(List<Jogo> jogos) {
        return "";
    }
}