public class ConsoleApp {
    private final Controller controller;

    public ConsoleApp(Controller controller) {
        this.controller = controller;
    }

    public void start() {
        System.out.println("Welcome to the Marketplace App!");
        boolean running = true;
        while (running) {

        }
    }

    private void displayStartMenu() {
        System.out.println("\n1. Log in");
        System.out.println("2. Sign up");
        System.out.println("3. Search products");
        System.out.println("4. Search users");
        System.out.println("5. Exit");
    }

    private void displayLoggedInMenu() {
        System.out.println("\n1. See profile"); //aici vezi credentialele in afara de parola si produsele listate
        System.out.println("2. Add a new product");
        System.out.println("3. Delete a product");
        System.out.println("4. Edit a product");
        System.out.println("5. Search products");
        System.out.println("6. Search users");

    }
}
