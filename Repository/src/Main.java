import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String userFilename = "Repository/users.json";
        String productFilename = "Repository/products.json";


        ProductFileRepository fileProductRepo = new ProductFileRepository(productFilename);
        UserFileRepository fileUserRepo = new UserFileRepository(userFilename);

        User user1 = new User("John Doe", "1234", "johndoe@gmail.com", "0724578967", 3.5);
        user1.nrOfFlaggedActions = 2;

        User user2 = new User("Jane Smith", "abc", "janesmith.com", "0724567789", 2.5);

        user1.nrOfFlaggedActions = 3;

        Product product1 = new Product( "T-shirt", "Red", 42, 19.99, "BrandA", "New", 100, 10, user1.getId());
        Product product2 = new Product( "Jeans", "Blue", 32, 39.99, "BrandB", "Used", 150, 25, user1.getId());
        Product product3 = new Product( "Users 2 thsirt", "Blue", 32, 39.99, "BrandB", "Used", 150, 25, user2.getId());
        Product product4 = new Product( "Users 2 jean", "Blue", 32, 39.99, "BrandB", "Used", 150, 25, user2.getId());


        Category categoryTops = new Category(CategoryName.TOPS);
        Category categoryBottoms=new Category(CategoryName.BOTTOMS);
        product1.setCategory(categoryTops);
        product3.setCategory(categoryTops);
        product2.setCategory(categoryBottoms);
        product4.setCategory(categoryBottoms);





        user1.getFavourites().add(product3.getId());
        user1.getListedProducts().addAll(Arrays.asList(product1.getId(),product2.getId()));

        user2.getFavourites().add(product1.getId());
        user1.getListedProducts().addAll(Arrays.asList(product3.getId(),product4.getId()));


        fileUserRepo.create(user1);
        fileUserRepo.create(user2);

        fileProductRepo.create(product1);
        fileProductRepo.create(product3);
        fileProductRepo.create(product4);
        fileProductRepo.create(product2);





        System.out.println("Before Deletion:");
        System.out.println("User 1: " + fileUserRepo.read(user1.getId()));
        System.out.println("User 2: " + fileUserRepo.read(user2.getId()));


        //System.out.println("\nDeleting User 1...");
        //fileUserRepo.delete(user1.getId());

        // Print users after deletion
        //System.out.println("\nAfter Deletion:");
        //System.out.println("User 1: " + userRepo.read(user1.getId()));  // Should return null or empty
        //System.out.println("User 2: " + userRepo.read(user2.getId()));  // Should still be available
    }
}
