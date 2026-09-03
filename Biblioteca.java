public class Biblioteca {
    private Livro[] livros;
    private int totalLivros;
    private int nextId;
    private int totalEmprestimos;

    // MATRIZ para registar empréstimos (até 100 empréstimos, 3 colunas)
    private String[][] historicoEmprestimos;
    private int totalHistorico;

    private static final int CAPACIDADE_MAXIMA = 100;

    public Biblioteca() {
        livros = new Livro[CAPACIDADE_MAXIMA];
        historicoEmprestimos = new String[CAPACIDADE_MAXIMA][3]; // [idLivro][utilizador][data]
        totalLivros = 0;
        totalHistorico = 0;
        nextId = 1;
        totalEmprestimos = 0;
    }

    public void registarLivro(String titulo, String autor, int ano, int quantidade) {
        if (totalLivros < CAPACIDADE_MAXIMA) {
            livros[totalLivros] = new Livro(nextId++, titulo, autor, ano, quantidade);
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

    public void emprestarLivro(int id, String utilizador, String data) {
        for (int i = 0; i < totalLivros; i++) {
            Livro l = livros[i];
            if (l.getId() == id && l.getQuantidade() > 0) {
                l.setQuantidade(l.getQuantidade() - 1);
                totalEmprestimos++;

                // REGISTA NA MATRIZ
                if (totalHistorico < CAPACIDADE_MAXIMA) {
                    historicoEmprestimos[totalHistorico][0] = String.valueOf(id);
                    historicoEmprestimos[totalHistorico][1] = utilizador;
                    historicoEmprestimos[totalHistorico][2] = data;
                    totalHistorico++;
                }

                System.out.println("📚 Empréstimo realizado com sucesso!");
                return;
            }
        }
        System.out.println("❌ Livro indisponível ou não encontrado.");
    }

    public void devolverLivro(int id) {
        for (int i = 0; i < totalLivros; i++) {
            Livro l = livros[i];
            if (l.getId() == id) {
                l.setQuantidade(l.getQuantidade() + 1);
                System.out.println("🔄 Devolução registada!");
                return;
            }
        }
        System.out.println("❌ Livro não encontrado.");
    }

    public void estatisticas() {
        System.out.println("📊 Total de livros: " + totalLivros);
        System.out.println("📊 Total de empréstimos: " + totalEmprestimos);

        if (totalLivros == 0) {
            System.out.println("📊 Nenhum livro para estatísticas.");
            return;
        }

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
                                   " | Utilizador: " + historicoEmprestimos[i][1] +
                                   " | Data: " + historicoEmprestimos[i][2]);
            }
        } else {
            System.out.println("📋 Nenhum empréstimo registado.");
        }
    }
}