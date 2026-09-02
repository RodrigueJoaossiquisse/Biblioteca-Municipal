import java.util.ArrayList;

class Biblioteca {
    private ArrayList<Livro> livros = new ArrayList<>();
    private int nextId = 1;
    private int totalEmprestimos = 0;

    public void registarLivro(String titulo, String autor, int ano, int quantidade) {
        livros.add(new Livro(nextId++, titulo, autor, ano, quantidade));
        System.out.println("✅ Livro registado com sucesso!");
    }

    public void consultarCatalogo() {
        if (livros.isEmpty()) {
            System.out.println("📭 Nenhum livro registado.");
            return;
        }
        for (Livro l : livros) {
            System.out.println(l);
        }
    }

    public void pesquisarLivro(String termo) {
        boolean encontrado = false;
        for (Livro l : livros) {
            if (l.getTitulo().toLowerCase().contains(termo.toLowerCase()) ||
                l.getAutor().toLowerCase().contains(termo.toLowerCase())) {
                System.out.println(l);
                encontrado = true;
            }
        }
        if (!encontrado) System.out.println("🔍 Nenhum livro encontrado.");
    }

    public void emprestarLivro(int id) {
        for (Livro l : livros) {
            if (l.getId() == id && l.getQuantidade() > 0) {
                l.setQuantidade(l.getQuantidade() - 1);
                totalEmprestimos++;
                System.out.println("📚 Empréstimo realizado com sucesso!");
                return;
            }
        }
        System.out.println("❌ Livro indisponível ou não encontrado.");
    }

    public void devolverLivro(int id) {
        for (Livro l : livros) {
            if (l.getId() == id) {
                l.setQuantidade(l.getQuantidade() + 1);
                System.out.println("🔄 Devolução registada!");
                return;
            }
        }
        System.out.println("❌ Livro não encontrado.");
    }

    public void estatisticas() {
        System.out.println("📊 Total de livros: " + livros.size());
        System.out.println("📊 Total de empréstimos: " + totalEmprestimos);
        if (!livros.isEmpty()) {
            Livro mais = livros.get(0);
            for (Livro l : livros) {
                if (l.getQuantidade() < mais.getQuantidade()) {
                    mais = l;
                }
            }
            System.out.println("📊 Livro mais emprestado (menos disponível): " + mais.getTitulo());
        }
    }
}