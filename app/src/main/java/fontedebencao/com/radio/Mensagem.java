package fontedebencao.com.radio;

public class Mensagem {

    private String nome;
    private String telefone;
    private String email;
    private String mensagem;

    public Mensagem() {
    }

    public Mensagem(String nome, String telefone, String email, String mensagem) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.mensagem = mensagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public String toString() {
        return  "Nome: " + nome +
                "\n\nMensagem:\n" + mensagem +
                "\n\nTelefone: " + telefone +
                "\nE-mail: " + email;
    }
}
