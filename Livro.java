class Livro {
    private int id;
    private String titulo;
    private String autor;
    private int ano;
    private int quantidade;

    public Livro(int id, String titulo, String autor, int ano, int quantidade) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.quantidade = quantidade;
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    @Override
    public String toString() {
        return id + " - " + titulo + " (" + autor + ", " + ano + ") - Disponíveis: " + quantidade;
    }
}