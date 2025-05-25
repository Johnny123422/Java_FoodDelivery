import java.util.*;

public class ExceptionTreat {

    public static void valideazaUseri(List<Clients> clients, List<Driver> drivers) throws UserException {
        List<user> users = new ArrayList<>();
        
        users.addAll(clients);
        users.addAll(drivers);

        for (user u : users) {
            if (!u.phoneNumber().matches("^07\\d{8}$")) {
                throw new PhoneNumberException("Numar de telefon invalid: " + u.phoneNumber());
            }

            if (Character.isLowerCase(u.name().charAt(0))) {
                throw new InvalidNameException("Prenumele incepe cu litera mica: " + u.name());
            }

            if (Character.isLowerCase(u.lastName().charAt(0))) {
                throw new InvalidNameException("Numele de familie incepe cu literă mica: " + u.lastName());
            }

            if (u instanceof Clients client) {
                if (Character.isLowerCase(client.adress().getCity().charAt(0))) {
                    throw new InvalidAdressException("Oras scris incorect: " + client.adress().getCity());
                }
                if (Character.isLowerCase(client.adress().getStreet().charAt(0))) {
                    throw new InvalidAdressException("Strada scrisa incorect: " + client.adress().getStreet());
                }
            }

            System.out.println("Utilizator valid: " + u.name() + " " + u.lastName());
        }
    }
}
