import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class OrdersDAO {
    private final ClientDAO clientDAO = new ClientDAO();  // ca să putem încărca și clienții

    public List<Orders> getAllOrders() throws SQLException {
        List<Orders> orders = new ArrayList<>();

        String sql = "SELECT o.id, c.name, c.last_name, c.phone_number, a.city, a.street " +
                     "FROM orders o " +
                     "JOIN clients c ON o.client_id = c.id " +
                     "JOIN adress a ON c.adress_id = a.id";

        try (Connection conn = OracleConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
             
                Adress adress = new Adress(
                        rs.getString("city"),
                        rs.getString("street")
                );

               
                Clients client = new Clients(
                        rs.getString("name"),
                        rs.getString("last_name"),
                        rs.getString("phone_number"),
                        adress
                );

               
                Orders order = new Orders(
                        rs.getString("id"),
                        client,
                        new ArrayList<>(), 
                        null, 
                        null  
                );

                orders.add(order);
            }
        }

        return orders;
    }
}
