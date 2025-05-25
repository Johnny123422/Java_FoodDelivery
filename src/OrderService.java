import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



public class OrderService {
    private final List<Orders> orders = new ArrayList<>();

    // Creează și salvează o comandă completă
    public Orders placeOrder(String orderId, Clients client, List<MenuItem> items, Driver driver, String paymentMethod) {
        double total = items.stream().mapToDouble(MenuItem::getPrice).sum();
        Payment payment = new Payment("PAY_" + orderId, total, paymentMethod, LocalDateTime.now());

        Orders order = new Orders(orderId, client, items, payment, null);
        Delivery delivery = new Delivery(order, driver, LocalDateTime.now().plusMinutes(30), "in tranzit");

        order.setDelivery(delivery);

        orders.add(order);
        return order;
    }

    public List<Orders> getAllOrders() {
        return orders;
    }

    public void printOrderDetails(Orders order) {
        System.out.println("=== Comanda #" + order.getId() + " ===");
        System.out.println("Client: " + order.getClient().name());
        System.out.println("Produse:");
        for (MenuItem item : order.getMenuItem()) {
            System.out.println("- " + item.getName() + " (" + item.getPrice() + " RON)");
        }
        System.out.println("Total: " + order.getTotalPrice() + " RON");
        System.out.println("Plată prin: " + order.getPayment().getMethod());
        System.out.println("Livrat de: " + order.getDelivery().getDriver().name() + ", status: " + order.getDelivery().getStatus());
    }
}