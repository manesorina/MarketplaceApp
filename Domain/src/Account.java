abstract class Account {
    int accountID;
    String userName;
    String password;
    String email;
    String phone;

    abstract boolean authenticate(String userName,String password);
    abstract boolean addToCategory(Object product);
    abstract boolean removeFromCategory(Object product);
}
