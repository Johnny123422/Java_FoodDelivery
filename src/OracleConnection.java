import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class OracleConnection {
    private static final String URL = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
    private static final String USER = "system";
    private static final String PASSWORD = "mysecurepassword";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    


}