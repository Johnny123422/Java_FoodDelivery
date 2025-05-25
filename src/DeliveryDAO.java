import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;



public class DeliveryDAO {
    public List<Delivery> getAllDeliveries() throws SQLException {
        List<Delivery> deliveries = new ArrayList<>();

        String sql = "SELECT d.order_id, d.delivery_time, d.status, dr.name, dr.last_name, dr.phone_number " +
                     "FROM delivery d JOIN driver dr ON d.driver_id = dr.id";

        try (Connection conn = OracleConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Driver driver = new Driver(
                        rs.getString("name"),
                        rs.getString("last_name"),
                        rs.getString("phone_number")
                );

                Delivery delivery = new Delivery(
                        null, // order va fi legat mai târziu
                        driver,
                        rs.getTimestamp("delivery_time").toLocalDateTime(),
                        rs.getString("status")
                );

                deliveries.add(delivery);
            }
        }

        return deliveries;
    }
}