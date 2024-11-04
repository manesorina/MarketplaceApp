import java.util.List;

public class User extends Account implements Identifiable {
    private int id;
    double score;

    public User(String userName, String password, String email, String phone, double score){
        this.userName=userName;
        this.password=password;
        this.email=email;
        this.phone=phone;
        this.score=0.0;
    }

    @Override
    boolean authenticate(String userName, String password) {
        return userName.equals(this.userName) && password.equals(this.password);
    }

    @Override
    boolean addToCategory(Product product) {
        return false;
    }

    @Override
    boolean removeFromCategory(Product product) {
        return false;
    }

    @Override
    public int getId() {
        return id;
    }
    public void setId(int id) {this.id = id;}
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void sendOffer() {};
    public void acceptOffer() {};
    public void denyOffer() {};
    public void writeReview() {};
    public void deleteReview() {};
    public void placeOrder(List<Product> products, String shippingAddress) {};
    public void getOrders() {};
    public void addProduct() {};
    public void removeProduct() {};
    public void getProducts() {};
    public void getFavorites() {};


}
