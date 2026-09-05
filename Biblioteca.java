public class Biblioteca {
    private Livro[] livros;
    private int totalLivros;
    private int nextIdLivro;

    private Utilizador[] utilizadores;
    private int totalUtilizadores;
    private int nextIdUtilizador;

    private int totalEmprestimos;

    // MATRIZ para registar empréstimos (até 100 empréstimos, 4 colunas)
    private String[][] historicoEmprestimos;
    private int totalHistorico;

    private static final int CAPACIDADE_MAXIMA = 100;

    public Biblioteca() {
        livros = new Livro[CAPACIDADE_MAXIMA];
        utilizadores = new Utilizador[CAPACIDADE_MAXIMA];
        historicoEmprestimos = new String[CAPACIDADE_MAXIMA][4]; // [idLivro][idUtilizador][nomeUtilizador][data]

        totalLivros = 0;
        totalUtilizadores = 0;
        totalHistorico = 0;
        totalEmprestimos = 0;

        nextIdLivro = 1;
        nextIdUtilizador = 1;
    }

    // ===== MÉTODOS PARA LIVROS =====
    public void registarLivro(String titulo, String autor, int ano, int quantidade) {
        if (totalLivros < CAPACIDADE_MAXIMA) {
            livros[totalLivros] = new Livro(nextIdLivro++, titulo, autor, ano, quantidade);
            totalLivros++;
            System.out.println("✅ Livro registado com sucesso!");
        } else {
            System.out.println("❌ Catálogo cheio! Não é possível registar mais livros.");
        }
    }

    public void consultarCatalogo() {
        if (totalLivros == 0) {
            System.out.println("📭 Nenhum livro registado.");
            return;
        }
        for (int i = 0; i < totalLivros; i++) {
            System.out.println(livros[i]);
        }
    }

    public void pesquisarLivro(String termo) {
        boolean encontrado = false;
        for (int i = 0; i < totalLivros; i++) {
            Livro l = livros[i];
            if (l.getTitulo().toLowerCase().contains(termo.toLowerCase()) ||
                l.getAutor().toLowerCase().contains(termo.toLowerCase())) {
                System.out.println(l);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("🔍 Nenhum livro encontrado.");
        }
    }

    // ===== MÉTODOS PARA UTILIZADORES =====
    public void registarUtilizador(String nome) {
        if (totalUtilizadores < CAPACIDADE_MAXIMA) {
            utilizadores[totalUtilizadores] = new Utilizador(nextIdUtilizador++, nome);
            totalUtilizadores++;
            System.out.println("✅ Utilizador registado com sucesso! ID: " + (nextIdUtilizador - 1));
        } else {
            System.out.println("❌ Limite de utilizadores atingido.");
        }
    }

    public void listarUtilizadores() {
        if (totalUtilizadores == 0) {
            System.out.println("📭 Nenhum utilizador registado.");
            return;
        }
        for (int i = 0; i < totalUtilizadores; i++) {
            System.out.println(utilizadores[i]);
        }
    }

    // ===== MÉTODOS PARA EMPRÉSTIMOS =====
    public void emprestarLivro(int idLivro, int idUtilizador, String data) {
        // Verifica se o utilizador existe
        String nomeUtilizador = null;
        for (int i = 0; i < totalUtilizadores; i++) {
            if (utilizadores[i].getId() == idUtilizador) {
                nomeUtilizador = utilizadores[i].getNome();
                break;
            }
        }
        if (nomeUtilizador == null) {
            System.out.println("❌ Utilizador não encontrado. Registe o utilizador primeiro.");
            return;
        }

        // Procura o livro
        for (int i = 0; i < totalLivros; i++) {
            Livro l = livros[i];
            if (l.getId() == idLivro && l.getQuantidade() > 0) {
                l.setQuantidade(l.getQuantidade() - 1);
                totalEmprestimos++;

                // REGISTA NA MATRIZ (agora com 4 colunas)
                if (totalHistorico < CAPACIDADE_MAXIMA) {
                    historicoEmprestimos[totalHistorico][0] = String.valueOf(idLivro);
                    historicoEmprestimos[totalHistorico][1] = String.valueOf(idUtilizador);
                    historicoEmprestimos[totalHistorico][2] = nomeUtilizador;
                    historicoEmprestimos[totalHistorico][3] = data;
                    totalHistorico++;
                }

                System.out.println("📚 Empréstimo realizado com sucesso!");
                return;
            }
        }
        System.out.println("❌ Livro indisponível ou não encontrado.");
    }

    public void devolverLivro(int idLivro) {
        for (int i = 0; i < totalLivros; i++) {
            Livro l = livros[i];
            if (l.getId() == idLivro) {
                l.setQuantidade(l.getQuantidade() + 1);
                System.out.println("🔄 Devolução registada!");
                return;
            }
        }
        System.out.println("❌ Livro não encontrado.");
    }

    // ===== ESTATÍSTICAS =====
    public void estatisticas() {
        System.out.println("📊 Total de livros: " + totalLivros);
        System.out.println("📊 Total de utilizadores: " + totalUtilizadores);
        System.out.println("📊 Total de empréstimos: " + totalEmprestimos);

        if (totalLivros == 0) {
            System.out.println("📊 Nenhum livro para estatísticas.");
            return;
        }

        // Livro mais emprestado (pela quantidade disponível)
        Livro maisEmprestado = livros[0];
        for (int i = 1; i < totalLivros; i++) {
            if (livros[i].getQuantidade() < maisEmprestado.getQuantidade()) {
                maisEmprestado = livros[i];
            }
        }
        System.out.println("📊 Livro mais emprestado (menos disponível): " + maisEmprestado.getTitulo());

        // EXIBE HISTÓRICO DA MATRIZ
        if (totalHistorico > 0) {
            System.out.println("\n📋 Histórico de Empréstimos (Matriz):");
            for (int i = 0; i < totalHistorico; i++) {
                System.out.println("  Livro ID: " + historicoEmprestimos[i][0] +
                                   " | Utilizador ID: " + historicoEmprestimos[i][1] +
                                   " | Nome: " + historicoEmprestimos[i][2] +
                                   " | Data: " + historicoEmprestimos[i][3]);
            }
        } else {
            System.out.println("📋 Nenhum empréstimo registado.");
        }
    }
}