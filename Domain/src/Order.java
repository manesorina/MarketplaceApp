import java.util.List;

public class Order implements Identifiable{
    private int id;
    private List<Product> products;
    private String status;
    private double totalPrice;
    private String shippingAddress;
    private User buyer;
    private User seller;

    public Order(List<Product> products, String status, String shippingAddress, User buyer, User seller) {
        this.products = products;
        this.status = status;
        this.shippingAddress = shippingAddress;
        this.buyer=buyer;
        this.seller=seller;
        this.totalPrice = calculateTotalPrice();
    }

    @Override
    public int getId() {return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void setBuyer(User buyer){this.buyer=buyer;}

    public User getBuyer(){return buyer;}

    private double calculateTotalPrice() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", products=" + products +
                ", status='" + status + '\'' +
                ", totalPrice=" + totalPrice +
                ", shippingAddress='" + shippingAddress + '\'' +
                ", buyer=" + buyer +
                '}';
    }
}
