package bancocrud;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    
    public static Connection createConnection() throws Exception {
        
        Class.forName("org.sqlite.JDBC");
        Connection conexao = DriverManager.getConnection("jdbc:sqlite:BancoCrud_DATABASE.db"); 
        
        return conexao;
    }
}

