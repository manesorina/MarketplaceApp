import java.util.*;
import java.util.stream.Collectors;

public class VisitorService {
    protected final IMRepository<User> userRepo;
    protected final IMRepository<Product> productRepo;
    protected final IMRepository<Review> reviewRepo;


    public VisitorService(IMRepository<User> userRepo, IMRepository<Product> productRepo, IMRepository<Review> reviewRepo) {
        this.userRepo = userRepo;
        this.productRepo = productRepo;
        this.reviewRepo = reviewRepo;

    }

    public boolean createAccount(String username, String password, String email, String phone) {
        User newUser = new User(username, password, email, phone, 0.0);
        userRepo.create(newUser);
        User confirmation = userRepo.read(newUser.getId());
        if (confirmation != null)
            return true;
        else return false;
    }

    public List<User> seeAllUsers() {
        return userRepo.getAll().stream().map(u -> u).collect(Collectors.toList());
    }

    public List<Product> seeAllProducts() {
        return productRepo.getAll().stream().map(u -> u).collect(Collectors.toList());
    }

    public List<Review> seeAllReviews() {
        return reviewRepo.getAll().stream().map(u -> u).collect(Collectors.toList());
    }

    public List<User> searchUsersByUsername(String username) {
        return userRepo.getAll().stream().filter(user -> user.getUserName().toLowerCase()
                        .contains(username.toLowerCase())).collect(Collectors.toList());
    }

    public List<User> searchUsersByMinimumScore(double minScore) {
        return userRepo.getAll().stream()
                .filter(user -> user.getScore() >= minScore)
                .collect(Collectors.toList());
    }

