import java.util.List;

public class Order {
    int orderID;
    List<Object> products;
    String status;
    double totalPrice;
    String shippingAddress;

    public Order(int orderID, List<Object> products, String status, double totalPrice, String shippingAddress) {
        this.orderID = orderID;
        this.products = products;
        this.status = status;
        this.totalPrice = totalPrice;
        this.shippingAddress = shippingAddress;
    }
}
