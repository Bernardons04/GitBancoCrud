package bancocrud;

import javax.swing.JOptionPane; 
import java.util.Scanner;

public class VerificadorCpf {
    private boolean existe;
    
    static Scanner teclado = new Scanner(System.in);

    public String verificarCpf(String cpf1) {
        int i2 = 0;
        int somaCpf = 0;
        int multiplicador = 10;
        for (int i = 1; i <= 9; i++) {
            somaCpf += Integer.parseInt(cpf1.substring(i2, i)) * multiplicador;
            i2++;
            multiplicador--;
        }
        int verificador = Integer.parseInt(cpf1.substring(9, 10));
        int verificador2 = Integer.parseInt(cpf1.substring(10, 11));

        int resto = somaCpf % 11;

        int[] verificador3 = new int[11];

        int relogio2 = 0;
        for (int relogio = 1; relogio <= 11; relogio++) {
            verificador3[relogio2] = Integer.parseInt(cpf1.substring(relogio2, relogio));
            relogio2++;
        }
        
        if (verificador3[1] == verificador3[2] &&
                verificador3[1] == verificador3[3] &&
                verificador3[1] == verificador3[4] &&
                verificador3[1] == verificador3[5] &&
                verificador3[1] == verificador3[6] &&
                verificador3[1] == verificador3[7] &&
                verificador3[1] == verificador3[8] &&
                verificador3[1] == verificador3[9]) {
            //JOptionPane.showMessageDialog(null, "Cpf inválido!", "Aviso", JOptionPane.WARNING_MESSAGE);
            this.setExiste(false);
            System.exit(0);
        }

        //1 verificador terminado
        if (resto == 0 || resto == 1) {
            int digito10 = 0;

            if (verificador == digito10) {
                i2 = 0;
                multiplicador = 11;
                int soma3Cpf = 0;
                for (int i = 1; i <= 10; i++) {
                    soma3Cpf += Integer.parseInt(cpf1.substring(i2, i)) * multiplicador;
                    i2++;
                    multiplicador--;
                }

                //System.out.println(soma3Cpf);

                int numeroVerificador = 0;
                soma3Cpf = soma3Cpf % 11;
                //2 verificador  terminado
                if (soma3Cpf == 0 || soma3Cpf == 1) {
                    numeroVerificador = 0;
                    if (numeroVerificador == verificador2) {
                        //JOptionPane.showMessageDialog(null, "Cpf válido!", "Aviso", JOptionPane.WARNING_MESSAGE);
                        this.setExiste(true);
                    } else {
                        //JOptionPane.showMessageDialog(null, "Cpf inválido!", "Aviso", JOptionPane.WARNING_MESSAGE);
                        this.setExiste(false);
                    }
                } else if (soma3Cpf >= 2) {
                    i2 = 0;
                    multiplicador = 11;
                    soma3Cpf = 0;
                    for (int i = 1; i <= 10; i++) {
                        soma3Cpf += Integer.parseInt(cpf1.substring(i2, i)) * multiplicador;
                        i2++;
                        multiplicador--;
                    }
                    int resultado = 11 - (soma3Cpf % 11);

                    if (resultado == verificador2) {
                        //JOptionPane.showMessageDialog(null, "Cpf válido!", "Aviso", JOptionPane.WARNING_MESSAGE);
                        this.setExiste(true);
                    } else {
                        //JOptionPane.showMessageDialog(null, "Cpf inválido!", "Aviso", JOptionPane.WARNING_MESSAGE);
                        this.setExiste(false);
                    }
                }
            } else {
                //JOptionPane.showMessageDialog(null, "Cpf inválido!", "Aviso", JOptionPane.WARNING_MESSAGE);
                this.setExiste(false);
            }
            //1 verificador terminado
        } else if (resto >= 2) {
            int calculoResto_Onze = 11 - resto;

            int digito10 = calculoResto_Onze;
            //-----------
            if (verificador == digito10) {
                i2 = 0;
                multiplicador = 11;
                int soma3Cpf = 0;
                for (int i = 1; i <= 10; i++) {
                    soma3Cpf += Integer.parseInt(cpf1.substring(i2, i)) * multiplicador;
                    i2++;
                    multiplicador--;
                }
                int resultado = soma3Cpf % 11;
                if (resultado >= 2) {
                    int calculo = 11 - resultado;
                    if (calculo == verificador2) {
                        //JOptionPane.showMessageDialog(null, "Cpf válido!", "Aviso", JOptionPane.WARNING_MESSAGE);
                        this.setExiste(true);
                    } else {
                        //JOptionPane.showMessageDialog(null, "Cpf inválido!", "Aviso", JOptionPane.WARNING_MESSAGE);
                        this.setExiste(false);
                    }
                } else if (resultado == 0 || resultado == 1) {
                    int number11 = 0;
                    if (verificador2 == number11) {
                        //JOptionPane.showMessageDialog(null, "Cpf válido!", "Aviso", JOptionPane.WARNING_MESSAGE);
                        this.setExiste(true);
                    } else {
                        //JOptionPane.showMessageDialog(null, "Cpf inválido!", "Aviso", JOptionPane.WARNING_MESSAGE);
                        this.setExiste(false);
                    }
                } else {
                    //JOptionPane.showMessageDialog(null, "Cpf inválido!", "Aviso", JOptionPane.WARNING_MESSAGE);
                    this.setExiste(false);
                }
            } else {
                //JOptionPane.showMessageDialog(null, "Cpf inválido!", "Aviso", JOptionPane.WARNING_MESSAGE);
                this.setExiste(false);
            }
        } else {
            //JOptionPane.showMessageDialog(null, "Cpf inválido!", "Aviso", JOptionPane.WARNING_MESSAGE);
            this.setExiste(false);
        }
        return null;
    }
    
    public boolean getExiste() {
        return existe;
    }
    public void setExiste(boolean existe) {
        this.existe = existe;
    }
}