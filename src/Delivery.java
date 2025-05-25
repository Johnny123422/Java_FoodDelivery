import java.time.LocalDateTime;

public class Delivery {
    private Orders order;
    private Driver driver;
    private LocalDateTime deliveryTime;
    private String status;

    public Delivery(Orders order, Driver driver, LocalDateTime deliveryTime, String status) {
        this.order = order;
        this.driver = driver;
        this.deliveryTime = deliveryTime;
        this.status = status;
    }

    

    public Orders getOrder() {
        return order;
    }

    public Driver getDriver() {
        return driver;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public String getStatus() {
        return status;
    }
}
