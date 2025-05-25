import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditService {
    private static final String FILE_PATH = "audit_log.csv";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void logAction(String actionName) {
        String timestamp = LocalDateTime.now().format(formatter);
        String line = actionName + "," + timestamp + "\n";

        try (FileWriter fw = new FileWriter(FILE_PATH, true)) {  
            fw.write(line);
        } catch (IOException e) {
            System.err.println("Eroare la scrierea în fisierul de audit: " + e.getMessage());
        }
    }
}
