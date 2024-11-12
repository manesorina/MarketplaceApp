import java.util.List;
import java.util.NoSuchElementException;

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
        try{
            Admin admin = admins.getFirst();
        }catch (NoSuchElementException e){
            return false;
        }

        return true;
    }

    //modifica
    public boolean deleteUser(String adminUsername, String adminPassword, int userId) {
        if (authenticate(adminUsername, adminPassword)) {
            List<User> users=userRepo.getAll();
            for(User user:users){
                if (user.getId()==userId){
                    userRepo.delete(userId);
                    return true;
                }
            }
        }
        return false;
    }


    //modifica
    public boolean deleteReview(String adminUsername, String adminPassword, int reviewId) {
        if (authenticate(adminUsername, adminPassword)) {
            List<Review> reviews = reviewRepo.getAll();
            for (Review review:reviews) {
                if(review.getId()==reviewId){
                    reviewRepo.delete(reviewId);
                }
            }
        }
        return false;
    }


    public boolean deleteProduct(String adminUsername, String adminPassword, int productId) {
        if (authenticate(adminUsername,adminPassword)) {
            List<Product> products=productRepo.getAll();
            for(Product product:products){
                if(product.getId()==productId){
                    productRepo.delete(productId);
                }
            }
        }
        return false;
    }



    public boolean updateCategory(String adminUsername,String adminPassword, int productId, Category newCategory){
        if (authenticate(adminUsername,adminPassword)) {
            List<Product> products=productRepo.getAll();
            Product targetetdProduct=productRepo.read(productId);
            for(Product product:products){
                if(product.equals(targetetdProduct)){
                    targetetdProduct.setCategory(newCategory);
                    productRepo.update(targetetdProduct);
                }
            }


        }
        return false;
    }

    public List<Category> getAllCategories(){
        return categoryRepo.getAll();
    }


    public Product getProductBySellerId(String adminUsername, String adminPassword,int userId){
        if(authenticate(adminUsername,adminPassword)){
            List<Product> products = productRepo.getAll();
            for(Product product:products){
                if(product.getListedBy().equals(userRepo.read(userId))){
                    return product;
                }
            }
        }


        return null;

    }





}

