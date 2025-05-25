import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class PaymentDAO {
    public List<Payment> getAllPayments() throws SQLException {
        List<Payment> payments = new ArrayList<>();

        String sql = "SELECT id, amount, method, payment_time FROM payment";

        try (Connection conn = OracleConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Payment payment = new Payment(
                        rs.getString("id"),
                        rs.getDouble("amount"),
                        rs.getString("method"),
                        rs.getTimestamp("payment_time").toLocalDateTime()
                );
                payments.add(payment);
            }
        }

        return payments;
    }
}