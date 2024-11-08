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
            printmainMenu();
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
    private void printmainMenu() {
        while (true) {
            System.out.println("Welcome to the Marketplace App!");
            System.out.println("1. Log in");
            System.out.println("2. Sign up");
            System.out.println("3. Browse products");
            System.out.println("4. Browse users");
            System.out.println("0. Exit");
            System.out.print("Please select an option: ");
        }
    }

    private void signUp() {
        System.out.println("Please enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Please enter your email address: ");
        String email = scanner.nextLine();
        System.out.println("Please enter your password: ");
        String password = scanner.nextLine();
        System.out.println("Please enter your phone number: ");
        String phoneNumber = scanner.nextLine();
        boolean success = false; //aici functia din controller pt sign up
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

    }

    private void browseUsersVisitor() {

    }

//USER
    private void userMenu() {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("Welcome to your profile!");
            System.out.println("1. Browse Products");
            System.out.println("2. Browse Users");
            System.out.println("3. View My Listings");
            System.out.println("0. Log Out");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> browseProductsUser();
                case 2 -> browseUsersUser();
                case 3 -> viewMyListings();
                case 0 -> loggedIn = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
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
                case 1 -> sortProducts();
                case 2 -> filterProducts();
                case 3 -> selectProductAction();
                case 0 -> browsing = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void sortProducts() {}

    private void filterProducts() {}

    private void likeProducts() {}

    private void sendOffer() {}

    private void makeOrder() {}

    private void selectProductAction() {}

    private void viewMyListings() {
        System.out.println("Your Current Listings:");
        //List<Product> myListings = controller.getUserListings(); trebuie functie care returneaza produsele postate de user
//        if (myListings.isEmpty()) {
//            System.out.println("You have no products listed.");
//        }
//        else{
//            myListings.forEach(System.out::println);
//        }
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
                //case 1 -> functie controller pt adaugat produs
                case 2 -> deleteProductFromMyListings();
                case 0 -> managingListings = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
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
            System.out.println("0. Go Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> sortUsers();
                case 2 -> filterUsers();
                case 3 -> selectUserForReview(displayedUsers);
                case 4 -> viewUserReviews(displayedUsers);
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
        List<User> sortedUsers = new ArrayList<>(); //controller.sortUsers(choice);
        sortedUsers.forEach(System.out::println);
        return sortedUsers;
    }

    private List<User> filterUsers() {
        System.out.println("Filter Users by:");
        System.out.println("1. Minimum Review Count");
        System.out.println("2. Search by Name");
        System.out.println("3. Minimum Score");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        List<User> filteredUsers = new ArrayList<>(); //controller.filterUsers(choice);
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

//ADMIN

    private void adminMenu() {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("1. Manage Categories");
            System.out.println("2. Manage Users");
            System.out.println("3. Manage Products");
            System.out.println("4. Manage Reviews");
            System.out.println("0. Log Out");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 0 -> loggedIn = false;
                default -> System.out.println("Invalid choice.");
            }
        }
    }


}
