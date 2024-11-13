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


    //Offers
    public List<Offer> getMadeOffers(String username, String password) {
        return userService.displayMadeOffers(username,password);

    }

    public List<Offer> displayReceivedOffers(String username, String password){
        return userService.displayReceivedOffers(username,password);
    }

    public List<Offer> getOffers(String username, String password){
        return userService.displayAllUserOffers(username, password);
    }

    public boolean sendOffer(String senderUsername,String senderPassword, String message, Product selectedProduct, double offeredPrice){
        return userService.sendOffer(senderUsername, senderPassword, message, selectedProduct, offeredPrice);
    }



    //Orders
    public boolean declineOffer(String username,String password,int offerId){
        return userService.declineOffer(username, password, offerId);
    }

    public boolean acceptOffer(String username,String password,int offerId){
        return userService.acceptOffer(username,password,offerId);

    }

    public List<Order> getMadeOrders(String username, String password){
        return userService.displayMadeOrders(username,password);
    }

    public List<Order> getReceivedOrders(String username, String password){
        return userService.displayReceivedOrders(username, password);
    }

    public List<Order> getOrders(String username, String password){
        return userService.displayReceivedOrders(username,password);
    }

    public List<Product> selectProductsForOrder(List<Integer> productIds){
        return userService.selectProductsForOrder(productIds);
    }

    public boolean makeOrder(String buyerUsername, String buyerPassword, List<Integer> selectedProductsIds, String status, String shippingAddress, int sellerId){
        return userService.placeOrder(buyerUsername, buyerPassword, selectedProductsIds,  status, shippingAddress,  sellerId);
    }



    //liked products

    public boolean likeProduct(String username, String password, int productId){
        return userService.addToFavorites(username,password,productId);
    }

    public boolean removeFromLiked(String username, String password, int productId){
        return userService.removeFromFavourites(username, password, productId);
    }

    public List<Product> displayLikedProducts(String username,String password){
        return userService.displayFavourites(username, password);
    }




    //Listings




    public boolean addToUserListings(String userName,String password, Category category,String name,String color, int size, double price, String brand, String condition, int nrOfViews, int nrOfLikes){
        return userService.listProduct(userName, password, category, name, color, size, price, brand, condition, nrOfViews, nrOfLikes);
    }

    public boolean removeFromUserListings(String userName,String password, int productId){
        return userService.deleteListedProduct(userName, password, productId);
    }

    public List<Product> getUserListing(int userId){
        return visitorService.displayUserListings(userId);
    }



    //Sort and filter Users

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


    //Review


    public boolean writeReview(String reviewerUsername, String reviewerPassword, double grade, String message, int revieweeId){
        if (grade >=1 && grade <= 5)
            return userService.writeReview(reviewerUsername, reviewerPassword, grade, message, revieweeId);
        else return false;
    }

    public boolean deleteReview(String username,String password, int reviewId){
        return userService.deleteReview(username, password, reviewId);
    }

    public List<Review> displayReviewsLeftForUser(int userId){
        return visitorService.displayReviewsLeftForUser(userId);
    }

    public List<Review> displayReviewsLeftByUser(String username, String password){
        return userService.displayMadePersonalReviews(username,password);
    }

    public List<Review> displayReviewsForMe(String username, String password){
        return userService.displayProfileReviews(username, password);
    }

    public double getProfileScore(String username, String password){
        return userService.getMyScore(username, password);
    }



    //admin


    public boolean deleteReviewAdmin(String adminUsername, String AdminPassword,int reviewId){
        return adminService.deleteReview(adminUsername, AdminPassword, reviewId);
    }

    public boolean deleteUser (String adminUsername, String adminPassword, int userId){
        return adminService.deleteUser(adminUsername, adminPassword, userId);
    }

    public boolean deleteProduct(String adminUsername, String adminPassword, int productId){
        return adminService.deleteProduct(adminUsername, adminPassword, productId);
    }



    public List<Category> getCategories(){
        return adminService.getAllCategories();
    }

    public boolean changeCategory(int productId, int categoryChoice, String adminUsername, String adminPassword) {
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

        return adminService.updateCategory(adminUsername, adminPassword, productId, newCategory);


    }

    public Product getProduct(String adminUsername, String adminPassword,int userId){
        return adminService.getProductBySellerId(adminUsername, adminPassword, userId);
    }


    public List<Product> getMyListings(String username, String password) {
        return userService.getMyListedProducts(username, password);
    }
}










