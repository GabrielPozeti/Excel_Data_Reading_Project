import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LeitorDeDados {
    private List<Cartoes> cartoes;
    private List<Informacoes> informacoes;
    private List<Gols> gols;
    private List<JogoBrasileirao> brasileiraoes;

    LeitorDeDados() {
        var rootFolder = Paths.get("./src/dadosExcel/brasileirao_2003a2022");

        try (Stream<Path> files = Files.list(rootFolder)) {
            files.filter(path -> path.toString().endsWith(".csv"))
                    .forEach(this::comecarThreadParaFile);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao listar arquivos no diretório: " + rootFolder, e);
        }
    }

    private void comecarThreadParaFile(Path file) {
        if (file.toString().contains("gols")) {
            new Thread(() -> {
                try {
                    gols = parseGols(file);
                } catch (IOException e) {
                    System.err.println("Erro ao ler gols: " + e.getMessage());
                }
            }).start();
        } else if (file.toString().contains("brasileiro-full")) {
            new Thread(() -> {
                try {
                    brasileiraoes = parseBrasileirao(file);
                } catch (IOException e) {
                    System.err.println("Erro ao ler dados do Brasileirão: " + e.getMessage());
                }
            }).start();
        } else if (file.toString().contains("cartoes")) {
            new Thread(() -> {
                try {
                    cartoes = parseCartoes(file);
                } catch (IOException e) {
                    System.err.println("Erro ao ler cartões: " + e.getMessage());
                }
            }).start();
        } else if (file.toString().contains("informacoes")) {
            new Thread(() -> {
                try {
                    informacoes = parseEstatisticas(file);
                } catch (IOException e) {
                    System.err.println("Erro ao ler estatísticas: " + e.getMessage());
                }
            }).start();
        }
    }

    private List<Gols> parseGols(Path file) throws IOException {
        try (Stream<String> lines = Files.lines(file)) {
            return lines
                    .map(line->line.replaceAll("\"", "").split(","))
                    .filter(row->isInteger(row[0]))
                    .map(row -> row.length == 6
                            ? new Gols(Integer.parseInt(row[0]), row[1], row[2], row[3], row[4], row[5])
                            : new Gols(Integer.parseInt(row[0]), row[1], row[2], row[3], row[4]))
                    .collect(Collectors.toList());
        }
    }

    private List<JogoBrasileirao> parseBrasileirao(Path file) throws IOException {
        try (Stream<String> lines = Files.lines(file)) {
            return lines
                    .map(line->line.replaceAll("\"", "").split(","))
                    .filter(row->isInteger(row[0]))
                    .map(row -> new JogoBrasileirao(Integer.parseInt(row[0]), Integer.parseInt(row[1]), row[2], row[3], row[4], row[5], row[6],
                            row[7], row[8], row[9], row[10], row[11], Integer.parseInt(row[12]), Integer.parseInt(row[13]), row[14], row[15]))
                    .collect(Collectors.toList());
        }
    }

    private List<Cartoes> parseCartoes(Path file) throws IOException {
        try (Stream<String> lines = Files.lines(file)) {
            return lines
                    .map(line->line.replaceAll("\"", "").split(","))
                    .filter(row->isInteger(row[0]))
                    .map(row -> {
                        Integer partida_Id = Integer.parseInt(row[0]);
                        Integer rodada = Integer.parseInt(row[1]);
                        return new Cartoes(partida_Id, rodada, row[2], row[3], row[4], row[5], row[6], row[7]);
                    })
                    .collect(Collectors.toList());
        }
    }

    private List<Informacoes> parseEstatisticas(Path file) throws IOException {
        try (Stream<String> lines = Files.lines(file)) {
            return lines
                    .map(line->line.replaceAll("\"", "").split(","))
                    .filter(row->isInteger(row[0]))
                    .map(row -> {
                        Integer partida_Id = Integer.parseInt(row[0]);
                        Integer rodadu = Integer.parseInt(row[1]);
                        Integer chutes = Integer.parseInt(row[3]);
                        Integer chutesAGol = Integer.parseInt(row[4]);
                        Integer passes = Integer.parseInt(row[6]);
                        Integer faltas =Integer.parseInt(row[8]);
                        Integer cartao_amarelo =Integer.parseInt(row[9]);
                        Integer cartao_vermelho =Integer.parseInt(row[10]);
                        Integer impedimentos =Integer.parseInt(row[11]);
                        Integer escanteios = Integer.parseInt(row[12]);
                        return new Informacoes(partida_Id, rodadu, row[2], chutes, chutesAGol, row[5], passes,
                                row[7], faltas, cartao_amarelo, cartao_vermelho, impedimentos, escanteios);
                    })
                    .collect(Collectors.toList());
        }
    }

    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public List<Cartoes> getCartoes() {
        return cartoes;
    }

    public List<Informacoes> getEstatisticas() {
        return informacoes;
    }

    public List<Gols> getGols() {
        return gols;
    }

    public List<JogoBrasileirao> getBrasileiraoes() {
        return brasileiraoes;
    }
}
