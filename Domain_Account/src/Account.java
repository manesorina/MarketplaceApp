abstract class Account {
    int accountID;
    String userName;
    String password;
    String email;
    String phone;

    abstract boolean authenticate(String userName,String password);

    public int getAccountID() {
        return accountID;
    }

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

    public void placeOrder() {};

    public void getOrders() {};

    public void addProduct() {};

    public void removeProduct() {};

    public void getProducts() {};

    public void getFavorites() {};

}
