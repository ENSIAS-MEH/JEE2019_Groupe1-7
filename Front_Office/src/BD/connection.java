package BD;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class connection {
     private Connection cnx=null;
    private Statement st=null;
    String username = "root";
    String password = "root";
    private String url="jdbc:mysql://localhost:8889/ecom_bd";
    
    public connection() throws SQLException, ClassNotFoundException
    {
        Class.forName("com.mysql.jdbc.Driver");
    cnx = DriverManager.getConnection(url, username, password);
    st = cnx.createStatement();
    }
    
    public Statement getStatement()
    {
    return st;
    }
}
