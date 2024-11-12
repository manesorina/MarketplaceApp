import java.util.*;
import java.util.stream.Collectors;

public class VisitorService {
    protected final IMRepository<User> userRepo;
    protected final IMRepository<Product> productRepo;
    protected final IMRepository<Review> reviewRepo;

    /**
     * Service for managing visitor interactions, such as user, product, and review searches,
     * sorting, and account creation.
     */
    public VisitorService(IMRepository<User> userRepo, IMRepository<Product> productRepo, IMRepository<Review> reviewRepo) {
        this.userRepo = userRepo;
        this.productRepo = productRepo;
        this.reviewRepo = reviewRepo;

    }


    /**
     * Creates an account for a new user.
     *
     * @param username the username of the new user.
     * @param password the password of the new user.
     * @param email the email address of the new user.
     * @param phone the phone number of the new user.
     * @return {@code true} if the account was successfully created; {@code false} otherwise.
     */
    public boolean createAccount(String username, String password, String email, String phone) {
        User newUser = new User(username, password, email, phone, 0.0);
        userRepo.create(newUser);
        User confirmation = userRepo.read(newUser.getId());
        if (confirmation != null)
            return true;
        else return false;
    }


    /**
     * Retrieves a list of all users.
     *
     * @return a list of all users.
     */
    public List<User> seeAllUsers() {
        return userRepo.getAll().stream().map(u -> u).collect(Collectors.toList());
    }

    /**
     * Retrieves a list of all products.
     *
     * @return a list of all products.
     */
    public List<Product> seeAllProducts() {
        return productRepo.getAll().stream().map(u -> u).collect(Collectors.toList());
    }

    /**
     * Retrieves a list of all reviews.
     *
     * @return a list of all reviews.
     */

    public List<Review> seeAllReviews() {
        return reviewRepo.getAll().stream().map(u -> u).collect(Collectors.toList());
    }


    /**
     * Searches for users by username.
     *
     * @param username the username to search for.
     * @return a list of users whose usernames contain the specified substring.
     */

    public List<User> searchUsersByUsername(String username) {
        return userRepo.getAll().stream().filter(user -> user.getUserName().toLowerCase()
                        .contains(username.toLowerCase())).collect(Collectors.toList());
    }


    /**
     * Searches for users whose score meets or exceeds the specified minimum.
     *
     * @param minScore the minimum score threshold.
     * @return a list of users whose scores meet or exceed the minimum score.
     */
    public List<User> searchUsersByMinimumScore(double minScore) {
        return userRepo.getAll().stream()
                .filter(user -> user.getScore() >= minScore)
                .collect(Collectors.toList());
    }


    /**
     * Searches for users by the minimum number of reviews received.
     *
     * @param reviewMin the minimum number of reviews.
     * @return a list of users with at least the specified number of reviews.
     */
    public List<User> searchUsersByMinimumReviewCount(double reviewMin) {
        Map<User, Long> reviewCounts = reviewRepo.getAll().stream()
                .collect(Collectors.groupingBy(Review::getReviewee, Collectors.counting()));
        return reviewCounts.entrySet().stream()
                .filter(entry -> entry.getValue() >= reviewMin)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }




    /**
     * Searches for reviews left by a user by their name.
     *
     * @param name the name of the user.
     * @return a list of reviews left by the user with the specified name.
     */

    public List<Review> searchReviewsLeftByUser(String name) {
        return reviewRepo.getAll().stream().filter(review -> review.getReviewer().getUserName().contains(name))
                .collect(Collectors.toList());
    }


    /**
     * Searches for products by their name.
     *
     * @param name the product name to search for.
     * @return a list of products whose names contain the specified substring.
     */
    public List<Product> searchProductsByName(String name) {
        return productRepo.getAll().stream().filter(product -> product.getName()
                .toLowerCase().contains(name.toLowerCase())).peek(product -> product.setNrViews(product.getNrViews() + 1)).collect(Collectors.toList());
    }


