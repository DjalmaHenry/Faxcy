// Classe Cadastro
package aplicacao;

import br.faxcy.sistema.LSESemRepetidos;
import java.util.Scanner;

public class Sistema {

    private LSESemRepetidos<Usuario> usuarios;

    public Sistema() {
        usuarios = new LSESemRepetidos();
    }
    
    public void inserirValor(String login) {
        Scanner in = new Scanner(System.in);
        String nome, senha;
        boolean result;
        Usuario usuA = new Usuario(login);
        result = usuarios.buscaNum(usuA);
        if (result == true) {
            System.err.println("Login já existe! Cadastro não efetuado!");
        } else {
            System.out.println("Informe o Nome: ");
            System.out.print("-> ");
            nome = in.next();
            in.nextLine();
            System.out.println("Informe a senha: ");
            System.out.print("-> ");
            senha = in.next();
            in.nextLine();
            Usuario usuB = new Usuario(login, nome, senha);
            usuarios.inserirValorSemV(usuB);
        }
    }
    
    public boolean verificaSenha(String senha){
        int contL = 0, contD = 0;
        if(senha.length() < 6){
            return false;
        }
        for(int i = 0; i < senha.length();i++){
            if(Character.isLetter(senha.charAt(i))){
                contL++;
            }
            if(Character.isDigit(senha.charAt(i))){
                contD++;
            }
        }
        if(contL < 3){
            return false;
        } else if(contD < 3){
            return false;
        } else {
            return true;
        }
    }

    public void alterarValor(String cpf) {
        Scanner in = new Scanner(System.in);
        String telefone, email;
        Usuario usu;
        boolean result;
        int op;
        Usuario usuA = new Usuario(cpf, "", "", "");
        usu = usuarios.buscarObjeto(usuA);
        if (usu == null) {
            System.err.println("CPF NÃO existe!");
        } else {
            do {
                System.out.println("O que deseja alterar?");
                System.out.println("1 - E-mail e Telefone.");
                System.out.println("2 - E-mail.");
                System.out.println("3 - Telefone.");
                System.out.println("0 - Cancelar operação.");
                System.out.println("Informe a opção:");
                System.out.print("-> ");
                op = in.nextInt();
                switch (op) {
                    case 1:
                        System.out.println("Informe o E-mail: ");
                        System.out.print("-> ");
                        email = in.next();
                        in.nextLine();
                        System.out.println("Informe o Telefone: ");
                        System.out.print("-> ");
                        telefone = in.next();
                        in.nextLine();
                        usu.atualizaEmail(email);
                        usu.atualizaTelefone(telefone);
                        System.out.println("Alteração efetuada com sucesso!");
                        break;
                    case 2:
                        System.out.println("Informe o E-mail: ");
                        System.out.print("-> ");
                        email = in.next();
                        in.nextLine();
                        usu.atualizaEmail(email);
                        System.out.println("Alteração efetuada com sucesso!");
                        break;
                    case 3:
                        System.out.println("Informe o Telefone: ");
                        System.out.print("-> ");
                        telefone = in.next();
                        in.nextLine();
                        usu.atualizaTelefone(telefone);
                        System.out.println("Alteração efetuada com sucesso!");
                        break;
                    case 0:
                        System.out.println("Operação cancelada.");
                        break;
                    default:
                        System.err.println("Erro, opção inválida.");
                        break;
                }
            } while (op != 0 && op != 1 && op != 2 && op != 3);
        }
    }

    public void exibirValor(String cpf) {
        Scanner in = new Scanner(System.in);
        String nome, telefone, email;
        Usuario aux;
        boolean result;
        Usuario usu = new Usuario(cpf, "", "", "");
        aux = usuarios.buscarObjeto(usu);
        if (aux == null) {
            System.err.println("CPF NÃO existe!");
        } else {
            System.out.println(aux);

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
