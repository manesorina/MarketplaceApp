public class Review {

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

}
