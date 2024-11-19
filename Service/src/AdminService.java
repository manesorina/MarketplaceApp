import java.util.*;
import java.util.stream.Collectors;

public class AdminService extends VisitorService {
    private final IMRepository<Admin> adminRepo;
    private final IMRepository<Category> categoryRepo;
    private final IMRepository<Order> orderRepo;



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
    public AdminService(IMRepository<User> userRepo, IMRepository<Product> productRepo, IMRepository<Review> reviewRepo, IMRepository<Admin> adminRepo, IMRepository<Category> categoryRepo, IMRepository<Order> orderRepo) {
        super(userRepo, productRepo, reviewRepo, categoryRepo);
        this.adminRepo = adminRepo;
        this.categoryRepo=categoryRepo;
        this.orderRepo = orderRepo;
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
        try{
            Admin admin = admins.getFirst();
        }catch (NoSuchElementException e){
            return false;
        }

        return true;
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
     * Deletes a review from the repository if the admin is authenticated, and updates the nr of flagged actions for an user
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
                    User user = userRepo.read(reviewRepo.read(reviewId).getReviewer());
                    user.incrementFlaggedActions();
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
                    User user = userRepo.read(productRepo.read(productId).getListedBy());
                    user.incrementFlaggedActions();
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
    public boolean updateCategory(String adminUsername,String adminPassword, int productId, int newCategory){
        if (authenticate(adminUsername,adminPassword)) {
            List<Product> products=productRepo.getAll();
            Product targetetdProduct=productRepo.read(productId);
            for(Product product:products){
                if(product.equals(targetetdProduct)){
                    targetetdProduct.setCategory(newCategory);
                    int userId = productRepo.read(productId).getListedBy();
                    User user = userRepo.read(userId);
                    user.incrementFlaggedActions();
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

    public Map<String, Double> sortCategoriesByIncome() {
        List<Order> allOrders = orderRepo.getAll();
        if (allOrders.isEmpty()) {
            return Map.of();
        }
        Map<String, Double> incomeByCategory = new HashMap<>();
        for (Order order:allOrders) {
            for (int productId: order.getProducts()) {
                Product product = productRepo.read(productId);
                if (product != null) {
                    String categoryName = String.valueOf(categoryRepo.read(product.getCategory()).getName());
                    double productPrice = product.getPrice();
                    incomeByCategory.merge(categoryName, productPrice, Double::sum);
                }
            }
        }
        List<Map.Entry<String, Double>> sortedEntries = new ArrayList<>(incomeByCategory.entrySet());
        sortedEntries.sort(Map.Entry.<String, Double>comparingByValue().reversed());
        Map<String, Double> sortedIncomeByCategory = new LinkedHashMap<>();
        for (Map.Entry<String, Double> entry : sortedEntries) {
            sortedIncomeByCategory.put(entry.getKey(), entry.getValue());
        }
        return sortedIncomeByCategory;

    }





}

