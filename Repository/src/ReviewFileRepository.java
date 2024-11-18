public class ReviewFileRepository extends FMRepository<Review>{

    public ReviewFileRepository(String filename){
        super(filename);
    }

    protected String convertObjectToString(Review review){
        return review.getId()+ "," +
                review.getGrade()+ "," +
                review.getMessage()+ "," +
                review.getReviewer()+ "," +
                review.getReviewee();
    }

    protected Review createObjectFromString(String line){
        String[] parts=line.split(",");
        int id=Integer.parseInt(parts[0]);
        double grade=Double.parseDouble(parts[1]);
        String message=parts[2];
        int reviewer=Integer.parseInt(parts[3]);
        int reviewee=Integer.parseInt(parts[4]);
        return new Review(grade,message,reviewer,reviewee);
    }
}
