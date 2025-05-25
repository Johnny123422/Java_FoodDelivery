import java.sql.*;
import java.util.*;

public class AdressDAO {
    public List<Adress> getAllAdresses() throws SQLException {
        List<Adress> adresses = new ArrayList<>();

        String sql = "SELECT city, street FROM adress";

        try (Connection conn = OracleConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Adress adress = new Adress(rs.getString("city"), rs.getString("street"));
                adresses.add(adress);
            }
        }

        return adresses;
    }
}