    /**
     * Searches for products listed by a user by their username.
     *
     * @param username the username of the product lister.
     * @return a list of products listed by the specified user.
     */
    public List<Product> searchProductsByUsername(String username) {
        return productRepo.getAll().stream().filter(product -> product.getListedBy().getUserName()
                .toLowerCase().contains(username.toLowerCase())).peek(product -> product.setNrViews(product.getNrViews() + 1)).collect(Collectors.toList());
    }


    /**
     * Searches for products by brand.
     *
     * @param brand the brand name to search for.
     * @return a list of products from the specified brand.
     */
    public List<Product> searchProductsByBrand(String brand) {
        return productRepo.getAll().stream().filter(product -> product.getBrand().toLowerCase()
                .contains(brand.toLowerCase())).peek(product -> product.setNrViews(product.getNrViews() + 1)).collect(Collectors.toList());
    }


    /**
     * Searches for products by category.
     *
     * @param category the category name to search for.
     * @return a list of products within the specified category.
     */
    public List<Product> searchProductsByCategory(String category) {
        return productRepo.getAll().stream().filter(product -> product.getCategory().getName().
                name().toLowerCase().contains(category.toLowerCase())).peek(product -> product.setNrViews(product.getNrViews() + 1)).collect(Collectors.toList());
    }


    /**
     * Searches for products within a specified price range.
     *
     * @param minPrice the minimum price.
     * @param maxPrice the maximum price.
     * @return a list of products within the specified price range.
     */
    public List<Product> searchProductsByPriceRange(double minPrice, double maxPrice) {
        return productRepo.getAll().stream()
                .filter(product -> product.getPrice() >= minPrice && product.getPrice() <= maxPrice)
                .peek(product -> product.setNrViews(product.getNrViews() + 1))
                .collect(Collectors.toList());
    }


    /**
     * Searches for products by condition.
     *
     * @param condition the condition to search for (e.g., "new", "used").
     * @return a list of products matching the specified condition.
     */
    public List<Product> searchProductsByCondition(String condition) {
        return productRepo.getAll().stream().filter(product -> product.getCondition().toLowerCase()
                .contains(condition.toLowerCase())).peek(product -> product.setNrViews(product.getNrViews() + 1)).collect(Collectors.toList());
    }


    /**
     * Searches for products by color.
     *
     * @param color the color to search for.
     * @return a list of products matching the specified color.
     */
    public List<Product> searchProductsByColor(String color) {
        return productRepo.getAll().stream().filter(product -> product.getColor().toLowerCase()
                .contains(color.toLowerCase())).peek(product -> product.setNrViews(product.getNrViews() + 1)).collect(Collectors.toList());
    }


    /**
     * Searches for products within a specified size range.
     *
     * @param minSize the minimum size.
     * @param maxSize the maximum size.
     * @return a list of products within the specified size range.
     */
    public List<Product> searchProductsBySizeRange(double minSize, double maxSize) {
        return productRepo.getAll().stream()
                .filter(product -> product.getSize() >= minSize && product.getSize() <= maxSize).
                peek(product -> product.setNrViews(product.getNrViews() + 1)).collect(Collectors.toList());
    }


    /**
     * Searches for products within a specified range of view counts.
     *
     * @param minViews the minimum number of views.
     * @param maxViews the maximum number of views.
     * @return a list of products within the specified view range.
     */
    public List<Product> searchProductsByViewRange(double minViews, double maxViews) {
        return productRepo.getAll().stream()
                .filter(product -> product.getNrViews() >= minViews && product.getNrViews() <= maxViews).
                peek(product -> product.setNrViews(product.getNrViews() + 1)).collect(Collectors.toList());
    }


    /**
     * Sorts users alphabetically in ascending order.
     *
     * @param users the list of users to sort.
     * @return a sorted list of users.
     */
    public List<User> sortUsersAlphabeticallyAscending(List<User> users) {
        return users.stream().sorted(Comparator.comparing(User::getUserName)).collect(Collectors.toList());
    }


