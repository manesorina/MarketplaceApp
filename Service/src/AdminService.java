import java.util.List;

public class AdminService extends VisitorService {
    private final IMRepository<Admin> adminRepo;


    public AdminService(IMRepository<User> userRepo, IMRepository<Product> productRepo, IMRepository<Review> reviewRepo, IMRepository<Admin> adminRepo) {
        super(userRepo, productRepo, reviewRepo);
        this.adminRepo = adminRepo;

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

    public boolean removeProduct(int adminId, int productId, User seller) {
        Admin admin = adminRepo.read(adminId);
        if (admin != null && admin.authenticate(admin.userName, admin.password)) {
            Product product = productRepo.read(productId);
            if (product != null && product.getListedBy().equals(seller)) {
                productRepo.delete(product.getId());
                return true;
            }
        }
        return false;
    }






}

