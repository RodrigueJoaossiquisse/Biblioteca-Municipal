import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Biblioteca bib = new Biblioteca();
        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n📚 BIBLIOTECA MUNICIPAL");
            System.out.println("1. Registrar livro");
            System.out.println("2. Consultar catálogo");
            System.out.println("3. Pesquisar livro");
            System.out.println("4. Emprestar livro");
            System.out.println("5. Devolver livro");
            System.out.println("6. Estatísticas");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Título: ");
                    String titulo = sc.nextLine();
                    System.out.print("Autor: ");
                    String autor = sc.nextLine();
                    System.out.print("Ano: ");
                    int ano = sc.nextInt();
                    System.out.print("Quantidade: ");
                    int qtd = sc.nextInt();
                    bib.registarLivro(titulo, autor, ano, qtd);
                    break;
                case 2:
                    bib.consultarCatalogo();
                    break;
                case 3:
                    System.out.print("Digite título ou autor: ");
                    String termo = sc.nextLine();
                    bib.pesquisarLivro(termo);
                    break;
                case 4:
                    System.out.print("ID do livro: ");
                    int idEmp = sc.nextInt();
                    bib.emprestarLivro(idEmp);
                    break;
                case 5:
                    System.out.print("ID do livro a devolver: ");
                    int idDev = sc.nextInt();
                    bib.devolverLivro(idDev);
                    break;
                case 6:
                    bib.estatisticas();
                    break;
                case 0:
                    System.out.println("👋 A sair...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        sc.close();
    }
}