    /**
     * Sorts users alphabetically in descending order.
     *
     * @param users the list of users to sort.
     * @return a sorted list of users.
     */
    public List<User> sortUsersAlphabeticallyDescending(List<User> users) {
        return users.stream().sorted(Comparator.comparing(User::getUserName).
                reversed()).collect(Collectors.toList());
    }


    /**
     * Sorts users by their scores in ascending order.
     *
     * @param users the list of users to sort.
     * @return a sorted list of users.
     */
    public List<User> sortUsersAscendingByScore(List<User> users) {
        return users.stream().sorted(Comparator.comparing(User::getScore).reversed())
                .collect(Collectors.toList());
    }


    /**
     * Sorts users by their scores in descending order.
     *
     * @param users the list of users to sort.
     * @return a sorted list of users.
     */
    public List<User> sortUsersDescendingByScore(List<User> users) {
        return users.stream().sorted(Comparator.comparing(User::getScore).reversed())
                .collect(Collectors.toList());
    }


    /**
     * Sorts users by their review count in ascending order.
     *
     * @param users the list of users to sort.
     * @return a sorted list of users.
     */
    public List<User> sortUsersAscendingByReviewCount(List<User> users) {
        Map<User, Integer> reviewCounts = reviewRepo.getAll().stream()
                .collect(Collectors.groupingBy(Review::getReviewee, Collectors.summingInt(review -> 1)));
        return users.stream()
                .sorted(Comparator.comparingInt(user -> reviewCounts.getOrDefault(user, 0))) // Default to 0 if no reviews
                .collect(Collectors.toList());
    }


    /**
     * Sorts users by their review count in descending order.
     *
     * @param users the list of users to sort.
     * @return a sorted list of users.
     */
    public List<User> sortUsersDescendingByReviewCount(List<User> users) {
        Map<User, Integer> reviewCounts = reviewRepo.getAll().stream()
                .collect(Collectors.groupingBy(Review::getReviewee, Collectors.summingInt(review -> 1)));
        return users.stream()
                .sorted(Comparator.comparingInt(user -> reviewCounts.getOrDefault(user, 0)).reversed()) // Default to 0 if no reviews
                .collect(Collectors.toList());
    }


    /**
     * Sorts products by name alphabetically in ascending order.
     *
     * @param products the list of products to sort.
     * @return a sorted list of products.
     */
    public List<Product> sortProductsByNameAlphabeticallyAscending(List<Product> products) {
        return products.stream().sorted(Comparator.comparing(Product::getName)).collect(Collectors.toList());
    }


    /**
     * Sorts products by category alphabetically in ascending order.
     *
     * @param products the list of products to sort.
     * @return a sorted list of products.
     */
    public List<Product> sortProductsByNameAlphabeticallyDescending(List<Product> products) {
        return products.stream().sorted(Comparator.comparing(Product::getName).
                reversed()).collect(Collectors.toList());
    }


    /**
     * Sorts products by category alphabetically in descending order.
     *
     * @param products the list of products to sort.
     * @return a sorted list of products.
     */
    public List<Product> sortProductsByCategoryAlphabeticallyAscending(List<Product> products) {
        return products.stream()
                .sorted(Comparator.comparing(product -> product.getCategory().getName().name()))
                .collect(Collectors.toList());
    }


    /**
     * Sorts products by brand in ascending order.
     *
     * @param products the list of products to sort.
     * @return a sorted list of products.
     */
    public List<Product> sortProductsByCategoryAlphabeticallyDescending(List<Product> products) {
        return products.stream().sorted(Comparator.comparing((Product product) -> product.getCategory()
                .getName().name()).reversed()).collect(Collectors.toList());
    }


    /**
     * Sorts products by brand in descending order.
     *
     * @param products the list of products to sort.
     * @return a sorted list of products.
     */
    public List<Product> sortProductsByBrandAscending(List<Product> products) {
        return products.stream().sorted(Comparator.comparing(Product::getBrand))
                .collect(Collectors.toList());
    }


