
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ClientDAO {

    public List<Clients> getAllClients() throws SQLException {
        List<Clients> list = new ArrayList<>();

        String sql = "SELECT c.id, c.name, c.last_name, c.phone_number, a.city, a.street "
                + "FROM clients c JOIN adress a ON c.adress_id = a.id";

        try (Connection conn = OracleConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Adress adress = new Adress(rs.getString("city"), rs.getString("street"));
                Clients client = new Clients(rs.getString("name"), rs.getString("last_name"),
                        rs.getString("phone_number"), adress);
                list.add(client);
            }
        }

        return list;
    }

    public void deleteClientByName(String name, String lastName) throws SQLException {
        String sql = "DELETE FROM clients WHERE LOWER(name) = ? AND LOWER(last_name) = ?";
        try (Connection conn = OracleConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name.toLowerCase());
            stmt.setString(2, lastName.toLowerCase());
            stmt.executeUpdate();
        }
    }

    public void insertClient(Clients client, int adressId) throws SQLException {
    String sql = "INSERT INTO clients (name, last_name, phone_number, adress_id) VALUES (?, ?, ?, ?)";

    try (Connection conn = OracleConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, client.name());
        stmt.setString(2, client.lastName());
        stmt.setString(3, client.phoneNumber());
        stmt.setInt(4, adressId);

        stmt.executeUpdate();
    }
}

}
