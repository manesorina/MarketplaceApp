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
        System.out.println("Browse products: ");
        System.out.println("1. See all available products");
        System.out.println("2. Search products by price range");
        System.out.println("3. Search products by name");
        System.out.println("4. Search products by seller name");
        System.out.println("5. Search products by brand");
        System.out.println("6. Search products by category");
        System.out.println("7. Search products by condition");
        System.out.println("8. Search products by size");
        System.out.println("9. Search products by color");
        System.out.println("Choose option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
    }

    private void browseUsersVisitor() {

    }

    private void userMenu() {}
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
    private void browseProductsUser() {}
    private void browseUsersUser() {}


}
