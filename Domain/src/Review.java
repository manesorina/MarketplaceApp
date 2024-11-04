public class Review implements Identifiable {

    int id;
    double grade;
    String message;
    User reviewer;
    User reviewee;

    public Review(double grade, String message, User reviewer, User reviewee) {
        this.grade = grade;
        this.message = message;
        this.reviewer = reviewer;
        this.reviewee = reviewee;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
