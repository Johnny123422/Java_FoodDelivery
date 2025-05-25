
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DriverDAO {

    public List<Driver> getAllDrivers() throws SQLException {
        List<Driver> drivers = new ArrayList<>();

        String sql = "SELECT name, last_name, phone_number FROM driver";

        try (Connection conn = OracleConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Driver driver = new Driver(
                        rs.getString("name"),
                        rs.getString("last_name"),
                        rs.getString("phone_number")
                );
                drivers.add(driver);
            }
        }

        return drivers;
    }

    public void insertDriver(Driver driver) throws SQLException {
        String sql = "INSERT INTO driver (name, last_name, phone_number) VALUES (?, ?, ?)";

        try (Connection conn = OracleConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, driver.name());
            stmt.setString(2, driver.lastName());
            stmt.setString(3, driver.phoneNumber());

            stmt.executeUpdate();
        }
    }
}
