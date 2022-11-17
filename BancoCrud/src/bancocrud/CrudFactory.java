package bancocrud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class CrudFactory {
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rset;   
    
    VerificadorCpf verificador = new VerificadorCpf();
    
    public void adicionar(ContaBanco conta) {
        String query = "INSERT INTO DadosCliente (CPF, Nome, TipoConta, Saldo) VALUES (?, ?, ?, ?)";
            
	try {
            verificador.verificarCpf(conta.getCpf());
            if (verificador.getExiste() == true) {
                conn = ConnectionFactory.createConnection();
                pstmt = conn.prepareStatement(query);
                pstmt.setString(1, conta.getCpf());
                pstmt.setString(2, conta.getDono());
                pstmt.setString(3, conta.getTipo());
                pstmt.setFloat(4, conta.getSaldo());
                conta.setStatus(true);
                pstmt.execute();
                pstmt.close();
                JOptionPane.showMessageDialog(null, "Cpf válido!", "Aviso", JOptionPane.WARNING_MESSAGE);
                JOptionPane.showMessageDialog(null, "Conta aberta com sucesso!");
            } else if (verificador.getExiste() == false) {
                JOptionPane.showMessageDialog(null, "Não foi possível inserir a pessoa no banco, CPF inválido!", "Aviso", JOptionPane.WARNING_MESSAGE);
                //JOptionPane.showMessageDialog(null,"Não foi possível inserir a pessoa no banco, CPF inválido!");
            }
	} catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Não foi possível inserir a pessoa no banco!");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
	}
    }
    
    public void remover(ContaBanco conta) {
        String query = "SELECT Saldo FROM DadosCliente WHERE CPF = ?";
        Float saldo = 0f;
        try {
            conn = ConnectionFactory.createConnection();

            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, conta.getCpf());            
            rset = pstmt.executeQuery();            
            saldo = rset.getFloat("Saldo");
            
            pstmt.execute();
            pstmt.close();
        } catch (Exception e){
        }
        if (saldo > 0) {
            JOptionPane.showMessageDialog(null, "Conta não pode ser fechada, pois ainda há dinheiro");
            try {
                    if (pstmt != null) {
                        pstmt.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                    if (rset != null) {
                        rset.close();
                    }
                } catch(Exception e) {
                    System.err.println(e.getClass().getName() + ": " + e.getMessage());
                }
        } else if (saldo < 0) {
            JOptionPane.showMessageDialog(null, "Conta não pode fechar pois está em débito");
            try {
                    if (pstmt != null) {
                        pstmt.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                    if (rset != null) {
                        rset.close();
                    }
                } catch(Exception e) {
                    System.err.println(e.getClass().getName() + ": " + e.getMessage());
                }
        } else {
            String sql = "DELETE FROM DadosCliente WHERE CPF = ?";
            try {
                conn = ConnectionFactory.createConnection();

                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, conta.getCpf());
                conta.setStatus(false);

                pstmt.execute();
                pstmt.close();
            } catch (Exception e) {
                System.err.println("Não foi possível remover a pessoa do banco!");
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            } finally {
                try {
                    if (pstmt != null) {
                        pstmt.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                    if (rset != null) {
                        rset.close();
                    }
                } catch(Exception e) {
                    System.err.println(e.getClass().getName() + ": " + e.getMessage());
                }
            }
            conta.setCpf("");
            conta.setDono("");
            conta.setTipo("");
            conta.setSaldo(0);
            conta.setStatus(false);
            JOptionPane.showMessageDialog(null, "Conta fechada com sucesso");
        }
    }
    
    public void atualizar(ContaBanco conta) {
        String query = "UPDATE DadosCliente SET Nome = ?, TipoConta = ?, Saldo = ?" 
                + "WHERE CPF = ?";
        try {
            conn = ConnectionFactory.createConnection();
            
            pstmt = conn.prepareStatement(query);

            pstmt.setString(1, conta.getDono());
            pstmt.setString(2, conta.getTipo());
            pstmt.setFloat(3, conta.getSaldo());
            pstmt.setString(4, conta.getCpf());
            
            pstmt.execute();
            pstmt.close();
            
        } catch (Exception e) {
            System.err.println("Não foi possível atualizar os dados da pessoa no banco!");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch(Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }
    }
    
    public void buscarDadosCliente(ContaBanco conta) {
        String query = "SELECT * FROM DadosCliente WHERE CPF = ?";
        try {
            conn = ConnectionFactory.createConnection();
            
            pstmt = conn.prepareStatement(query);

            pstmt.setString(1, conta.getCpf());
            rset = pstmt.executeQuery();            
            conta.setSaldo(rset.getFloat("Saldo"));
            conta.setDono(rset.getString("Nome"));
            conta.setTipo(rset.getString("TipoConta"));

            pstmt.execute();
            pstmt.close();
            
        } catch (Exception e) {
            System.err.println("Não foi possível atualizar os dados da pessoa no banco!");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch(Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }
    }
    
    public List<ContaBanco> buscar() {
        String query = "SELECT * FROM DadosCliente";
        
        List<ContaBanco> contas = new ArrayList<ContaBanco>();
      
        try {
            conn = ConnectionFactory.createConnection();
            pstmt = conn.prepareStatement(query);
            
            rset = pstmt.executeQuery();
            
            while (rset.next()) {
                ContaBanco conta = new ContaBanco();
                
                conta.setCpf(rset.getString("CPF"));
                conta.setDono(rset.getString("Nome"));
                conta.setTipo(rset.getString("TipoConta"));
                conta.setSaldo(rset.getFloat("Saldo"));
                
                contas.add(conta);
            }   
	} catch (Exception e) {
            System.err.println("Não foi possível buscar os dados do banco!");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
	} finally {
            try {
                if (rset != null) {
                    rset.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch(Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }
	return contas;
    }
}
