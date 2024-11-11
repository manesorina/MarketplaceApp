import java.util.ArrayList;
import java.util.List;

public class Controller {
    protected final AdminService adminService;
    protected final UserService userService;
    protected final VisitorService visitorService;

    public Controller(AdminService adminService, UserService userService, VisitorService visitorService) {
        this.adminService = adminService;
        this.userService = userService;
        this.visitorService = visitorService;
    }

    public boolean createAccount(String username, String password, String email, String phoneNumber) {
        if (username != null && password != null && email != null && phoneNumber != null && phoneNumber.length() == 10) {
            visitorService.createAccount(username, password, email, phoneNumber);
            return true;
        }
        else return false;
    }

    public int logIn(String username, String password) {
        if (username != null && password != null) {
            boolean isAdmin = adminService.authenticate(username, password);
            boolean isUser = userService.authenticate(username, password);
            if (isUser)
                return 1;
            if (isAdmin)
                return 2;
        }
        return 0;
    }

    public List<Product> sortProducts(int choice, int order) {
        List<Product> products = visitorService.seeAllProducts();
        return switch (choice) {
            case 1 -> order == 1 ? visitorService.sortProductsByPriceAscending(products) : visitorService.sortProductsByPriceDescending(products);
            case 2 -> order == 1 ? visitorService.sortProductsByLikesAscending(products) : visitorService.sortProductsByLikesDescending(products);
            case 3 -> order == 1 ? visitorService.sortProductsBySizeAscending(products) : visitorService.sortProductsBySizeDescending(products);
            case 4 -> order == 1 ? visitorService.sortProductsByViewsAscending(products) : visitorService.sortProductsByViewsDescending(products);
            default -> {
                yield products;
            }
        };
    }

    public List<Product> filterProducts(int choice) {
        List<Product> products = visitorService.seeAllProducts();
        return switch (choice) {
            case 1 -> visitorService.searchProductsByCategory("someCategory");
            case 2 -> visitorService.searchProductsByBrand("someBrand");
            case 3 -> visitorService.searchProductsByColor("someColor");
            case 4 -> visitorService.searchProductsByUsername("someUsername");
            case 5 -> visitorService.searchProductsByViewRange(100, 500);
            case 6 -> visitorService.searchProductsByCondition("New");
            case 7 -> visitorService.searchProductsByPriceRange(10.0, 100.0);
            case 8 -> visitorService.searchProductsBySizeRange(10.0, 20.0);
            default -> {
                yield products;
            }
        };
    }

    public List<Offer> getOffers(String username, String password) {
        return userService.displayOffers(username, password);

    }

    public boolean declineOffer(String username,String password,Offer offer){
        return userService.declineOffer(username,password,offer);
    }

    public boolean acceptOffer(String username,String password,Offer offer){
        return userService.acceptOffer(username,password,offer);

    }

    public List<Order> getOrders(String username, String password){
        return userService.displayOrders(username,password);
    }

    public boolean likeProduct(String username, String password, Product product){
        return userService.addToFavorites(username,password,product);
    }

    public boolean sendOffer(User seller, String buyerUsername, String buyerPassword, Offer offer){
        return userService.sendOffer(seller, buyerUsername, buyerPassword, offer);
    }

    public boolean makeOrder(User seller, Order order){
        return userService.placeOrder(seller, order);
    }

    public List<Product> getUserListings(String username,String password){
        return userService.displayListedProducts(username,password);
    }

    public boolean addToUserListings(String userName,String password, Product product){
        return userService.listProduct(userName, password, product);
    }

    public boolean removeFromUserListings(String userName,String password, Product product){
        return userService.deleteListedProduct(product, userName, password);
    }

    public List<User> sortUsers(int choice, int order) {
        List<User> users = visitorService.seeAllUsers();
        return switch (choice) {
            case 1 -> order == 1 ? visitorService.sortUsersAscendingByReviewCount(users) : visitorService.sortUsersDescendingByReviewCount(users);
            case 2 -> order == 1 ? visitorService.sortUsersAlphabeticallyAscending(users) : visitorService.sortUsersAlphabeticallyDescending(users);
            case 3 -> order == 1 ? visitorService.sortUsersAscendingByScore(users) : visitorService.sortUsersDescendingByScore(users);
            case 4 -> users;
            default -> {yield users;
            }
        };
    }

    public List<User> filterUsers(int choice){
        List<User> users=visitorService.seeAllUsers();
        return switch(choice){
            case 1 -> visitorService.searchUsersByMinimumReviewCount(2);
            case 2 -> visitorService.searchUsersByUsername("someUsername");
            case 3 -> visitorService.searchUsersByMinimumScore(2.00);
            default -> {yield users;}
        };


    }

    public boolean writeReview(Review review){
        return userService.writeReview(review);
    }

    public List<Product> getUserListing(String username){
        return visitorService.displayUserListings(username);
    }

    public List<Review> getReviewsLeftForUser(String username){
        return visitorService.displayReviewsLeftForUser(username);
    }

    public boolean deleteReview(String username,String password){
        return userService.deleteReview(username, password);
    }

    public List<Review> getReviewsLeftByUser(String username){
        return visitorService.displayReviewsLeftByUser(username);
    }

    public boolean deleteReview(String adminUsername, String AdminPassword,String reviewerUsername, String revieweeUsername){
        return adminService.deleteReview(adminUsername, AdminPassword, reviewerUsername, revieweeUsername);
    }

    public boolean deleteUser (String adminUsername, String adminPassword, String nameOfUser){
        return adminService.deleteUser(adminUsername, adminPassword, nameOfUser);
    }

    public boolean deleteProduct(String adminUsername, String adminPassword, String sellerUsername){
        return adminService.deleteProduct(adminUsername, adminPassword, sellerUsername);
    }



    public List<Category> getCategories(){
        return adminService.getAllCategories();
    }

    public boolean changeCategory(Product product, int categoryChoice, String adminUsername, String adminPassword) {
        Category newCategory;

        switch (categoryChoice) {
            case 1 -> newCategory = new Category(CategoryName.TOPS);
            case 2 -> newCategory = new Category(CategoryName.BOTTOMS);
            case 3 -> newCategory = new Category(CategoryName.DRESSES);
            case 4 -> newCategory = new Category(CategoryName.OUTERWEAR);
            case 5 -> newCategory = new Category(CategoryName.FOOTWEAR);
            case 6 -> newCategory = new Category(CategoryName.ACCESSORIES);
            case 7 -> newCategory = new Category(CategoryName.SWIMWEAR);
            default -> {
                return false;
            }
        }

        User seller = product.getListedBy();
       return adminService.updateCategory(adminUsername, adminPassword, seller, newCategory);


    }

    public Product getProduct(String sellerUsername){
        adminService.getProductBySellerUsername(sellerUsername);
    }










}










