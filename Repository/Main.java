//import Domain.*;
//import Repository.*;
//import Service.*;
//import Controller.*;
//import java.util.List;
//
//public class Main {
//    public static void main(String[] args) {
//        // File paths
//        String userFilename = "Repository/ObjectFiles/users.txt";
//        String productFilename = "Repository/ObjectFiles/products.txt";
//        String categoriesFilename = "Repository/ObjectFiles/categories.txt";
//        String offersFilename = "Repository/ObjectFiles/offers.txt";
//        String orderFilename = "Repository/ObjectFiles/orders.txt";
//        String reviewsFilename = "Repository/ObjectFiles/reviews.txt";
//        String adminsFilename = "Repository/ObjectFiles/admins.txt";
//        String visitorsFilename = "Repository/ObjectFiles/visitors.txt";
//        String likedProducts = "Repository/ObjectFiles/likedProducts.txt";
//        String listedProducts = "Repository/ObjectFiles/listedProducts.txt";
//        String orderedProducts = "Repository/ObjectFiles/orderedProducts.txt";
//
//        // Initialize repositories
//        UserFileRepository userRepo = new UserFileRepository(userFilename, listedProducts, likedProducts);
//        ProductFileRepository productRepo = new ProductFileRepository(productFilename);
//        CategoryFileRepository categoryRepo = new CategoryFileRepository(categoriesFilename);
//        OfferFileRepository offerRepo = new OfferFileRepository(offersFilename);
//        OrderFileRepository orderRepo = new OrderFileRepository(orderFilename, orderedProducts);
//        ReviewFileRepository reviewRepo = new ReviewFileRepository(reviewsFilename);
//        AdminFileRepository adminRepo = new AdminFileRepository(adminsFilename);
//
//        // Load data from files
//        userRepo.loadDataFromFile();
//        productRepo.loadDataFromFile();
//        categoryRepo.loadDataFromFile();
//        offerRepo.loadDataFromFile();
//        orderRepo.loadDataFromFile();
//        reviewRepo.loadDataFromFile();
//        adminRepo.loadDataFromFile();
//
//        // Print loaded data to verify
//        System.out.println("Users:");
//        printEntities(userRepo.getAll());
//        System.out.println("\nProducts:");
//        printEntities(productRepo.getAll());
//        System.out.println("\nCategories:");
//        printEntities(categoryRepo.getAll());
//        System.out.println("\nOffers:");
//        printEntities(offerRepo.getAll());
//        System.out.println("\nOrders:");
//        printEntities(orderRepo.getAll());
//        System.out.println("\nReviews:");
//        printEntities(reviewRepo.getAll());
//        System.out.println("\nAdmins:");
//        printEntities(adminRepo.getAll());
//
//        // Initialize services
//        VisitorService visitorService = new VisitorService(userRepo, productRepo, reviewRepo, categoryRepo);
//        UserService userService = new UserService(userRepo, productRepo, reviewRepo, categoryRepo, orderRepo, offerRepo);
//        AdminService adminService = new AdminService(userRepo, productRepo, reviewRepo, adminRepo, categoryRepo, orderRepo);
//
//        // Initialize controller
//        Controller controller = new Controller(adminService, userService, visitorService);
//
//        // Test service functionality
//        System.out.println("\nTest: Get all products for visitors");
//        List<Product> allProducts = visitorService.browseProducts();
//        printEntities(allProducts);
//
//        System.out.println("\nTest: Create an order (User Service)");
//        List<Integer> productIds = List.of(1, 2);
//        Order newOrder = userService.placeOrder(1, productIds, "123 Street, City", 2);
//        System.out.println("Created Order: " + newOrder);
//
//        System.out.println("\nTest: Admin deletes a category");
//        adminService.upd(1);
//        printEntities(categoryRepo.getAll());
//    }
//
//    private static <T> void printEntities(List<T> entities) {
//        for (T entity : entities) {
//            System.out.println(entity);
//        }
//    }
//}
