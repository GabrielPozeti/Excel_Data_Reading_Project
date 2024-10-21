import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int escolha;
        LeitorDeDados leitorDeDados = new LeitorDeDados();
        Logo.exibirLogo();
        String menuPrincipal = """
                MENU PRINCIPAL
                ===============================================================
                1 - Quero ver o time que teve mais vitórias em 2008
                2 - Quero ver o estado com menos jogos entre 2003 e 2022
                3 - Quero encontrar o jogador que fez mais gols
                4 - Quero encontrar o jogador que fez mais gols de pênaltis
                5 - Quero encontrar o jogador que fez mais gols contra
                6 - Quero encontrar o jogador que levou mais cartões amarelos
                7 - Quero encontrar o jogador que levou mais cartões vermelhos
                8 - Quero ver o jogo com o maior numero de gols
                0 - Sair do programa
                ===============================================================
                Digite o número referente à informação que deseja visualizar:
                """;

        do {
            System.out.println();
            System.out.println(menuPrincipal);
            escolha = Opcao(input);
            Opcao(escolha, leitorDeDados);
        } while (escolha != 0);
        input.close();
    }

    private static int Opcao(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Digite sua escolha: ");
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Entrada inválida! Por favor, digite apenas números.");
                scanner.nextLine();
            }
        }
    }

    private static void Opcao(int escolha, LeitorDeDados leitorDeDados) {
        switch (escolha) {
            case 1 -> mostrarOTimeComMaisVitoriasEm2008(leitorDeDados);
            case 2 -> mostrarOEstadoComMenosJogos(leitorDeDados);
            case 3 -> mostrarOJogadorQueFezMaisGols(leitorDeDados);
            case 4 -> mostrarOJogadorComMaisPenaltisRealizados(leitorDeDados);
            case 5 -> mostrarJogadorQueFezMaisGolsContra(leitorDeDados);
            case 6 -> mostrarOJogadorComMaisCartoes(leitorDeDados, "amarelo");
            case 7 -> mostrarOJogadorComMaisCartoes(leitorDeDados, "vermelho");
            case 8 -> mostrarJogoComMaiorNumeroDeGols(leitorDeDados);
            case 0 -> System.out.println("Obrigado por utilizar o programa de análise Santander!");
            default -> System.out.println("Opção inválida, digite apenas numeros.");
        }
    }


    private static void mostrarOJogadorComMaisCartoes(LeitorDeDados leitorDeDados, String tipoCartao) {
        try {
            Map.Entry<String, Long> jogador = encontrarOJogadorComMaisCartoes(leitorDeDados, tipoCartao);
            System.out.printf("O jogador com mais cartões %s foi o %s, com %d cartões recebidos.%n", tipoCartao + "s", jogador.getKey(), jogador.getValue());
        } catch (NoSuchElementException e) {
            System.err.println("Nenhum cartão encontrado do tipo " + tipoCartao + "foi encontrado.");
        }
    }

    private static Map.Entry<String, Long> encontrarOJogadorComMaisCartoes(LeitorDeDados leitorDeDados, String tipoCartao) {
        return leitorDeDados.getCartoes()
                .stream()
                .filter(cartao -> cartao.cartao.equalsIgnoreCase(tipoCartao))
                .collect(Collectors.groupingBy(cartao -> cartao.atleta, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow(() -> new NoSuchElementException("A lista de cartões está vazia."));
    }

    private static void mostrarOJogadorQueFezMaisGols(LeitorDeDados leitorDeDadosr) {
        Map<String, Long> golsPorJogador = leitorDeDadosr.getGols()
                .stream()
                .collect(Collectors.groupingBy(g -> g.atleta, Collectors.counting()));

        if (golsPorJogador.isEmpty()) {
            System.out.println("Nenhum gol foi registrado.");
        }

        Map.Entry<String, Long> jogadorComMaisGolsRealizados = golsPorJogador.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow(() -> new NoSuchElementException("Nenhum jogador foi encontrado."));

        System.out.printf("O jogador que mais fez gols foi o %s, com %d gols realizados!\n",
                jogadorComMaisGolsRealizados.getKey(), jogadorComMaisGolsRealizados.getValue());
    }

    private static void mostrarOJogadorComMaisPenaltisRealizados(LeitorDeDados leitorDeDados) {
        Map<String, Long> golsDePenaltiPorJogador = leitorDeDados.getGols()
                .stream()
                .filter(gol -> gol.tipoDeGol != null && gol.tipoDeGol.equalsIgnoreCase("penalty"))
                .collect(Collectors.groupingBy(g -> g.atleta, Collectors.counting()));

        if (golsDePenaltiPorJogador.isEmpty()) {
            System.out.println("Nenhum gol de pênalti foi encontrado.");
            return;
        }

        Map.Entry<String, Long> jogadorComMaisPenaltisRealizados = golsDePenaltiPorJogador.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow(() -> new NoSuchElementException("Nenhum jogador foi encontrado."));

        System.out.printf("O jogador que mais fez gols de pênalti foi o %s, com %d gols de pênalti.\n",
                jogadorComMaisPenaltisRealizados.getKey(), jogadorComMaisPenaltisRealizados.getValue());
    }

    private static void mostrarOTimeComMaisVitoriasEm2008(LeitorDeDados LeitorDeDados) {
        Map<String, Long> vitoriasPorTime = LeitorDeDados.getBrasileiraoes()
                .stream()
                .filter(jogo -> jogo.data.getYear() == 2008)
                .filter(jogo -> jogo.vencedor != null && !jogo.vencedor.equals("-"))
                .collect(Collectors.groupingBy(jogo -> jogo.vencedor, Collectors.counting()));

        if (vitoriasPorTime.isEmpty()) {
            System.out.println("Nenhuma vitória foi registrada em 2008.");
            return;
        }

        Map.Entry<String, Long> timeComMaisVitorias = vitoriasPorTime.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow(() -> new NoSuchElementException("Nenhum time foi encontrado."));

        System.out.printf("O time que mais venceu em 2008 foi o %s, com %d vitórias.\n",
                timeComMaisVitorias.getKey(), timeComMaisVitorias.getValue());
    }

    private static void mostrarOEstadoComMenosJogos(LeitorDeDados leitorDeDados) {
        Map<String, String> siglaParaNomeCompleto = new HashMap<>();
        siglaParaNomeCompleto.put("AC", "Acre");
        siglaParaNomeCompleto.put("AL", "Alagoas");
        siglaParaNomeCompleto.put("AP", "Amapá");
        siglaParaNomeCompleto.put("AM", "Amazonas");
        siglaParaNomeCompleto.put("BA", "Bahia");
        siglaParaNomeCompleto.put("CE", "Ceará");
        siglaParaNomeCompleto.put("DF", "Distrito Federal");
        siglaParaNomeCompleto.put("ES", "Espírito Santo");
        siglaParaNomeCompleto.put("GO", "Goiás");
        siglaParaNomeCompleto.put("MA", "Maranhão");
        siglaParaNomeCompleto.put("MT", "Mato Grosso");
        siglaParaNomeCompleto.put("MS", "Mato Grosso do Sul");
        siglaParaNomeCompleto.put("MG", "Minas Gerais");
        siglaParaNomeCompleto.put("PA", "Pará");
        siglaParaNomeCompleto.put("PB", "Paraíba");
        siglaParaNomeCompleto.put("PR", "Paraná");
        siglaParaNomeCompleto.put("PE", "Pernambuco");
        siglaParaNomeCompleto.put("PI", "Piauí");
        siglaParaNomeCompleto.put("RJ", "Rio de Janeiro");
        siglaParaNomeCompleto.put("RN", "Rio Grande do Norte");
        siglaParaNomeCompleto.put("RS", "Rio Grande do Sul");
        siglaParaNomeCompleto.put("RO", "Rondônia");
        siglaParaNomeCompleto.put("RR", "Roraima");
        siglaParaNomeCompleto.put("SC", "Santa Catarina");
        siglaParaNomeCompleto.put("SP", "São Paulo");
        siglaParaNomeCompleto.put("SE", "Sergipe");
        siglaParaNomeCompleto.put("TO", "Tocantins");

        Map<String, Long> jogosPorEstado = leitorDeDados.getBrasileiraoes()
                .stream()
                .filter(jogo -> jogo.data.getYear() > 2002 && jogo.data.getYear() < 2023)
                .flatMap(jogo -> {
                    List<String> estados = new ArrayList<>();
                    if (jogo.estadoMandante != null)
                        estados.add(jogo.estadoMandante);
                    if (jogo.estadoVisitante != null)
                        estados.add(jogo.estadoVisitante);
                    return estados.stream();
                })
                .collect(Collectors.groupingBy(estado -> estado, Collectors.counting()));

        if (jogosPorEstado.isEmpty()) {
            System.out.println("Nenhum jogo foi registrado entre 2003 e 2022.");
            return;
        }

        Map.Entry<String, Long> estadoComMenosJogos = jogosPorEstado.entrySet()
                .stream()
                .min(Map.Entry.comparingByValue())
                .orElseThrow(() -> new NoSuchElementException("Nenhum estado foi encontrado."));

        String nomeCompletoEstado = siglaParaNomeCompleto.getOrDefault(estadoComMenosJogos.getKey(), estadoComMenosJogos.getKey());

        System.out.printf("O estado com o menor número de jogos realizados entre 2003 e 2022 foi %s, com %d jogos realizados.\n",
                nomeCompletoEstado, estadoComMenosJogos.getValue());
    }

    private static void mostrarJogadorQueFezMaisGolsContra(LeitorDeDados leitorDeDados) {
        Map<String, Long> golsContra = leitorDeDados.getGols()
                .stream()
                .filter(gol -> gol.tipoDeGol != null && gol.tipoDeGol.equalsIgnoreCase("Gols Contra"))
                .collect(Collectors.groupingBy(gol -> gol.atleta, Collectors.counting()));

        if (golsContra.isEmpty()) {
            System.out.println("Nenhum gol contra foi encontrado.");
            return;
        }

        Map.Entry<String, Long> jogadorQueFezMaisGolsContra = golsContra.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow(() -> new NoSuchElementException("Nenhum jogador foi encontrado."));

        System.out.printf("O jogador que fez mais gols contra foi o %s, com %d gols contra.\n",
                jogadorQueFezMaisGolsContra.getKey(), jogadorQueFezMaisGolsContra.getValue());
    }

    private static void mostrarJogoComMaiorNumeroDeGols(LeitorDeDados leitorDeDados) {
        Optional<AbstractMap.SimpleEntry<JogoBrasileirao, Integer>> jogoComMaisGols = leitorDeDados.getBrasileiraoes()
                .stream()
                .filter(jogo -> jogo.PlacarDoMandante != null && jogo.PlacardoVisitante != null)
                .map(jogo -> {
                    int totalGols = jogo.PlacarDoMandante + jogo.PlacardoVisitante;
                    return new AbstractMap.SimpleEntry<>(jogo, totalGols);
                })
                .max(Comparator.comparingLong(Map.Entry::getValue));

        if (jogoComMaisGols.isEmpty()) {
            System.out.println("Nenhuma partida com gols foi encontrada.");
            return;
        }

        AbstractMap.SimpleEntry<JogoBrasileirao, Integer> partida = jogoComMaisGols.get();
        int golsMandante = partida.getKey().PlacarDoMandante;
        int golsVisitante = partida.getKey().PlacardoVisitante;
        System.out.printf("O jogo com o maior número de gols foi o%s (%d à %d).\n",
                partida.getKey().arena, golsMandante, golsVisitante);
    }
}