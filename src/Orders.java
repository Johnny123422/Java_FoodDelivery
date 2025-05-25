import java.util.*;

public class Orders  {
    private final String id;
    public Clients client;
    private final List<MenuItem> items;
    public Payment payment;
    public Delivery delivery;


   public Orders(String id, Clients client, List<MenuItem> items, Payment payment, Delivery delivery) {
        this.id = id;
        this.client = client;
        this.items = items;
        this.payment =payment;
        this.delivery = delivery;
       
    }



    public String getId() {
        return id;
    }

    public void setDelivery(Delivery delivery) {
    this.delivery = delivery;
}

    public void setPayment(Payment payment) {
    this.payment = payment;
}

    public Clients getClient() {
        return client;
    }

    public List<MenuItem> getMenuItem() {
        return items;
    }
    public Payment getPayment() {
        return payment;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public double getTotalPrice() {
        return items.stream().mapToDouble(MenuItem::getPrice).sum();
    }

   
}