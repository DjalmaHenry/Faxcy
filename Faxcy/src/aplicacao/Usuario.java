// Classe Cliente
package aplicacao;

public class Usuario implements Comparable<Usuario> {

    private String login;
    private String nome;
    private String senha;

    public Usuario(String login, String nome, String senha) {
        this.login = login;
        this.nome = nome;
        this.senha = senha;
    }

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario(String login) {
        this.login = login;
    }

    public String getLogin() {
        return this.login;
    }

    public String getNome() {
        return this.nome;
    }

    private void setNome(String novoNome) {
        this.nome = novoNome;
    }

    private void setSenha(String novaSenha) {
        this.senha = novaSenha;
    }

    public boolean validaSenha(String senha) {
        if (this.senha.equals(senha)) {
            return true;
        } else {
            return false;
        }
    }

    public void atualizaNome(String nome) {
        this.setNome(nome);
    }

    public void atualizaSenha(String senha) {
        this.setSenha(senha);
    }

    public int compareTo(Usuario usuario) {
        int result;
        result = this.login.compareTo(usuario.login);
        return result;
    }
}
