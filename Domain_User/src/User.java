public class User extends Account{

    double score;

    public User(int accountID, String userName, String password, String email, String phone, double score){
        this.accountID=accountID;
        this.userName=userName;
        this.password=password;
        this.email=email;
        this.phone=phone;
        this.score=0.0;

    }


    @Override
    boolean authenticate(String userName, String password) {
        return false;
    }



}
