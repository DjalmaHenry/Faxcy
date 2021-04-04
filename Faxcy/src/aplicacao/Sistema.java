// Classe Cadastro
package aplicacao;

import br.faxcy.sistema.LSESemRepetidosOrdenado;
import java.util.Scanner;

public class Sistema {

    private LSESemRepetidosOrdenado<Usuario> usuarios;

    public Sistema() {
        usuarios = new LSESemRepetidosOrdenado();
    }

    public String logar(String login, String senha) {
        boolean result;
        Usuario usu = new Usuario(login);
        usu = usuarios.buscarObjeto(usu);
        if (usu == null) {
            System.err.println("Faxcy - Login inválido!");
            return null;
        } else {
            result = usu.validaSenha(senha);
            if (result == true) {
                return login;
            } else {
                System.err.println("Faxcy - Senha inválida!");
                return null;
            }
        }
    }

    public void inserirValor(String login) {
        Scanner in = new Scanner(System.in);
        String nome, senha;
        boolean result;
        Usuario usuA = new Usuario(login);
        result = usuarios.buscaNum(usuA);
        if (result == true) {
            System.err.println("Faxcy - Login já existe! Cadastro não efetuado!");
        } else {
            System.out.println("Informe um nome: ");
            System.out.print("-> ");
            nome = in.next();
            in.nextLine();
            System.out.println("Informe uma senha: ");
            System.out.print("-> ");
            senha = in.next();
            in.nextLine();
            while (verificaSenha(senha) == false) {
                System.err.println("Erro, senha fraca!");
                System.out.println("A senha deve conter no mínimo 6 caracteres. letras e números.");
                System.out.println("Informe uma senha: ");
                System.out.print("-> ");
                senha = in.next();
                in.nextLine();
            }
            Usuario usuB = new Usuario(login, nome, senha);
            usuarios.inseri(usuB);
            System.out.println("Faxcy - Cadastro realizado com sucesso!");
        }
    }

    public boolean verificaSenha(String senha) {
        int contL = 0, contD = 0;
        if (senha.length() < 6) {
            return false;
        }
        for (int i = 0; i < senha.length(); i++) {
            if (Character.isLetter(senha.charAt(i))) {
                contL++;
            }
            if (Character.isDigit(senha.charAt(i))) {
                contD++;
            }
        }
        if (contL < 1) {
            return false;
        } else if (contD < 1) {
            return false;
        } else {
            return true;
        }
    }

    public void alterarValor(String login) {
        Scanner in = new Scanner(System.in);
        String nome, senha;
        Usuario usu;
        int op;
        Usuario usuA = new Usuario(login);
        usu = usuarios.buscarObjeto(usuA);
        if (usu == null) {
            System.err.println("Faxcy - Login NÃO existe!");
        } else {
            do {
                System.out.println("=============================");
                System.out.println("O que deseja alterar?");
                System.out.println("1 - Nome e Senha.");
                System.out.println("2 - Nome.");
                System.out.println("3 - Senha.");
                System.out.println("0 - Cancelar operação.");
                System.out.println("Informe a opção:");
                System.out.print("-> ");
                op = in.nextInt();
                switch (op) {
                    case 1:
                        System.out.println("Informe um nome: ");
                        System.out.print("-> ");
                        nome = in.next();
                        in.nextLine();
                        System.out.println("Informe uma senha: ");
                        System.out.print("-> ");
                        senha = in.next();
                        in.nextLine();
                        while (verificaSenha(senha) == false) {
                            System.err.println("Erro, senha fraca!");
                            System.out.println("A senha deve conter no mínimo 6 caracteres. letras e números.");
                            System.out.println("Informe uma senha: ");
                            System.out.print("-> ");
                            senha = in.next();
                            in.nextLine();
                        }
                        usu.atualizaNome(nome);
                        usu.atualizaSenha(senha);
                        System.out.println("Faxcy - Alteração efetuada com sucesso!");
                        break;
                    case 2:
                        System.out.println("Informe um nome: ");
                        System.out.print("-> ");
                        nome = in.next();
                        in.nextLine();
                        usu.atualizaNome(nome);
                        System.out.println("Faxcy - Alteração efetuada com sucesso!");
                        break;
                    case 3:
                        System.out.println("Informe uma senha: ");
                        System.out.print("-> ");
                        senha = in.next();
                        in.nextLine();
                        while (verificaSenha(senha) == false) {
                            System.err.println("Erro, senha fraca!");
                            System.out.println("A senha deve conter no mínimo 6 caracteres. letras e números.");
                            System.out.println("Informe uma senha: ");
                            System.out.print("-> ");
                            senha = in.next();
                            in.nextLine();
                        }
                        usu.atualizaSenha(senha);
                        System.out.println("Faxcy - Alteração efetuada com sucesso!");
                        break;
                    case 0:
                        System.out.println("Faxcy - Operação cancelada.");
                        break;
                    default:
                        System.err.println("Erro, opção inválida.");
                        break;
                }
            } while (op != 0 && op != 1 && op != 2 && op != 3);
        }
    }

