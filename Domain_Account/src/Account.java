abstract class Account {
    int AccountID;
    String userName;
    String password;
    String email;
    String phone;

    abstract boolean authenticate();
}
