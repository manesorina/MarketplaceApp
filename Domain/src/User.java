import java.util.List;

public class User extends Account implements Identifiable {
    private int id;
    private double score;

    public User(String userName, String password, String email, String phone, double score){
        this.userName=userName;
        this.password=password;
        this.email=email;
        this.phone=phone;
        this.score=0.0;
    }

    @Override
    public int getId() {
        return id;
    }
    public void setId(int id) {this.id = id;}

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
