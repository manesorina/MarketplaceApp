abstract class Account {
    String userName;
    String password;
    String email;
    String phone;

    abstract boolean authenticate(String userName,String password);
    abstract boolean addToCategory(Product product);
    abstract boolean removeFromCategory(Product product);
}