    public void adicionaAmigoPendente(String login, String amigo) {
        int i;
        Usuario usuAA, usuBB;
        Usuario usuA = new Usuario(amigo);
        usuAA = usuarios.buscarObjeto(usuA);
        if (usuAA == null) {
            System.err.println("Erro, esse usuário não existe.");
        } else if (login.compareTo(amigo) == 0) {
            System.err.println("Erro, você não pode adicionar você mesmo!");
        } else {
            Usuario usuB = new Usuario(login);
            usuBB = usuarios.buscarObjeto(usuB);
            for (i = 0; i < usuBB.getQtdListaAmigos(); i++) {
                if (usuBB.getListaAmigos(i).compareTo(amigo) == 0) {
                    System.err.println("Este usuário já está na lista de amigos.");
                    return;
                }
            }
            for (i = 0; i < usuAA.getQtdListaAmigosPendentes(); i++) {
                if (usuAA.getListaAmigosPendentes(i).compareTo(login) == 0) {
                    System.err.println("Erro, pedido de amizade já enviado!");
                    return;
                }
            }
            for (i = 0; i < usuBB.getQtdListaAmigosPendentes(); i++) {
                if (usuBB.getListaAmigosPendentes(i).compareTo(amigo) == 0) {
                    System.err.println("Este usuário já encontra-se na lista de pendentes!");
                    return;
                }
            }
            usuAA.setListaAmigosPendentes(login);
            System.out.println("Faxcy - Pedido de amizade enviado com sucesso!");
        }
    }

    public void adicionaAmigo(String login) {
        Scanner in = new Scanner(System.in);
        boolean result;
        Usuario usuAA, usuBB;
        Usuario usuB = new Usuario(login);
        usuBB = usuarios.buscarObjeto(usuB);
        if (usuBB.getQtdListaAmigosPendentes() == 0) {
            return;
        } else {
            String amigo;
            System.out.println("Informe o login do usuário que deseja aceitar:");
            System.out.print("-> ");
            amigo = in.next();
            in.nextLine();
            Usuario usuA = new Usuario(amigo);
            usuAA = usuarios.buscarObjeto(usuA);
            result = usuBB.setListaAmigosB(amigo);
            if (result == true) {
                usuAA.setListaAmigosA(login);
                System.out.println("Faxcy - Amigo adicionado com sucesso!");
            } else {
                System.err.println("Erro, usuário não consta na lista de pedidos de amizade.");
            }
        }
    }

    public void exibirAmigosPendentes(String login) {
        Usuario aux;
        Usuario usu = new Usuario(login);
        aux = usuarios.buscarObjeto(usu);
        if (aux.getQtdListaAmigosPendentes() == 0) {
            System.err.println("Lista de amigos pendentes vázia!");
        } else {
            System.out.println("Faxcy - Amigos Pendentes:");
            for (int i = 0; i < aux.getQtdListaAmigosPendentes(); i++) {
                System.out.println(aux.getListaAmigosPendentes(i));
            }
        }
    }

    public void exibirAmigos(String login) {
        Usuario aux;
        Usuario usu = new Usuario(login);
        aux = usuarios.buscarObjeto(usu);
        if (aux.getQtdListaAmigos() == 0) {
            System.err.println("Lista de amigos vázia!");
        } else {
            System.out.println("Faxcy - Lista de Amigos:");
            for (int i = 0; i < aux.getQtdListaAmigos(); i++) {
                System.out.println(aux.getListaAmigos(i));
            }
        }
    }

    public void removerValor(String cpf) {
        Usuario cli = new Usuario(cpf, "", "", "");
        usuarios.removeValor(cli);
    }

    public void exibirValores() {
        usuarios.exibirValores();
    }

}
