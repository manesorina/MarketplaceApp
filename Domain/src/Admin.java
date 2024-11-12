public class Admin extends Account implements Identifiable {
    private int id;

    public Admin(String userName, String password, String email, String phone) {
        this.userName=userName;
        this.password=password;
        this.email=email;
        this.phone=phone;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {this.id = id;}

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                '}';
    }
}
