// Classe Cliente
package aplicacao;

public class Usuario implements Comparable<Usuario> {

    private String login;
    private String nome;
    private String senha;
    private String[] listaAmigos;
    private String[] listaAmigosPendentes;
    private int qtdListaAmigos;
    private int qtdListaAmigosPendentes;

    public Usuario(String login, String nome, String senha) {
        this.login = login;
        this.nome = nome;
        this.senha = senha;
        listaAmigos = new String[500];
        listaAmigosPendentes = new String[100];
        qtdListaAmigos = 0;
        qtdListaAmigosPendentes = 0;
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

    public String getListaAmigos(int qtd) {
        return this.listaAmigos[qtd];
    }

    public int getQtdListaAmigos() {
        return this.qtdListaAmigos;
    }

    public String getListaAmigosPendentes(int qtd) {
        return this.listaAmigosPendentes[qtd];
    }

    public int getQtdListaAmigosPendentes() {
        return this.qtdListaAmigosPendentes;
    }

    private void setNome(String novoNome) {
        this.nome = novoNome;
    }

    private void setSenha(String novaSenha) {
        this.senha = novaSenha;
    }

    public void setListaAmigosA(String login) {
        int i;
        boolean achou = false;
        if (qtdListaAmigos == 0) {
            listaAmigos[qtdListaAmigos] = login;
            qtdListaAmigos++;
        } else {
            if (login.compareTo(listaAmigos[qtdListaAmigos - 1]) == 1) {
                listaAmigos[qtdListaAmigos] = login;
                qtdListaAmigos++;
            } else {
                for (i = 0; i < qtdListaAmigos; i++) {
                    if (login.compareTo(listaAmigos[i]) == -1) {
                        break;
                    }
                }
                for (int j = qtdListaAmigos; j < i; j--) {
                    listaAmigos[j] = listaAmigos[j - 1];
                }
                listaAmigos[i] = login;
                qtdListaAmigos++;
            }
        }
    }

    public boolean setListaAmigosB(String login) {
        int i;
        boolean achou = false;
        for (i = 0; i < qtdListaAmigosPendentes; i++) {
            if (listaAmigosPendentes[i].compareTo(login) == 0) {
                achou = true;
                break;
            }
        }
        if (achou == true) {
            for (int j = i; j < qtdListaAmigosPendentes; j++) {
                listaAmigosPendentes[i] = listaAmigosPendentes[i + 1];
            }
            qtdListaAmigosPendentes--;
            if (qtdListaAmigos == 0) {
                listaAmigos[qtdListaAmigos] = login;
                qtdListaAmigos++;
                return true;
            } else {
                if (login.compareTo(listaAmigos[qtdListaAmigos - 1]) == 1) {
                    listaAmigos[qtdListaAmigos] = login;
                    qtdListaAmigos++;
                    return true;
                } else {
                    for (i = 0; i < qtdListaAmigos; i++) {
                        if (login.compareTo(listaAmigos[i]) == -1) {
                            break;
                        }
                    }
                    for (int j = qtdListaAmigos; j < i; j--) {
                        listaAmigos[j] = listaAmigos[j - 1];
                    }
                    listaAmigos[i] = login;
                    qtdListaAmigos++;
                    return true;
                }
            }
        } else {
            return false;
        }
    }

    public void setListaAmigosPendentes(String login) {
        listaAmigosPendentes[qtdListaAmigosPendentes] = login;
        qtdListaAmigosPendentes++;
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
