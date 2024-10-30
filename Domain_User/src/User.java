abstract class User extends Visitor{
    String userName;
    String password;
    String email;
    String phone;
    abstract boolean authenticate();
}
