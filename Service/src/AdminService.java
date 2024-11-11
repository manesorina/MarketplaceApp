import java.util.List;

public class AdminService extends VisitorService {
    private final IMRepository<Admin> adminRepo;
    private final IMRepository<Category> categoryRepo;


    public AdminService(IMRepository<User> userRepo, IMRepository<Product> productRepo, IMRepository<Review> reviewRepo, IMRepository<Admin> adminRepo, IMRepository<Category> categoryRepo) {
        super(userRepo, productRepo, reviewRepo);
        this.adminRepo = adminRepo;
        this.categoryRepo=categoryRepo;

    }

    protected boolean authenticate(String username, String password) {
        List<Admin> admins = adminRepo.findByCriteria(admin -> admin.getUserName().equals(username) && admin.getPassword().equals(password));
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

    public boolean deleteReview(String adminUsername, String adminPassword, String reviewerUsername, String revieweeUsername) {
        if (authenticate(adminUsername, adminPassword)) {
            List<Review> reviews = reviewRepo.findByCriteria(review -> review.getReviewer().getUserName().equals(reviewerUsername) && review.getReviewee().getUserName().equals(revieweeUsername));
            if (!reviews.isEmpty()) {
                reviewRepo.delete(reviews.getFirst().getId());
                return true;
            }
        }
        return false;
    }

    public boolean deleteProduct(String adminUsername, String adminPassword, String sellerUsername) {
        if (authenticate(adminUsername,adminPassword)) {
            
            List<Product> products=productRepo.findByCriteria(product -> product.getListedBy().getUserName().equals(sellerUsername));
            
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

    public List<Category> getAllCategories(){
        return categoryRepo.getAll();
    }





}