    public List<User> searchUsersByMinimumReviewCount(double reviewMin) {
        Map<User, Long> reviewCounts = reviewRepo.getAll().stream()
                .collect(Collectors.groupingBy(Review::getReviewee, Collectors.counting()));
        return reviewCounts.entrySet().stream()
                .filter(entry -> entry.getValue() >= reviewMin)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public List<Review> searchUserReviews(String name) {
        return reviewRepo.getAll().stream().filter(review -> review.getReviewee().getUserName().contains(name))
                .collect(Collectors.toList());
    }

    public List<Review> searchReviewsLeftByUser(String name) {
        return reviewRepo.getAll().stream().filter(review -> review.getReviewer().getUserName().contains(name))
                .collect(Collectors.toList());
    }

    public List<Product> searchProductsByName(String name) {
        return productRepo.getAll().stream().filter(product -> product.getName()
                .toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }

    public List<Product> searchProductsByUsername(String username) {
        return productRepo.getAll().stream().filter(product -> product.getListedBy().getUserName()
                .toLowerCase().contains(username.toLowerCase())).collect(Collectors.toList());
    }

    public List<Product> searchProductsByBrand(String brand) {
        return productRepo.getAll().stream().filter(product -> product.getBrand().toLowerCase()
                .contains(brand.toLowerCase())).collect(Collectors.toList());
    }

    public List<Product> searchProductsByCategory(String category) {
        return productRepo.getAll().stream().filter(product -> product.getCategory().getName().
                name().toLowerCase().contains(category.toLowerCase())).collect(Collectors.toList());
    }

    public List<Product> searchProductsByPriceRange(double minPrice, double maxPrice) {
        return productRepo.getAll().stream()
                .filter(product -> product.getPrice() >= minPrice && product.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    public List<Product> searchProductsByCondition(String condition) {
        return productRepo.getAll().stream().filter(product -> product.getCondition().toLowerCase()
                .contains(condition.toLowerCase())).collect(Collectors.toList());
    }

    public List<Product> searchProductsByColor(String color) {
        return productRepo.getAll().stream().filter(product -> product.getColor().toLowerCase()
                .contains(color.toLowerCase())).collect(Collectors.toList());
    }

    public List<Product> searchProductsBySizeRange(double minSize, double maxSize) {
        return productRepo.getAll().stream()
                .filter(product -> product.getSize() >= minSize && product.getSize() <= maxSize)
                .collect(Collectors.toList());
    }

    public List<Product> searchProductsByViewRange(double minViews, double maxViews) {
        return productRepo.getAll().stream()
                .filter(product -> product.getNrViews() >= minViews && product.getNrViews() <= maxViews)
                .collect(Collectors.toList());
    }

    public List<User> sortUsersAlphabeticallyAscending(List<User> users) {
        return users.stream().sorted(Comparator.comparing(User::getUserName)).collect(Collectors.toList());
    }

    public List<User> sortUsersAlphabeticallyDescending(List<User> users) {
        return users.stream().sorted(Comparator.comparing(User::getUserName).
                reversed()).collect(Collectors.toList());
    }

    public List<User> sortUsersAscendingByScore(List<User> users) {
        return users.stream().sorted(Comparator.comparing(User::getScore).reversed())
                .collect(Collectors.toList());
    }

    public List<User> sortUsersDescendingByScore(List<User> users) {
        return users.stream().sorted(Comparator.comparing(User::getScore).reversed())
                .collect(Collectors.toList());
    }

    public List<User> sortUsersAscendingByReviewCount(List<User> users) {
        Map<User, Integer> reviewCounts = reviewRepo.getAll().stream()
                .collect(Collectors.groupingBy(Review::getReviewee, Collectors.summingInt(review -> 1)));
        return users.stream()
                .sorted(Comparator.comparingInt(user -> reviewCounts.getOrDefault(user, 0))) // Default to 0 if no reviews
                .collect(Collectors.toList());
    }

    public List<User> sortUsersDescendingByReviewCount(List<User> users) {
        Map<User, Integer> reviewCounts = reviewRepo.getAll().stream()
                .collect(Collectors.groupingBy(Review::getReviewee, Collectors.summingInt(review -> 1)));
        return users.stream()
                .sorted(Comparator.comparingInt(user -> reviewCounts.getOrDefault(user, 0)).reversed()) // Default to 0 if no reviews
                .collect(Collectors.toList());
    }

    public List<Product> sortProductsByNameAlphabeticallyAscending(List<Product> products) {
        return products.stream().sorted(Comparator.comparing(Product::getName)).collect(Collectors.toList());
    }

    public List<Product> sortProductsByNameAlphabeticallyDescending(List<Product> products) {
        return products.stream().sorted(Comparator.comparing(Product::getName).
                reversed()).collect(Collectors.toList());
    }

    public List<Product> sortProductsByCategoryAlphabeticallyAscending(List<Product> products) {
        return products.stream()
                .sorted(Comparator.comparing(product -> product.getCategory().getName().name()))
                .collect(Collectors.toList());
    }

    public List<Product> sortProductsByCategoryAlphabeticallyDescending(List<Product> products) {
        return products.stream().sorted(Comparator.comparing((Product product) -> product.getCategory()
                .getName().name()).reversed()).collect(Collectors.toList());
    }

    public List<Product> sortProductsByBrandAscending(List<Product> products) {
        return products.stream().sorted(Comparator.comparing(Product::getBrand))
                .collect(Collectors.toList());
    }

    public List<Product> sortProductsByBrandDescending(List<Product> products) {
        return products.stream().sorted(Comparator.comparing(Product::getBrand).reversed())
                .collect(Collectors.toList());
    }

    public List<Product> sortProductsBySizeAscending(List<Product> products) {
        return products.stream().sorted(Comparator.comparingInt(Product::getSize))
                .collect(Collectors.toList());
    }

    public List<Product> sortProductsBySizeDescending(List<Product> products) {
        return products.stream().sorted(Comparator.comparingInt(Product::getSize).reversed())
                .collect(Collectors.toList());
    }

    public List<Product> sortProductsByUsernameAscending(List<Product> products) {
        return products.stream().sorted(Comparator.comparing(product -> product.getListedBy().getUserName()))
                .collect(Collectors.toList());
    }

    public List<Product> sortProductsByUsernameDescending(List<Product> products) {
        return products.stream().sorted((p1, p2) -> p2.getListedBy().getUserName().compareTo(p1.getListedBy()
                .getUserName())).collect(Collectors.toList());
    }

    public List<Product> sortProductsByColorAscending(List<Product> products) {
        return products.stream().sorted(Comparator.comparing(Product::getColor))
                .collect(Collectors.toList());
    }

    public List<Product> sortProductsByColorDescending(List<Product> products) {
        return products.stream().sorted(Comparator.comparing(Product::getColor).reversed())
                .collect(Collectors.toList());
    }

    public List<Product> sortProductsByViewsAscending(List<Product> products) {
        return products.stream().sorted(Comparator.comparingInt(Product::getNrViews))
                .collect(Collectors.toList());
    }

    public List<Product> sortProductsByViewsDescending(List<Product> products) {
        return products.stream().sorted(Comparator.comparingInt(Product::getNrViews).reversed())
                .collect(Collectors.toList());
    }

    public List<Product> sortProductsByConditionAscending(List<Product> products) {
        return products.stream().sorted(Comparator.comparing(Product::getCondition))
                .collect(Collectors.toList());
    }

    public List<Product> sortProductsByConditionDescending(List<Product> products) {
        return products.stream().sorted(Comparator.comparing(Product::getCondition).reversed())
                .collect(Collectors.toList());
    }

    public List<Product> sortProductsByPriceAscending(List<Product> products) {
        return products.stream().sorted(Comparator.comparingDouble(Product::getPrice))
                .collect(Collectors.toList());
    }

    public List<Product> sortProductsByPriceDescending(List<Product> products) {
        return products.stream().sorted(Comparator.comparingDouble(Product::getPrice).reversed())
                .collect(Collectors.toList());
    }

    public List<Product> sortProductsByLikesAscending(List<Product> products) {
        return products.stream().sorted(Comparator.comparingInt(Product::getNrLikes))
                .collect(Collectors.toList());
    }

    public List<Product> sortProductsByLikesDescending(List<Product> products) {
        return products.stream().sorted(Comparator.comparingInt(Product::getNrLikes).reversed())
                .collect(Collectors.toList());
    }

    public List<Review> sortUserReviewsAscending(String name) {
        return reviewRepo.getAll().stream().filter(review -> review.getReviewee().getUserName().contains(name))
                .sorted(Comparator.comparing(Review::getGrade)).collect(Collectors.toList());
    }

    public List<Review> sortUserReviewsDescending(String name) {
        return reviewRepo.getAll().stream().filter(review -> review.getReviewee().getUserName().contains(name))
                .sorted(Comparator.comparing(Review::getGrade).reversed()).collect(Collectors.toList());
    }

    public List<Review> sortReviewsLeftByUserAscending(String name) {
        return reviewRepo.getAll().stream().filter(review -> review.getReviewer().getUserName().contains(name))
                .sorted(Comparator.comparing(Review::getGrade)).collect(Collectors.toList());
    }

    public List<Review> sortReviewsLeftByUserDescending(String name) {
        return reviewRepo.getAll().stream().filter(review -> review.getReviewer().getUserName().contains(name))
                .sorted(Comparator.comparing(Review::getGrade).reversed()).collect(Collectors.toList());
    }

    public List<Product> displayUserListings(String username) {
        List<User> users =userRepo.findByCriteria(user -> user.getUserName().equals(username));
        User user=users.getFirst();
        if (user != null) {
            return user.getListedProducts();
        }
        return new ArrayList<>();

    }




}
