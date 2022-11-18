/*Projeto onde eu simulo um banco, onde é possível o cliente sacar, depositar e 
realizar pagamentos com o seu dinheiro, entre outras ações.*/

package bancocrud;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class BancoCrud {

    public static void main(String[] args) {
        CrudFactory crud = new CrudFactory();
        ContaBanco usuario1 = new ContaBanco();
        List<ContaBanco> contas = new ArrayList<ContaBanco>();
        
        String nome;
        String tipo;
        String cpf;
        contas = crud.buscar();
        
        float pgtMensal;
        int busca = 0;
        int opção;
        int opção2;

        do { 
            opção = Integer.parseInt(JOptionPane.showInputDialog("Digite 1, caso você queira abrir uma conta.\n" 
                    + "\nDigite 2, caso você já tenha uma conta e queira acessa-la.\n" 
                    + "\nDigite 3, caso você queira fechar a sua conta existente.\n" 
                    + "\nDigite 0, caso você queira sair do programa!"));
            if (opção == 1) {
                nome = JOptionPane.showInputDialog("Digite o seu nome: ");
                usuario1.setDono(nome);
                
                tipo = JOptionPane.showInputDialog("Digite CC, caso você queira uma conta corrente, " 
                        + "\ne CP, caso você queira uma conta poupança.");
                
                cpf = JOptionPane.showInputDialog("Digite o seu CPF(somente os números): ");
                usuario1.setCpf(cpf);
                
                usuario1.abrirConta(tipo);                
                crud.adicionar(usuario1);
            } else if (opção == 2) {
                cpf = JOptionPane.showInputDialog("Digite o seu CPF(somente os números): ");
                usuario1.setCpf(cpf);
                
                if (contas == null) {
                    JOptionPane.showMessageDialog(null, "Conta inexistente!");
                } else {
                    do {
                        usuario1.setStatus(true);
                        crud.buscarDadosCliente(usuario1);
                        usuario1.mostrarEstadoAtual();
                        opção2 = Integer.parseInt(JOptionPane.showInputDialog("Digite 1, caso você queira fazer um depósito.\n" 
                            + "\nDigite 2, caso você queira realizar um saque.\n" 
                            + "\nDigite 3, caso você queira pagar a mensalidade.\n" 
                            + "\nDigite 4, caso você queira alterar algum dado.\n "
                            + "\nDigite 0, caso você queira sair do programa!"));;
                        if (opção2 == 1) {
                            float deposito = Float.parseFloat(JOptionPane.showInputDialog("Digite abaixo o valor que você gostaria de depositar!"));                            
                            usuario1.depositar(deposito);
                            crud.atualizar(usuario1);
                        } else if (opção2 == 2) {
                            float saque = Float.parseFloat(JOptionPane.showInputDialog("Digite abaixo o valor que você gostaria de sacar!"));
                            usuario1.sacar(saque);
                            crud.atualizar(usuario1);
                        } else if (opção2 == 3) {
                            pgtMensal = 0;
                            usuario1.pagarMensal(pgtMensal);
                            crud.atualizar(usuario1);
                        } else if (opção2 == 4) {
                            for (int i = 0; i < contas.size(); i++) {
                                if (contas.get(i).getCpf().equals(cpf)) {
                                    try {
                                        nome = JOptionPane.showInputDialog("Digite o seu nome: ");
                                        usuario1.setDono(nome);
                                        tipo = JOptionPane.showInputDialog("Digite CC, caso você queira uma conta corrente, " 
                                                + "\ne CP, caso você queira uma conta poupança.");
                                        usuario1.setTipo(tipo);
                                        crud.atualizar(usuario1);
                                        break;
                                    } catch(Exception e) {
                            
                                    }
                                }
                            }
                        }
                    } while (opção2 != 0);
                }
            } else if (opção == 3) {
                cpf = JOptionPane.showInputDialog("Digite o seu CPF(somente os números): ");
                usuario1.setCpf(cpf);
                
                for (int i = 0; i < contas.size(); i++) {
                    if (contas.get(i).getCpf().equals(cpf)) {
                        try {
                            busca = 1;
                            crud.remover(usuario1);
                            break;
                        } catch(Exception e) {
                            
                        }
                    }
		}
                if (busca == 0) {
                    JOptionPane.showMessageDialog(null, "A conta não existe");
                    break;
                }
            }
        } while (opção != 0);
    }
}
