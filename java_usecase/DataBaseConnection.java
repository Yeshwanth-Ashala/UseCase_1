import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DataBaseConnection {
    private static final String url="jdbc:oracle:thin:@localhost:1521:xe";
    private static final String user="system";
    private static final String pass="8443";
    public static Connection getConnection(){
    Connection con=null;
    try {
        Class.forName("oracle.jdbc.driver.OracleDriver"); 
        con=DriverManager.getConnection(url,user,pass);
    }
    catch(ClassNotFoundException e) {
        System.out.println("Driver JAR not found in Classpath!");
    }
    catch(SQLException e){
        System.out.println("Connection failed: " + e.getMessage());
    }
    return con;
}
}
