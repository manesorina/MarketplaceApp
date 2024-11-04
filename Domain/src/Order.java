import java.util.List;

public class Order implements Identifiable{
    int id;
    List<Product> products;
    String status;
    double totalPrice;
    String shippingAddress;

    public Order(List<Product> products, String status, double totalPrice, String shippingAddress) {
        this.products = products;
        this.status = status;
        this.totalPrice = totalPrice;
        this.shippingAddress = shippingAddress;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
