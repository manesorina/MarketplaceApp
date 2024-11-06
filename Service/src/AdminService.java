public class AdminService {
    private final IMRepository<User> userRepo;
    private final IMRepository<Review> reviewRepo;
    private final IMRepository<Admin> adminRepo;

    public AdminService(IMRepository<User> userRepo, IMRepository<Review> reviewRepo, IMRepository<Admin> adminRepo) {
        this.userRepo = userRepo;
        this.reviewRepo = reviewRepo;
        this.adminRepo=adminRepo;
    }

    public boolean deleteUser(int adminId, String adminUsername, String adminPassword, int userId) {
        Admin admin = adminRepo.read(adminId);
        if (admin != null && admin.authenticate(adminUsername, adminPassword)) {
            User user = userRepo.read(userId);
            if (user != null) {
                userRepo.delete(userId);
                return true;
            }
        }
        return false;
    }

    public boolean deleteReview(int adminId, String adminUsername, String adminPassword, int reviewId) {

        Admin admin = adminRepo.read(adminId);
        if (admin != null && admin.authenticate(adminUsername, adminPassword)) {
            Review review = reviewRepo.read(reviewId);
            if (review != null) {
                reviewRepo.delete(reviewId);
                return true;
            }
        }
        return false;
    }
}

