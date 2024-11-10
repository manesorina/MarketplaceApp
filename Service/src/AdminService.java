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

    public boolean deleteUser(String adminUsername, String adminPassword, String nameOfUser) {
        if (authenticate(adminUsername, adminPassword)) {
            List<User> users = userRepo.findByCriteria(user -> user.getUserName().equals(nameOfUser));
            if (!users.isEmpty()) {
                userRepo.delete(users.getFirst().getId());
                return true;
            }
        }
        return false;
    }

    public boolean deleteReview(int adminId, String adminUsername, String adminPassword, User reviewerName, User revieweeName) {
        if (authenticate(adminUsername, adminPassword)) {
            List<Review> reviews = reviewRepo.findByCriteria(review -> review.getReviewer().equals(reviewerName) && review.getReviewee().equals(revieweeName));
            if (!reviews.isEmpty()) {
                reviewRepo.delete(reviews.getFirst().getId());
                return true;
            }
        }
        return false;
    }

    public boolean deleteProduct(String adminUsername, String adminPassword, int productId, User seller) {
        if (authenticate(adminUsername,adminPassword)) {
            
            List<Product> products=productRepo.findByCriteria(product -> product.getListedBy().equals(seller));
            
            if (!products.isEmpty()) {
                productRepo.delete(products.getFirst().getId());
                return true;
            }
        }
        return false;
    }

    public boolean updateCategory(String adminUsername,String adminPassword, User seller, Category newCategory){
        if (authenticate(adminUsername,adminPassword)) {
            List<Product> products=productRepo.findByCriteria(product -> product.getListedBy().equals(seller));
            if(!products.isEmpty()){
                products.getFirst().setCategory(newCategory);
                productRepo.update(products.getFirst());
                return true;
            }

        }
        return false;
    }







}

