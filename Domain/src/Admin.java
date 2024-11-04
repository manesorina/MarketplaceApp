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

    public boolean deleteUser(User user){
        return false;
    };

    public boolean deleteReview(Review review){
        return false;
    };

    @Override
    boolean authenticate(String userName, String password) {
        return true;

    }

    @Override
    boolean addToCategory(Product product) {
        return false;
    }

    @Override
    boolean removeFromCategory(Product product) {
        return false;
    }

}