    /**
     * Sorts products by brand in descending order.
     *
     * @param products the list of products to sort.
     * @return a sorted list of products.
     */
    public List<Product> sortProductsByBrandDescending(List<Product> products) {
        return products.stream().sorted(Comparator.comparing(Product::getBrand).reversed())
                .collect(Collectors.toList());
    }


    /**
     * Sorts products by size in ascending order.
     *
     * @param products the list of products to sort.
     * @return a sorted list of products.
     */
    public List<Product> sortProductsBySizeAscending(List<Product> products) {
        return products.stream().sorted(Comparator.comparingInt(Product::getSize))
                .collect(Collectors.toList());
    }


    /**
     * Sorts products by size in descending order.
     *
     * @param products the list of products to sort.
     * @return a sorted list of products.
     */
    public List<Product> sortProductsBySizeDescending(List<Product> products) {
        return products.stream().sorted(Comparator.comparingInt(Product::getSize).reversed())
                .collect(Collectors.toList());
    }


    /**
     * Sorts products by the username of the lister in ascending order.
     *
     * @param products the list of products to sort.
     * @return a sorted list of products.
     */
    public List<Product> sortProductsByUsernameAscending(List<Product> products) {
        return products.stream().sorted(Comparator.comparing(product -> product.getListedBy().getUserName()))
                .collect(Collectors.toList());
    }


    /**
     * Sorts products by the username of the lister in descending order.
     *
     * @param products the list of products to sort.
     * @return a sorted list of products.
     */
    public List<Product> sortProductsByUsernameDescending(List<Product> products) {
        return products.stream().sorted((p1, p2) -> p2.getListedBy().getUserName().compareTo(p1.getListedBy()
                .getUserName())).collect(Collectors.toList());
    }


    /**
     * Sorts a list of products by their color in ascending order.
     *
     * @param products the list of products to be sorted
     * @return a sorted list of products, ordered by color in ascending order
     */
    public List<Product> sortProductsByColorAscending(List<Product> products) {
        return products.stream().sorted(Comparator.comparing(Product::getColor))
                .collect(Collectors.toList());
    }


    /**
     * Sorts a list of products by their color in descending order.
     *
     * @param products the list of products to be sorted
     * @return a sorted list of products, ordered by color in descending order
     */
    public List<Product> sortProductsByColorDescending(List<Product> products) {
        return products.stream().sorted(Comparator.comparing(Product::getColor).reversed())
                .collect(Collectors.toList());
    }


    /**
     * Sorts a list of products by the number of views in ascending order.
     *
     * @param products the list of products to be sorted
     * @return a sorted list of products, ordered by number of views in ascending order
     */
    public List<Product> sortProductsByViewsAscending(List<Product> products) {
        return products.stream().sorted(Comparator.comparingInt(Product::getNrViews))
                .collect(Collectors.toList());
    }


    /**
     * Sorts a list of products by the number of views in descending order.
     *
     * @param products the list of products to be sorted
     * @return a sorted list of products, ordered by number of views in descending order
     */
    public List<Product> sortProductsByViewsDescending(List<Product> products) {
        return products.stream().sorted(Comparator.comparingInt(Product::getNrViews).reversed())
                .collect(Collectors.toList());
    }


    /**
     * Sorts a list of products by their condition in ascending order.
     *
     * @param products the list of products to be sorted
     * @return a sorted list of products, ordered by condition in ascending order
     */
    public List<Product> sortProductsByConditionAscending(List<Product> products) {
        return products.stream().sorted(Comparator.comparing(Product::getCondition))
                .collect(Collectors.toList());
    }


    /**
     * Sorts a list of products by their condition in descending order.
     *
     * @param products the list of products to be sorted
     * @return a sorted list of products, ordered by condition in descending order
     */
    public List<Product> sortProductsByConditionDescending(List<Product> products) {
        return products.stream().sorted(Comparator.comparing(Product::getCondition).reversed())
                .collect(Collectors.toList());
    }


    /**
     * Sorts a list of products by price in ascending order.
     *
     * @param products the list of products to be sorted
     * @return a sorted list of products, ordered by price in ascending order
     */
    public List<Product> sortProductsByPriceAscending(List<Product> products) {
        return products.stream().sorted(Comparator.comparingDouble(Product::getPrice))
                .collect(Collectors.toList());
    }


