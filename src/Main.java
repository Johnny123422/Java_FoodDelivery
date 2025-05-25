import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

interface user {
    String name();
    String lastName();
    String phoneNumber();
}
public class Main {

    private final Scanner scanner = new Scanner(System.in);
    private final Service service = new Service();

    public static void main(String[] args) {
        new Main().start();
    }

    public void start() {
        while (true) {
            System.out.println("\n=== Meniu Food Delivery ===");
            System.out.println("1. Adauga client");
            System.out.println("2. Sterge client");
            System.out.println("3. Afiseaza toti clientii");
            System.out.println("4. Cauta client dupa nume");
            System.out.println("5. Cauta restaurante in apropiere");
            System.out.println("6. Adauga driver");
            System.out.println("7. Afisare driveri");
            System.out.println("8. Iesire");
            System.out.print("Alege o optiune: ");

            int choice = Integer.parseInt(scanner.nextLine());

            try {
                switch (choice) {
                    case 1 -> adaugaClient();
                case 2 -> stergeClient();
                case 3 -> afiseazaClienti();
                case 4 -> cautaClient();
                case 5 -> cautaRestauranteInOras();
                case 6 -> adaugaDriver();
                case 7 -> afiseazaDriver();
                case 8 -> {
                    System.out.println("La revedere!");
                    return;
                }
                    default -> System.out.println("Opțiune invalidă!");
                }
            } catch (SQLException e) {
                System.out.println("Eroare la accesarea bazei de date: " + e.getMessage());
            }
        }
    }

    private void adaugaClient() throws SQLException {
        System.out.print("Nume: ");
        String nume = scanner.nextLine();
        System.out.print("Prenume: ");
        String prenume = scanner.nextLine();
        System.out.print("Telefon (07xxxxxxxx): ");
        String telefon = scanner.nextLine();

        if (!service.valideazaTelefon(telefon)) {
            System.out.println("Număr de telefon invalid!");
            return;
        }
        int adresaId = 1; 

        Clients client = new Clients(nume, prenume, telefon, null); 
        service.adaugaClient(client, adresaId);

        System.out.println("Client adaugat!");
    }

    private void stergeClient() throws SQLException {
        System.out.print("Nume client de sters: ");
        String nume = scanner.nextLine();
        System.out.print("Prenume client de sters: ");
        String prenume = scanner.nextLine();
        service.stergeClient(nume, prenume);
        System.out.println("Client sters!");
    }

    private void afiseazaClienti() throws SQLException {
         try {
        List<Clients> clienti = service.afiseazaTotiClientii();

        ExceptionTreat.valideazaUseri(clienti, new ArrayList<>());  
        for (Clients c : clienti) {
            System.out.println("- " + c.name() + " " + c.lastName() + ", Tel: " + c.phoneNumber());
        }
    } catch (UserException e) {
        System.out.println("Eroare de validare: " + e.getMessage());
    } catch (SQLException e) {
        System.out.println("Eroare la interogarea bazei de date: " + e.getMessage());
    }
    }

    private void afiseazaDriver() throws SQLException {
         try {
        List<Driver> drivers = service.afiseazaTotiDriverii();

        ExceptionTreat.valideazaUseri(new ArrayList<>(), drivers);  
        for (Driver d : drivers) {
            System.out.println("- " + d.name() + " " + d.lastName() + ", Tel: " + d.phoneNumber());
        }
    } catch (UserException e) {
        System.out.println("Eroare de validare: " + e.getMessage());
    } catch (SQLException e) {
        System.out.println("Eroare la interogarea bazei de date: " + e.getMessage());
    }
    }


    private void cautaClient() throws SQLException {
        System.out.print("Nume client cautat: ");
        String nume = scanner.nextLine();
        var clientOpt = service.cautaClientDupaNume(nume);
        if (clientOpt.isPresent()) {
            Clients c = clientOpt.get();
            System.out.println("Client gasit: " + c.name() + " " + c.lastName() + ", Tel: " + c.phoneNumber());
        } else {
            System.out.println("Clientul nu a fost gasit.");
        }
    }

    private void cautaRestauranteInOras() throws SQLException {
    System.out.print("Introdu orasul: ");
    String oras = scanner.nextLine();

    List<Restaurant> restaurante = service.cautaRestauranteInOras(oras);

    if (restaurante.isEmpty()) {
        System.out.println("Nu s-au gssit restaurante in " + oras + ".");
    } else {
        System.out.println("Restaurante in " + oras + ":");
        for (Restaurant r : restaurante) {
            System.out.println("- " + r.getName() + ", pe strada " + r.getStreet() + ", orasul" + r.getCity());
        }
    }
}

    private void adaugaDriver() throws SQLException {
        System.out.print("Nume: ");
        String nume = scanner.nextLine();
        System.out.print("Prenume: ");
        String prenume = scanner.nextLine();
        System.out.print("Telefon (07xxxxxxxx): ");
        String telefon = scanner.nextLine();

        if (!service.valideazaTelefon(telefon)) {
            System.out.println("Numar de telefon invalid!");
            return;
        }
        

        Driver driver = new Driver(nume, prenume, telefon); 
        service.adaugaDriver(driver);

        System.out.println("Driver adaugat!");
    }

}
