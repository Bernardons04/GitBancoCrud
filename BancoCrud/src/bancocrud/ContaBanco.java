package bancocrud;

import javax.swing.JOptionPane;

public class ContaBanco {
    private String cpf;
    private String tipo;
    private String dono;
    private float saldo;
    private boolean status;

    public void mostrarEstadoAtual(){
        JOptionPane.showMessageDialog(null, "------------------------------" 
            + "\nCPF: " + this.getCpf() 
            + "\nDono: " + this.getDono() 
            + "\nTipo: " + this.getTipo()
            + "\nSaldo: " + this.getSaldo());
    }
   
    public void abrirConta(String t){
        this.setTipo(t);
        this.setStatus(true);
        if ("CC".equals(t) || "cc".equals(t)) {
            this.setSaldo(50);
            this.setTipo("Corrente");
        } else if ("CP".equals(t) || "cp".equals(t)){
            this.setSaldo(150);
            this.setTipo("Poupança");
        }
    }

    public void depositar(float v){
        if (this.getStatus()) {
            this.setSaldo(this.getSaldo() + v);
            JOptionPane.showMessageDialog(null, "Depósito realizado na conta de " + this.getDono());
        } else {
            JOptionPane.showMessageDialog(null, "Impossível depositar em uma conta fechada!");
        }
    }
    
    public void sacar(float s){        
        if (this.getStatus()) {
            if (this.getSaldo() >= s) {
                this.setSaldo(this.getSaldo() - s);
                JOptionPane.showMessageDialog(null, "Saque realizado na conta de " + this.getDono());
            } else {
                JOptionPane.showMessageDialog(null, "Impossível sacar, saldo insuficiente!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Impossível sacar em uma conta fechada!");
        }
    }
    
    public void pagarMensal(float pgt){
        if ("corrente".equals(this.getTipo()) || "Corrente".equals(this.getTipo()) || "CORRENTE".equals(this.getTipo())) {
            pgt = 12;
        } else if ("poupança".equals(this.getTipo()) || "Poupança".equals(this.getTipo()) || "POUPANÇA".equals(this.getTipo())) {
            pgt = 20;
        }
        
        if (this.getStatus() == true) {
            if (this.getSaldo() >= pgt) {
                this.setSaldo(this.getSaldo() - pgt);
                JOptionPane.showMessageDialog(null, "Mensalidade paga com sucesso por " + this.getDono());
            } else {
                JOptionPane.showMessageDialog(null, "Impossível pagar a mensalidade, saldo insuficiente!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Impossível pagar a mensalidade em uma conta fechada!");
        }
    }
    
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String n) {
        this.cpf = n;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String t) {
        this.tipo = t;
        if ("CC".equals(t) || "cc".equals(t)) {
            this.setTipo("Corrente");
        } else if ("CP".equals(t) || "cp".equals(t)){
            this.setTipo("Poupança");
        }
    }
    public String getDono() {
        return dono;
    }
    public void setDono(String dono) {
        this.dono = dono;
    }
    public float getSaldo() {
        return saldo;
    }
    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
    public boolean getStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }   
}
