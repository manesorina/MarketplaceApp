public class Admin extends User {


    public Admin(int accountID, String userName, String password, String email, String phone, double score) {
        super(accountID, userName, password, email, phone, score);
    }

    public boolean deleteUser(User user){};
    public boolean deleteReview(Review review){};

    @Override
    boolean authenticate(String userName, String password) {
        return true;

    }


}
