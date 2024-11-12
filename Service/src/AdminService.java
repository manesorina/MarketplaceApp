import java.util.List;

public class AdminService extends VisitorService {
    private final IMRepository<Admin> adminRepo;
    private final IMRepository<Category> categoryRepo;



    /**
     * Constructor for the AdminService class. Initializes the service with the provided repositories for
     * users, products, reviews, admins, and categories.
     *
     * @param userRepo the repository to handle user-related operations
     * @param productRepo the repository to handle product-related operations
     * @param reviewRepo the repository to handle review-related operations
     * @param adminRepo the repository to handle admin-related operations
     * @param categoryRepo the repository to handle category-related operations
     */
    public AdminService(IMRepository<User> userRepo, IMRepository<Product> productRepo, IMRepository<Review> reviewRepo, IMRepository<Admin> adminRepo, IMRepository<Category> categoryRepo) {
        super(userRepo, productRepo, reviewRepo);
        this.adminRepo = adminRepo;
        this.categoryRepo=categoryRepo;

    }

    /**
     * Authenticates an admin based on their username and password.
     *
     * @param username the username of the admin.
     * @param password the password of the admin.
     * @return {@code true} if an admin with the specified username and password is found; {@code false} otherwise.
     */
    protected boolean authenticate(String username, String password) {
        List<Admin> admins = adminRepo.findByCriteria(admin -> admin.getUserName().equals(username) && admin.getPassword().equals(password));
        Admin admin = admins.getFirst();
        return admin != null;
    }

    /**
     * Deletes a user from the repository if the admin is authenticated.
     *
     * @param adminUsername the username of the admin performing the action.
     * @param adminPassword the password of the admin performing the action.
     * @param userId the ID of the user to be deleted.
     * @return {@code true} if the user is successfully deleted; {@code false} otherwise.
     */
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


    /**
     * Deletes a review from the repository if the admin is authenticated.
     *
     * @param adminUsername the username of the admin performing the action.
     * @param adminPassword the password of the admin performing the action.
     * @param reviewId the ID of the review to be deleted.
     * @return {@code true} if the review is successfully deleted; {@code false} otherwise.
     */
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

    /**
     * Deletes a product from the repository if the admin is authenticated.
     *
     * @param adminUsername the username of the admin performing the action.
     * @param adminPassword the password of the admin performing the action.
     * @param productId the ID of the product to be deleted.
     * @return {@code true} if the product is successfully deleted; {@code false} otherwise.
     */
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


    /**
     * Updates the category of a product if the admin is authenticated.
     *
     * @param adminUsername the username of the admin performing the action.
     * @param adminPassword the password of the admin performing the action.
     * @param productId the ID of the product to be updated.
     * @param newCategory the new category to set for the product.
     * @return {@code true} if the category is successfully updated; {@code false} otherwise.
     */
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


    /**
     * Retrieves all categories from the repository.
     *
     * @return a list of all categories.
     */
    public List<Category> getAllCategories(){
        return categoryRepo.getAll();
    }

    /**
     * Retrieves a product listed by a specific seller if the admin is authenticated.
     *
     * @param adminUsername the username of the admin performing the action.
     * @param adminPassword the password of the admin performing the action.
     * @param userId the ID of the seller whose product is to be retrieved.
     * @return the product listed by the seller, or {@code null} if not found.
     */
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

