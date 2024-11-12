import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleApp {
    private final Controller controller;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleApp(Controller controller) {
        this.controller = controller;
    }

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("Welcome to the Marketplace App!");
            System.out.println("1. Log in");
            System.out.println("2. Sign up");
            System.out.println("3. Browse products");
            System.out.println("4. Browse users");
            System.out.println("0. Exit");
            System.out.print("Please select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1-> logIn();
                case 2-> signUp();
                case 3-> browseProductsVisitor();
                case 4-> browseUsersVisitor();
                case 0 -> running = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    //VISITOR

    private void signUp() {
        System.out.println("Please enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Please enter your email address: ");
        String email = scanner.nextLine();
        System.out.println("Please enter your password: ");
        String password = scanner.nextLine();
        System.out.println("Please enter your phone number: ");
        String phoneNumber = scanner.nextLine();
        boolean success = controller.createAccount(username, password, email, phoneNumber);
        if (success){
            System.out.println("Account created successfully! Please log in to continue.");
        }
        else {
            System.out.println("Something went wrong. Please try again.");
        }
    }

    private void logIn() {
        System.out.println("Please enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Please enter your password: ");
        String password = scanner.nextLine();
        int result = controller.logIn(username, password);
        if (result == 1) {
            userMenu(username, password);
        }
        if (result == 2) {
            adminMenu(username, password);
        }
        else {
            System.out.println("Login failed. Please try again.");
        }

    }

    private void browseProductsVisitor() {
        boolean browsing = true;
        List<Product> products = new ArrayList<>();

        while (browsing) {
            System.out.println("Product Browsing Options: ");
            System.out.println("1. Sort Products");
            System.out.println("2. Filter Products");
            System.out.println("0. Go Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> sortProducts();
                case 2 -> filterProducts();
                case 0 -> browsing = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void browseUsersVisitor() {
        boolean browsing = true;
        List<User> displayedUsers = new ArrayList<>();
        while (browsing) {
            System.out.println("User Browsing Options: ");
            System.out.println("1. Sort Users");
            System.out.println("2. Filter Users");
            System.out.println("3. View User Reviews");
            System.out.println("0. Go Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> sortUsers();
                case 2 -> filterUsers();
                case 3 -> viewUserReviews(displayedUsers);
                case 0 -> browsing = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

//USER
    private void userMenu(String username, String password) {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("Welcome to your profile!");
            System.out.println("1. Browse Products");
            System.out.println("2. Browse Users");
            System.out.println("3. View My Listings");
            System.out.println("4. View My Orders");
            System.out.println("5. View Offers");
            System.out.println("0. Log Out");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> browseProductsUser(username, password);
                case 2 -> browseUsersUser(username, password);
                case 3 -> viewMyListings(username, password);
                case 4 -> viewMyOrders(username, password);
                case 5 -> viewOffers(username, password);
                case 0 -> loggedIn = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void viewOffers(String username, String password) {
        List<Offer> offers = controller.getOffers(username, password);
        if (offers.isEmpty()) {
            System.out.println("You have no offers.");
            return;
        }
        boolean managingOffers = true;
        while (managingOffers) {
            System.out.println("Your Offers:");
            for (int i = 0; i < offers.size(); i++) {
                System.out.println((i + 1) + ". " + offers.get(i));
            }
            System.out.println("0. Go Back");

            System.out.print("Select an offer to accept/decline (by number) or go back: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 0) {
                managingOffers = false;
            } //oare e ok validarea asta aici sau ar trebui in controller?
            else if (choice > 0 && choice <= offers.size()) {
                Offer selectedOffer = offers.get(choice - 1);
                System.out.println("You selected offer: " + selectedOffer);
                System.out.println("1. Accept Offer");
                System.out.println("2. Decline Offer");
                System.out.print("Choose an option: ");
                int action = scanner.nextInt();
                scanner.nextLine();
                switch (action) {
                    case 1 -> acceptOffer(username, password, selectedOffer);
                    case 2 -> declineOffer(username, password, selectedOffer);
                    default -> System.out.println("Invalid action. Returning to offers menu.");
                }
            }
            else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void declineOffer(String username, String password,Offer selectedOffer) {
        boolean success = controller.declineOffer(username, password, selectedOffer);
        if (success) {
            System.out.println("Offer declined successfully!");
        }
        else {
            System.out.println("Offer decline failed! Please try again.");
        }
    }

    private void acceptOffer(String username, String password, Offer selectedOffer) {
        boolean success = controller.acceptOffer(username, password, selectedOffer);
        if (success) {
            System.out.println("Offer accepted successfully!");
        }
        else {
            System.out.println("Offer accept failed! Please try again.");
        }
    }

    private void viewMyOrders(String username, String password) {
        List<Order> orders = controller.getOrders(username, password);
        if (orders.isEmpty()) {
            System.out.println("You have no orders.");
            return;
        }
        System.out.println("Your Orders:");
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    private void browseProductsUser(String username, String password) {
        boolean browsing = true;
        List<Product> products = new ArrayList<>();

        while (browsing) {
            System.out.println("Product Browsing Options: ");
            System.out.println("1. Sort Products");
            System.out.println("2. Filter Products");
            System.out.println("3. Select Product for Action (like, offer, order)");
            System.out.println("0. Go Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> products = sortProducts();
                case 2 -> products = filterProducts();
                case 3 -> selectProductAction(username, password, products);
                case 0 -> browsing = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private List<Product> sortProducts() {
        System.out.println("Sort Products by:");
        System.out.println("1. Price");
        System.out.println("2. Likes");
        System.out.println("3. Size");
        System.out.println("4. Views");
        System.out.println("5. All products");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Sort in:");
        System.out.println("1. Ascending");
        System.out.println("2. Descending");
        System.out.print("Choose an option: ");

        int order = scanner.nextInt();
        scanner.nextLine();

        List<Product> sortedProducts = controller.sortProducts(choice, order);
        sortedProducts.forEach(System.out::println);
        return sortedProducts;
    }

    private List<Product> filterProducts() {
        System.out.println("Filter Products by: ");
        System.out.println("1. Category");
        System.out.println("2. Brand");
        System.out.println("3. Color");
        System.out.println("4. Seller");
        System.out.println("5. Views");
        System.out.println("6. Condition");
        System.out.println("7. Price");
        System.out.println("8. Size");
        System.out.println("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        List<Product> filteredProducts = controller.filterProducts(choice);
        filteredProducts.forEach(System.out::println);
        return filteredProducts;
    }

    private void selectProductAction(String username, String password,List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("No products to select. Please search or filter products first.");
            return;
        }
        //validare aici
        System.out.print("Enter Product ID to select for action: ");
        int productId = scanner.nextInt();
        Product selectedProduct = products.get(productId);
        scanner.nextLine();
        System.out.println("Choose Action for Product:");
        System.out.println("1. Like Product");
        System.out.println("2. Send Offer");
        System.out.println("3. Make Order");
        System.out.print("Enter your choice: ");

        int actionChoice = scanner.nextInt();
        scanner.nextLine();
        switch (actionChoice) {
            case 1 -> likeProducts(username, password, selectedProduct);
            case 2 -> sendOffer(username, password, selectedProduct);
            case 3 -> makeOrder(username, password, selectedProduct);
            default -> System.out.println("Invalid action choice. Please try again.");
        }
    }

    private void likeProducts(String username, String password,Product selectedProduct) {
        boolean success = controller.likeProduct(username, password, selectedProduct);
        if (success) {
            System.out.println("You liked the product: " + selectedProduct.getName());
        } else {
            System.out.println("Could not like the product. Please try again.");
        }
    }

    private void sendOffer(String username, String password, Product selectedProduct) {
        System.out.print("Enter your offer amount: ");
        double offerAmount = scanner.nextDouble();
        scanner.nextLine();
        //de corectat metodele send offer in controller si userservice, nu este verificat daca
        // pretul oferit e corespunzator
        // nu ar trebui sa aiba un obiect Offer ca parametru
        User seller = selectedProduct.getListedBy();

        // params: seller, username, password, date offer
        boolean success = controller.sendOffer();
        if (success) {
            System.out.println("Offer sent for product: " + selectedProduct.getName() + " with amount " + offerAmount);
        } else {
            System.out.println("Could not send offer. Please try again.");
        }
    }

    private void makeOrder(String username, String password, Product selectedProduct) {
        //de implementat makeOrder
        boolean success = controller.makeOrder();
        if (success) {
            System.out.println("Order placed for product: " + selectedProduct.getName());
        } else {
            System.out.println("Could not place order. Please try again.");
        }
    }

    private void viewMyListings(String username, String password) {
        System.out.println("Your Current Listings:");
        List<Product> myListings = controller.getUserListings(username, password);
        if (myListings.isEmpty()) {
            System.out.println("You have no products listed.");
        }
        else{
            myListings.forEach(System.out::println);
        }
        boolean managingListings = true;
        while (managingListings) {
            System.out.println("\nOptions:");
            System.out.println("1. Add Product to My Listings");
            System.out.println("2. Delete Product from My Listings");
            System.out.println("0. Back to Main Menu");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> addProductToMyListings(username, password);
                case 2 -> deleteProductFromMyListings(username, password);
                case 0 -> managingListings = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addProductToMyListings(String username, String password) {
        System.out.println("Enter product details to add to your listings:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Color: ");
        String color = scanner.nextLine();
        System.out.print("Size: ");
        int size = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Brand: ");
        String brand = scanner.nextLine();
        System.out.print("Condition (e.g., New, Used): ");
        String condition = scanner.nextLine();

        //params: name, color, etc in loc de product
        boolean success = controller.addToUserListings(username, password, );
        if (success) {
            System.out.println("Product added to your listings successfully!");
        }
        else {
            System.out.println("Could not add product to your listings. Please try again.");
        }

    }

    private void deleteProductFromMyListings(String username, String password) {
        //validare aici
        System.out.print("Enter the ID of the product you wish to delete: ");
        int productId = scanner.nextInt();
        scanner.nextLine();

        //params: product_id in loc de product, username, password
        boolean success = controller.removeFromUserListings(username, password, productId);
        if (success) {
            System.out.println("Product deleted successfully.");
        }
        else {
            System.out.println("Product deletion failed. Please check the product ID.");
        }
    }

    private void browseUsersUser(String username, String password) {
        boolean browsing = true;
        List<User> displayedUsers = new ArrayList<>();
        while (browsing) {
            System.out.println("User Browsing Options: ");
            System.out.println("1. Sort Users");
            System.out.println("2. Filter Users");
            System.out.println("3. Select User for Review");
            System.out.println("4. View User Reviews");
            System.out.println("5. View User Listings");
            System.out.println("0. Go Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> displayedUsers = sortUsers();
                case 2 -> displayedUsers = filterUsers();
                case 3 -> selectUserForReview(displayedUsers);
                case 4 -> viewUserReviews(displayedUsers);
                case 5 -> viewUserListings(displayedUsers);
                case 0 -> browsing = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private List<User> sortUsers() {
        System.out.println("Sort Users by:");
        System.out.println("1. Review Count");
        System.out.println("2. Name");
        System.out.println("3. Score");
        System.out.println("4. All Users");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Sort in:");
        System.out.println("1. Ascending");
        System.out.println("2. Descending");
        System.out.print("Choose an option: ");

        int order = scanner.nextInt();
        scanner.nextLine();



        List<User> sortedUsers = controller.sortUsers(choice,order);
        sortedUsers.forEach(System.out::println);
        return sortedUsers;
    }

    private List<User> filterUsers() {
        System.out.println("Filter Users by:");
        System.out.println("1. Minimum Review Count");
        System.out.println("2. Name");
        System.out.println("3. Minimum Score");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        List<User> filteredUsers =controller.filterUsers(choice);
        filteredUsers.forEach(System.out::println);
        return filteredUsers;
    }

    private void viewUserReviews(List<User> displayedUsers) {
        if (displayedUsers.isEmpty()) {
            System.out.println("No users to select. Please search or filter first.");
            return;
        }
        //validare aici
        System.out.print("Enter User ID to see their rating: ");
        int userId = scanner.nextInt();
        scanner.nextLine();
        //controller.getUserReview(userId);
    }

    private void selectUserForReview(List<User> displayedUsers) {
        if (displayedUsers.isEmpty()) {
            System.out.println("No users to select. Please search or filter first.");
            return;
        }
        //validare aici
        System.out.print("Enter User ID to leave a review for: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter review content: ");
        String content = scanner.nextLine();
        System.out.print("Enter rating (1-5): ");
        double rating = scanner.nextInt();
        scanner.nextLine();
        //Problema aici: am putea folosi Id-urile userilor in clasa Review in loc de obiecte user
        //nevoie de validare id selectat daca e in lista displayedUsers
        //Review newReview = new Review(rating, content,)
        //controller.writeReview(newReview);
    }

    private void viewUserListings(List<User> displayedUsers) {
        if (displayedUsers.isEmpty()) {
            System.out.println("No users to select. Please search or filter first.");
            return;
        }
        System.out.print("Enter User ID to view their listings: ");
        //nevoie de validare id selectat daca e in lista displayedUsers
        int userId = scanner.nextInt();
        scanner.nextLine();
        //aici ar trbui facuta cautarea dupa id
        List<Product> userProducts = controller.getUserListing();
        if (userProducts.isEmpty()) {
            System.out.println("This user has no listed products.");
        } else {
            userProducts.forEach(System.out::println);
        }
    }

//ADMIN

    private void adminMenu(String username, String password) {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("1. Manage Products");
            System.out.println("2. Manage Users");
            System.out.println("0. Log Out");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> manageProducts(username, password);
                case 2 -> manageUsers();
                case 0 -> loggedIn = false;
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void manageUsers() {
        boolean browsing = true;
        List<User> displayedUsers = new ArrayList<>();
        while (browsing) {
            System.out.println("User Browsing Options: ");
            System.out.println("1. Sort Users");
            System.out.println("2. Filter Users");
            System.out.println("3. Delete User");
            System.out.println("4. Manage User Reviews");
            System.out.println("0. Go Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> displayedUsers = sortUsers();
                case 2 -> displayedUsers = filterUsers();
                case 3 -> deleteUser(displayedUsers);
                case 4 -> manageReviews(displayedUsers);
                case 0 -> browsing = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void manageReviews(List<User> displayedUsers) {
        if (displayedUsers.isEmpty()) {
            System.out.println("No users to select. Please search or filter first.");
            return;
        }
        System.out.println("Manage User Reviews Options: ");
        System.out.println("1. Delete Review Made by a User");
        System.out.println("2. Delete Review Left for a User");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> deleteReviewMadeByUser(displayedUsers);
            case 2 -> deleteReviewLeftForUser(displayedUsers);
            default -> System.out.println("Invalid choice. Please try again.");
        }
    }

    private void deleteReviewLeftForUser(List<User> displayedUsers) {
        System.out.println("Select a User to see reviews left for them:");
        for (int i = 0; i < displayedUsers.size(); i++) {
            System.out.println((i + 1) + ". " + displayedUsers.get(i).getUserName() + " (ID: " + displayedUsers.get(i).getId() + ")");
        }
        //aici ar trebui o validare
        System.out.print("Enter the number of the user to manage: ");
        int userChoice = scanner.nextInt();
        scanner.nextLine();
        List<Review> reviewsLeftForUser = new ArrayList<>(); //controller.getReviewsLeftForUser()
        if (reviewsLeftForUser.isEmpty()) {
            System.out.println("No reviews found left for this user.");
            return;
        }
        System.out.println("Reviews left for this user: ");
        System.out.println("Reviews:");
        for (Review review : reviewsLeftForUser) {
            System.out.println("Review ID: " + review.getId() + " - " + review.getMessage() + " (Rating: " + review.getGrade() + ")");
        }
        //validare aici
        System.out.print("Enter Review ID to delete: ");
        int reviewId = scanner.nextInt();
        scanner.nextLine();
        //aici tb parametrii
        boolean success = controller.deleteReviewAdmin();
        if (success) {
            System.out.println("Review deleted successfully.");
        } else {
            System.out.println("Failed to delete review. Please check the Review ID.");
        }
    }

    private void deleteReviewMadeByUser(List<User> displayedUsers) {
        System.out.println("Select a User to see reviews they have made:");
        for (int i = 0; i < displayedUsers.size(); i++) {
            System.out.println((i + 1) + ". " + displayedUsers.get(i).getUserName() + " (ID: " + displayedUsers.get(i).getId() + ")");
        }
        //validare aici
        System.out.print("Enter the number of the user to manage: ");
        int userChoice = scanner.nextInt();
        scanner.nextLine();
        List<Review> reviewsLeftByUser = controller.getReviewsLeftByUser();
        if (reviewsLeftByUser.isEmpty()) {
            System.out.println("No reviews made by this user.");
            return;
        }
        System.out.println("Reviews made by this user: ");
        System.out.println("Reviews:");
        for (Review review : reviewsLeftByUser) {
            System.out.println("Review ID: " + review.getId() + " - " + review.getMessage() + " (Rating: " + review.getGrade() + ")");
        }
        //validare aici
        System.out.print("Enter Review ID to delete: ");
        int reviewId = scanner.nextInt();
        scanner.nextLine();

        boolean success = controller.deleteReviewAdmin(reviewId);
        if (success) {
            System.out.println("Review deleted successfully.");
        } else {
            System.out.println("Failed to delete review. Please check the Review ID.");
        }
    }

    private void deleteUser(List<User> displayedUsers) {
        if (displayedUsers.isEmpty()) {
            System.out.println("No users to select. Please search or filter first.");
            return;
        }
        System.out.println("Displayed Users:");
        for (User user : displayedUsers) {
            System.out.println("ID: " + user.getId() + ", Username: " + user.getUserName());
        }
        //validare aici
        System.out.print("Enter User ID to delete: ");
        int userId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Are you sure you want to delete this account? (yes/no): ");
        String confirmation = scanner.nextLine();
        if (confirmation.equalsIgnoreCase("yes")) {
            boolean success = controller.deleteUser(userId);
            if (success)
                System.out.println("Account deleted successfully.");
            else
                System.out.println("Failed to delete user.");
        }
        else {
            System.out.println("Account deletion canceled.");
        }
    }

    private void manageProducts(String username, String password) {
        boolean browsing = true;
        List<Product> products = new ArrayList<>();

        while (browsing) {
            System.out.println("Product Browsing Options: ");
            System.out.println("1. Sort Products");
            System.out.println("2. Filter Products");
            System.out.println("3. Select Product for Action (change category/delete product)");
            System.out.println("0. Go Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> products = sortProducts();
                case 2 -> products = filterProducts();
                case 3 -> selectProductActionAdmin(products, username, password);
                case 0 -> browsing = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void selectProductActionAdmin(List<Product> products, String username, String password) {
        if (products.isEmpty()) {
            System.out.println("No products to select. Please search or filter products first.");
            return;
        }
        //validare aici
        System.out.print("Enter Product ID to select for action: ");
        int productId = scanner.nextInt();

        //cautare cu id in loc de nume seller
       // Product selectedProduct = controller.getProduct();
        scanner.nextLine();
        System.out.println("Choose Action for Product:");
        System.out.println("1. Change Product Category");
        System.out.println("2. Delete Product");
        System.out.print("Enter your choice: ");

        int actionChoice = scanner.nextInt();
        scanner.nextLine();
        switch (actionChoice) {
            case 1 -> changeProductCategory(productId, username, password);
            case 2 -> deleteProduct(productId, username, password);
            default -> System.out.println("Invalid action choice. Please try again.");
        }
    }

    private void deleteProduct(Product selectedProduct, String username, String password) {
        System.out.print("Are you sure you want to delete this product? (yes/no): ");
        String confirmation = scanner.nextLine();
        if (confirmation.equalsIgnoreCase("yes")) {
            boolean success = controller.deleteProduct(username, password, );
            if (success)
                System.out.println("Product deleted successfully.");
            else
                System.out.println("Failed to delete product.");
        }
        else {
            System.out.println("Product deletion canceled.");
        }
    }

    private void changeProductCategory(Product selectedProduct, String username, String password) {
        System.out.println("Available Categories:");
        List<Category> categories = controller.getCategories();
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i).getName());
        }
        //validare aici
        System.out.print("Choose a new category for the product: ");
        int categoryChoice = scanner.nextInt();
        scanner.nextLine();
        boolean success = controller.changeCategory(selectedProduct, categoryChoice, username, password);
        if (success)
            System.out.println("Product category updated successfully.");
        else
            System.out.println("Failed to change category.");
    }


}
