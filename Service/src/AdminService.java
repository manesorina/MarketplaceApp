import java.util.List;

public class AdminService extends VisitorService {
    private final IMRepository<Admin> adminRepo;


    public AdminService(IMRepository<User> userRepo, IMRepository<Product> productRepo, IMRepository<Review> reviewRepo, IMRepository<Admin> adminRepo) {
        super(userRepo, productRepo, reviewRepo);
        this.adminRepo = adminRepo;

    }

    protected boolean authenticate(String userName, String password) {
        List<Admin> admins = adminRepo.findByCriteria(admin -> admin.getUserName().equals(userName) && admin.getPassword().equals(password));
        Admin admin = admins.getFirst();
        return admin != null;
    }

    public boolean deleteUser(int adminId, String adminUsername, String adminPassword, int userId) {
        if (authenticate(adminId, adminUsername, adminPassword)) {
            User user = userRepo.read(userId);
            if (user != null) {
                userRepo.delete(userId);
                return true;
            }
        }
        return false;
    }

    public boolean deleteReview(int adminId, String adminUsername, String adminPassword, int reviewId) {
        if (authenticate(adminId, adminUsername, adminPassword)) {
            Review review = reviewRepo.read(reviewId);
            if (review != null) {
                reviewRepo.delete(reviewId);
                return true;
            }
        }
        return false;
    }

    public boolean deleteProduct(int adminId, int productId, User seller) {
        Admin admin = adminRepo.read(adminId);
        if (admin != null && authenticate(adminId, admin.getUserName(), admin.getPassword())) {
            Product product = productRepo.read(productId);
            if (product != null && product.getListedBy().equals(seller)) {
                productRepo.delete(product.getId());
                return true;
            }
        }
        return false;
    }

    public boolean updateCategory(int adminId, int productId, Category newCategory){
        Admin admin = adminRepo.read(adminId);
        if (admin != null && authenticate(adminId, admin.getUserName(), admin.getPassword())) {
            Product product = productRepo.read(productId);
            product.setCategory(newCategory);
            productRepo.update(product);
            return true;

        }
        return false;
    }







}

