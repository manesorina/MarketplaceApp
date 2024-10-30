public class Review {

    int reviewID;
    double grade;
    String message;
    User reviewer;
    User reviewee;

    public Review(int reviewID, double grade, String message, User reviewer, User reviewee) {
        this.reviewID = reviewID;
        this.grade = grade;
        this.message = message;
        this.reviewer = reviewer;
        this.reviewee = reviewee;
    }

}