    /**
     * Sorts a list of products by price in descending order.
     *
     * @param products the list of products to be sorted
     * @return a sorted list of products, ordered by price in descending order
     */
    public List<Product> sortProductsByPriceDescending(List<Product> products) {
        return products.stream().sorted(Comparator.comparingDouble(Product::getPrice).reversed())
                .collect(Collectors.toList());
    }


    /**
     * Sorts a list of products by the number of likes in ascending order.
     *
     * @param products the list of products to be sorted
     * @return a sorted list of products, ordered by number of likes in ascending order
     */
    public List<Product> sortProductsByLikesAscending(List<Product> products) {
        return products.stream().sorted(Comparator.comparingInt(Product::getNrLikes))
                .collect(Collectors.toList());
    }


    /**
     * Sorts a list of products by the number of likes in descending order.
     *
     * @param products the list of products to be sorted
     * @return a sorted list of products, ordered by number of likes in descending order
     */
    public List<Product> sortProductsByLikesDescending(List<Product> products) {
        return products.stream().sorted(Comparator.comparingInt(Product::getNrLikes).reversed())
                .collect(Collectors.toList());
    }


    /**
     * Sorts the reviews left for a user by grade in ascending order.
     *
     * @param name the username of the reviewee whose reviews will be sorted
     * @return a sorted list of reviews, ordered by grade in ascending order
     */
    public List<Review> sortUserReviewsAscending(String name) {
        return reviewRepo.getAll().stream().filter(review -> review.getReviewee().getUserName().contains(name))
                .sorted(Comparator.comparing(Review::getGrade)).collect(Collectors.toList());
    }


    /**
     * Sorts the reviews left for a user by grade in descending order.
     *
     * @param name the username of the reviewee whose reviews will be sorted
     * @return a sorted list of reviews, ordered by grade in descending order
     */
    public List<Review> sortUserReviewsDescending(String name) {
        return reviewRepo.getAll().stream().filter(review -> review.getReviewee().getUserName().contains(name))
                .sorted(Comparator.comparing(Review::getGrade).reversed()).collect(Collectors.toList());
    }


    /**
     * Sorts the reviews left by a user by grade in ascending order.
     *
     * @param name the username of the reviewer whose reviews will be sorted
     * @return a sorted list of reviews, ordered by grade in ascending order
     */
    public List<Review> sortReviewsLeftByUserAscending(String name) {
        return reviewRepo.getAll().stream().filter(review -> review.getReviewer().getUserName().contains(name))
                .sorted(Comparator.comparing(Review::getGrade)).collect(Collectors.toList());
    }


    /**
     * Sorts the reviews left by a user by grade in descending order.
     *
     * @param name the username of the reviewer whose reviews will be sorted
     * @return a sorted list of reviews, ordered by grade in descending order
     */
    public List<Review> sortReviewsLeftByUserDescending(String name) {
        return reviewRepo.getAll().stream().filter(review -> review.getReviewer().getUserName().contains(name))
                .sorted(Comparator.comparing(Review::getGrade).reversed()).collect(Collectors.toList());
    }



    public List<Product> displayUserListings(int userId) {
        User user=userRepo.read(userId);
        if (user != null) {
            return user.getListedProducts();
        }
        return new ArrayList<>();

    }


    /**
     * Displays the products listed by a user based on their user ID.
     *
     * @param userId the ID of the user whose listed products will be displayed
     * @return a list of products listed by the user, or an empty list if the user does not exist
     */
    public List<Review> displayReviewsLeftForUser(int userId){
        User user=userRepo.read(userId);
        List<Review> receivedReviews=new ArrayList<>();
        if(user != null){
            List<Review> reviews=reviewRepo.getAll();
            for(Review review:reviews){
                if (review.getReviewee().equals(user)){
                    receivedReviews.add(review);
                }
            }
        }
        return receivedReviews;

    }






}
