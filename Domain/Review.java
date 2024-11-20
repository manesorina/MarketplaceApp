package Domain;

public class Review implements Identifiable {

    private int id;
    private double grade;
    private String message;
    private int reviewer;
    private int reviewee;

    public Review(double grade, String message, int reviewer, int reviewee) {
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

    public int getReviewer() {
        return reviewer;
    }

    public void setReviewer(int reviewer) {
        this.reviewer = reviewer;
    }

    public int getReviewee() {
        return reviewee;
    }

    public void setReviewee(int reviewee) {
        this.reviewee = reviewee;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", grade=" + grade +
                ", message='" + message + '\'' +
                ", reviewer=" + reviewer +
                ", reviewee=" + reviewee +
                '}';
    }
}
