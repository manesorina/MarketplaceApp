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
        System.out.println("Please enter your email address: ");
        String email = scanner.nextLine();
        System.out.println("Please enter your password: ");
        String password = scanner.nextLine();
        int result = 0; //aici functia din controller care cauta credentialele
        // si vede daca sunt de admin sau de user, daca sunt de user atunci result va fi 1, daca sunt de admin
        //result va fi 2, 0 daca log in fail
        if (result == 1) {
            userMenu();
        }
        if (result == 2) {
            adminMenu();
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
    private void userMenu() {
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
                case 1 -> browseProductsUser();
                case 2 -> browseUsersUser();
                case 3 -> viewMyListings();
                case 4 -> viewMyOrders();
                case 5 -> viewOffers();
                case 0 -> loggedIn = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void viewOffers() {
        List<Offer> offers = new ArrayList<>(); //controller.getOffers()
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
            } else if (choice > 0 && choice <= offers.size()) {
                Offer selectedOffer = offers.get(choice - 1);
                System.out.println("You selected offer: " + selectedOffer);
                System.out.println("1. Accept Offer");
                System.out.println("2. Decline Offer");
                System.out.print("Choose an option: ");
                int action = scanner.nextInt();
                scanner.nextLine();
                switch (action) {
                    case 1 -> acceptOffer(selectedOffer);
                    case 2 -> declineOffer(selectedOffer);
                    default -> System.out.println("Invalid action. Returning to offers menu.");
                }
            }
            else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void declineOffer(Offer selectedOffer) {
        boolean success = true; //controller.declineOffer()
        if (success) {
            System.out.println("Offer declined successfully!");
        }
        else {
            System.out.println("Offer decline failed! Please try again.");
        }
    }

    private void acceptOffer(Offer selectedOffer) {
        boolean success = true; //controller.acceptOffer()
        if (success) {
            System.out.println("Offer accepted successfully!");
        }
        else {
            System.out.println("Offer accept failed! Please try again.");
        }
    }

    private void viewMyOrders() {
        List<Order> orders = new ArrayList<>(); //controller.getOrders()
        if (orders.isEmpty()) {
            System.out.println("You have no orders.");
            return;
        }
        System.out.println("Your Orders:");
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    private void browseProductsUser() {
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
                case 3 -> selectProductAction(products);
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

    private void selectProductAction(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("No products to select. Please search or filter products first.");
            return;
        }
        System.out.print("Enter Product ID to select for action: ");
        int productId = scanner.nextInt();
        Product selectedProduct = products.get(productId); //controller.getProduct();
        scanner.nextLine();
        System.out.println("Choose Action for Product:");
        System.out.println("1. Like Product");
        System.out.println("2. Send Offer");
        System.out.println("3. Make Order");
        System.out.print("Enter your choice: ");

        int actionChoice = scanner.nextInt();
        scanner.nextLine();
        switch (actionChoice) {
            case 1 -> likeProducts(selectedProduct);
            case 2 -> sendOffer(selectedProduct);
            case 3 -> makeOrder(selectedProduct);
            default -> System.out.println("Invalid action choice. Please try again.");
        }
    }

    private void likeProducts(Product selectedProduct) {
        boolean success = true; //controller.likeProduct();
        if (success) {
            System.out.println("You liked the product: " + selectedProduct.getName());
        } else {
            System.out.println("Could not like the product. Please try again.");
        }
    }

    private void sendOffer(Product selectedProduct) {
        System.out.print("Enter your offer amount: ");
        double offerAmount = scanner.nextDouble();
        scanner.nextLine();
        boolean success = true; //controller.sendOffer();
        if (success) {
            System.out.println("Offer sent for product: " + selectedProduct.getName() + " with amount " + offerAmount);
        } else {
            System.out.println("Could not send offer. Please try again.");
        }
    }

    private void makeOrder(Product selectedProduct) {
        boolean success = true; //controller.makeOrder();
        if (success) {
            System.out.println("Order placed for product: " + selectedProduct.getName());
        } else {
            System.out.println("Could not place order. Please try again.");
        }
    }

    private void viewMyListings() {
        System.out.println("Your Current Listings:");
        List<Product> myListings = new ArrayList<>();//controller.getUserListings(); trebuie functie care returneaza produsele postate de user
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
                case 1 -> addProductToMyListings();
                case 2 -> deleteProductFromMyListings();
                case 0 -> managingListings = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addProductToMyListings() {
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

        boolean success = true; //controller.addToUserListings()
        if (success) {
            System.out.println("Product added to your listings successfully!");
        }
        else {
            System.out.println("Could not add product to your listings. Please try again.");
        }

    }

    private void deleteProductFromMyListings() {
        System.out.print("Enter the ID of the product you wish to delete: ");
        int productId = scanner.nextInt();
        scanner.nextLine();
        boolean success = false; // functie booleana controller care cauta produsul dupa id si il sterge
        if (success) {
            System.out.println("Product deleted successfully.");
        }
        else {
            System.out.println("Product deletion failed. Please check the product ID.");
        }
    }

    private void browseUsersUser() {
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
        System.out.print("Enter User ID to leave a review for: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter review content: ");
        String content = scanner.nextLine();
        System.out.print("Enter rating (1-5): ");
        int rating = scanner.nextInt();
        scanner.nextLine();
        //controller.leaveReview(userId, content, rating);
    }

    private void viewUserListings(List<User> displayedUsers) {
        if (displayedUsers.isEmpty()) {
            System.out.println("No users to select. Please search or filter first.");
            return;
        }
        System.out.print("Enter User ID to view their listings: ");
        int userId = scanner.nextInt();
        scanner.nextLine();
        List<Product> userProducts = controller.getUserListings();
        if (userProducts.isEmpty()) {
            System.out.println("This user has no listed products.");
        } else {
            userProducts.forEach(System.out::println);
        }
    }

//ADMIN

    private void adminMenu() {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("1. Manage Products");
            System.out.println("2. Manage Users");
            System.out.println("0. Log Out");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> manageProducts();
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
        System.out.print("Enter Review ID to delete: ");
        int reviewId = scanner.nextInt();
        scanner.nextLine();

        boolean success = true; //controller.deleteReview(reviewId)
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
        System.out.print("Enter the number of the user to manage: ");
        int userChoice = scanner.nextInt();
        scanner.nextLine();
        List<Review> reviewsLeftByUser = new ArrayList<>(); //controller.getReviewsLeftByUser()
        if (reviewsLeftByUser.isEmpty()) {
            System.out.println("No reviews made by this user.");
            return;
        }
        System.out.println("Reviews made by this user: ");
        System.out.println("Reviews:");
        for (Review review : reviewsLeftByUser) {
            System.out.println("Review ID: " + review.getId() + " - " + review.getMessage() + " (Rating: " + review.getGrade() + ")");
        }
        System.out.print("Enter Review ID to delete: ");
        int reviewId = scanner.nextInt();
        scanner.nextLine();

        boolean success = true; //controller.deleteReview(reviewId)
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
        System.out.print("Enter User ID to delete: ");
        int userId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Are you sure you want to delete this account? (yes/no): ");
        String confirmation = scanner.nextLine();
        if (confirmation.equalsIgnoreCase("yes")) {
            //controller.deleteUser(userId)
            System.out.println("Account deleted successfully.");
        }
        else {
            System.out.println("Account deletion canceled.");
        }
    }

    private void manageProducts() {
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
                case 3 -> selectProductActionAdmin(products);
                case 0 -> browsing = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void selectProductActionAdmin(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("No products to select. Please search or filter products first.");
            return;
        }
        System.out.print("Enter Product ID to select for action: ");
        int productId = scanner.nextInt();
        Product selectedProduct = products.get(productId); //controller.getProduct();
        scanner.nextLine();
        System.out.println("Choose Action for Product:");
        System.out.println("1. Change Product Category");
        System.out.println("2. Delete Product");
        System.out.print("Enter your choice: ");

        int actionChoice = scanner.nextInt();
        scanner.nextLine();
        switch (actionChoice) {
            case 1 -> changeProductCategory(selectedProduct);
            case 2 -> deleteProduct(selectedProduct);
            default -> System.out.println("Invalid action choice. Please try again.");
        }
    }

    private void deleteProduct(Product selectedProduct) {
        System.out.print("Are you sure you want to delete this product? (yes/no): ");
        String confirmation = scanner.nextLine();
        if (confirmation.equalsIgnoreCase("yes")) {
            //controller.deleteProduct()
            System.out.println("Product deleted successfully.");
        }
        else {
            System.out.println("Product deletion canceled.");
        }
    }

    private void changeProductCategory(Product selectedProduct) {
        System.out.println("Available Categories:");
        List<Category> categories = new ArrayList<>(); //controller.getCategroies()
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i).getName());
        }
        System.out.print("Choose a new category for the product: ");
        int categoryChoice = scanner.nextInt();
        scanner.nextLine();
        //controller.changeCategory(choice)
        System.out.println("Product category updated successfully.");
    }


}
