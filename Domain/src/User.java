import java.util.ArrayList;
import java.util.List;

public class User extends Account implements Identifiable {
    private int id;
    private double score;
    protected List<Product> favourites;

    public User(String userName, String password, String email, String phone, double score){
        this.userName=userName;
        this.password=password;
        this.email=email;
        this.phone=phone;
        this.score=score;
        this.favourites=new ArrayList<>();
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

    public List<Product> getFavourites() {
        return favourites;
    }
}
