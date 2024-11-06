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

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getReviewer() {
        return reviewer;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
    }

    public User getReviewee() {
        return reviewee;
    }

    public void setReviewee(User reviewee) {
        this.reviewee = reviewee;
    }
}
