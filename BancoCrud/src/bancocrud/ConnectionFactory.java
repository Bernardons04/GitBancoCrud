package bancocrud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory {
    
    public static Connection createConnection() throws Exception {
        
        Class.forName("org.sqlite.JDBC");
        Connection conexao = DriverManager.getConnection("jdbc:sqlite:BancoCrud_DATABASE.db"); 
        
        return conexao;
    }
}

