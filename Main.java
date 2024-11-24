import Controller.Controller;
import Domain.*;
import Presentation.ConsoleApp;
import Repository.*;
import Service.AdminService;
import Service.UserService;
import Service.VisitorService;


import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String userFilename = "Repository/ObjectFiles/users.txt";
        String productFilename = "Repository/ObjectFiles/products.txt";
        String categoriesFilename = "Repository/ObjectFiles/categories.txt";
        String offersFilename = "Repository/ObjectFiles/offers.txt";
        String orderFilename = "Repository/ObjectFiles/orders.txt";
        String reviewsFilename = "Repository/ObjectFiles/reviews.txt";
        String adminsFilename = "Repository/ObjectFiles/admins.txt";
        String visitorsFilename = "Repository/ObjectFiles/visitors.txt";
        String likedProducts = "Repository/ObjectFiles/likedProducts.txt";
        String listedProducts = "Repository/ObjectFiles/listedProducts.txt";
        String orderedProducts = "Repository/ObjectFiles/orderedProducts.txt";
        VisitorFileRepository visitorRepo = new VisitorFileRepository(visitorsFilename);
        UserFileRepository userRepo = new UserFileRepository(userFilename, listedProducts, likedProducts);
        ProductFileRepository productRepo = new ProductFileRepository(productFilename);
        CategoryFileRepository categoryRepo = new CategoryFileRepository(categoriesFilename);
        OfferFileRepository offerRepo = new OfferFileRepository(offersFilename);
        OrderFileRepository orderRepo = new OrderFileRepository(orderFilename, orderedProducts);
        ReviewFileRepository reviewRepo = new ReviewFileRepository(reviewsFilename);
        AdminFileRepository adminRepo = new AdminFileRepository(adminsFilename);
        VisitorService visitorService = new VisitorService(userRepo, productRepo, reviewRepo, categoryRepo);
        UserService userService = new UserService(userRepo, productRepo, reviewRepo, categoryRepo, orderRepo, offerRepo);
        AdminService adminService = new AdminService(userRepo, productRepo, reviewRepo, adminRepo, categoryRepo, orderRepo);
        Controller controller = new Controller(adminService, userService, visitorService);
        ConsoleApp console = new ConsoleApp(controller);


        Category categoryTops = new Category(CategoryName.TOPS);
        Category categoryDresses= new Category(CategoryName.DRESSES);
        Category categoryShoes = new Category(CategoryName.FOOTWEAR);
        Category categoryAccessories = new Category(CategoryName.ACCESSORIES);
        Category categoryOuterwear = new Category(CategoryName.OUTERWEAR);
        Category categoryBottoms=new Category(CategoryName.BOTTOMS);

//        categoryRepo.create(categoryTops);
//        categoryRepo.create(categoryDresses);
//        categoryRepo.create(categoryShoes);
//        categoryRepo.create(categoryAccessories);
//        categoryRepo.create(categoryOuterwear);
//        categoryRepo.create(categoryBottoms);
//
//
//        Admin a1=new Admin("JohnDoe","qwerty1234","johnedoe@email.com","0747896547");
//        Admin a2=new Admin("JaneSmith","1234abc","janesmith@email.com","0748596321");
//        Admin a3=new Admin("MikeSteel","a1b2c3d4","mikesteel@email.com","0748693214");
//
//        adminRepo.create(a1);
//        adminRepo.create(a2);
//        adminRepo.create(a3);
//
//        Visitor v1=new Visitor();
//        Visitor v2=new Visitor();
//
//        visitorRepo.create(v1);
//        visitorRepo.create(v2);
//
//        List<Admin> admins = adminRepo.getAll();
//        for (Admin admin: admins) {
//            System.out.println(admin);
//        }
//        System.out.println();
//
//        //Users
//        User u1= new User("LisaTeak","xyz987","lisateak@gmail.com","0747558114",4.5);
//        User u2= new User("TinaSilver","x1y2z3","tinasilver@gmail.com","0758669327",3.5);
//        User u3 = new User("JohnSmith", "password123", "john.smith@gmail.com", "0711223344", 4.5);
//        User u4 = new User("ChrisLee", "leePass789", "chris.lee@gmail.com", "0755667788", 4.1);
//
//        userRepo.create(u1);
//        userRepo.create(u2);
//        userRepo.create(u3);
//        userRepo.create(u4);
//
//
//
//        List<User> users = userRepo.getAll();
//        for (User user: users) {
//            System.out.println(user);
//        }
//        System.out.println();
//
//
//        //Products
//
//
//        userService.listProduct(u1.getUserName(),u1.getPassword(),categoryShoes.getId(),"Sandals", "white", 39, 14.35, "Birkenstock", "New", 0, 0);
//        userService.listProduct(u1.getUserName(),u1.getPassword(),categoryAccessories.getId(),"Sunglasses", "black", 0, 29.99, "Ray-Ban", "New", 0, 0);
//        userService.listProduct(u2.getUserName(),u2.getPassword(),categoryOuterwear.getId(),"Blazer", "navy", 44, 30.00, "Zara", "Good", 0, 0);
//        userService.listProduct(u2.getUserName(),u2.getPassword(),categoryBottoms.getId(),"Shorts", "beige", 34, 7.49, "Nike", "Good", 0, 0);
//        userService.listProduct(u2.getUserName(),u2.getPassword(),categoryTops.getId(),"Coat", "black", 44, 40.00, "Carhartt", "Bad", 0, 0);
//        userService.listProduct(u2.getUserName(),u2.getPassword(),categoryAccessories.getId(),"Skirt", "pink", 36, 15.00, "Orsay", "New", 0, 0);
//        userService.listProduct(u2.getUserName(),u2.getPassword(),categoryOuterwear.getId(),"Dress", "purple", 38, 50.00, "C&A", "Worn", 0, 0);
//
//
//
//        controller.addToUserListings(u1.getUserName(),u1.getPassword(),categoryShoes.getId(),"Sandals", "white", 39, 14.35, "Birkenstock", "New", 0, 0);
//        controller.addToUserListings(u1.getUserName(),u1.getPassword(),categoryAccessories.getId(),"Sunglasses", "black", 0, 29.99, "Ray-Ban", "New", 0, 0);
//        controller.addToUserListings(u2.getUserName(),u2.getPassword(),categoryOuterwear.getId(),"Blazer", "navy", 44, 30.00, "Zara", "Good", 0, 0);
//        controller.addToUserListings(u2.getUserName(),u2.getPassword(),categoryBottoms.getId(),"Shorts", "beige", 34, 7.49, "Nike", "Good", 0, 0);
//        controller.addToUserListings(u2.getUserName(),u2.getPassword(),categoryTops.getId(),"Coat", "black", 44, 40.00, "Carhartt", "Bad", 0, 0);
//        controller.addToUserListings(u2.getUserName(),u2.getPassword(),categoryAccessories.getId(),"Skirt", "pink", 36, 15.00, "Orsay", "New", 0, 0);
//        controller.addToUserListings(u2.getUserName(),u2.getPassword(),categoryOuterwear.getId(),"Dress", "purple", 38, 50.00, "C&A", "Worn", 0, 0);
//
//
//        List<Product> products = productRepo.getAll();
//        for (Product product: products) {
//            System.out.println(product);
//        }
//
//        System.out.println();
//
//
//        //Offers
//
//        controller.userService.sendOffer(u3.getUserName(),u3.getPassword(),"Would you consider..",1,14.00);
//        controller.userService.sendOffer(u3.getUserName(),u3.getPassword(),"Would you consider..",2,28.00);
//        controller.userService.sendOffer(u4.getUserName(),u4.getPassword(),"Would you consider..",3,28.00);
//        controller.userService.sendOffer(u4.getUserName(),u4.getPassword(),"Would you consider..",4,7.00);
//
//
//        controller.userService.declineOffer(u1.getUserName(),u1.getPassword(),1);
//        controller.userService.acceptOffer(u2.getUserName(),u2.getPassword(),3);
//
//        List<Offer> offers = offerRepo.getAll();
//        for (Offer offer: offers) {
//            System.out.println(offer);
//        }
//        System.out.println();
//
//        //orders
//
//        controller.userService.placeOrder(u3.getUserName(),u3.getPassword(), List.of(1), "sent", "StradaX");
//
//        List<Integer> orderedProducts4=new ArrayList<>();
//        orderedProducts4.add(3);
//        orderedProducts4.add(4);
//        controller.userService.placeOrder(u4.getUserName(),u4.getPassword(), orderedProducts4, "sent", "StradaY");
//        controller.userService.placeOrder(u2.getUserName(),u2.getPassword(), List.of(2), "sent", "StradaX");
//
//        List<Order> orders = orderRepo.getAll();
//        for (Order order: orders) {
//            System.out.println(order);
//        }
//        System.out.println();
//
//        //reviews
//
//        controller.writeReview(u3.getUserName(),u3.getPassword(),2.5,"Not good",u1.getId());
//        controller.writeReview(u4.getUserName(),u4.getPassword(),4.5,"Very good",u2.getId());
//        controller.writeReview(u2.getUserName(),u2.getPassword(), 1.0, "Terrible", u1.getId());
//
//        List<Review> reviews = reviewRepo.getAll();
//        for (Review review: reviews) {
//            System.out.println(review);
//        }
//
//
//        controller.userService.addToFavorites(u3.getUserName(),u3.getPassword(),6);
//        controller.userService.addToFavorites(u4.getUserName(),u4.getPassword(),7);
//        System.out.println();
//
//        //flagged actions
//        controller.adminService.updateCategory(a1.getUserName(),a1.getPassword(),p5.getId(),categoryOuterwear.getId());
//        controller.adminService.deleteProduct(a1.getUserName(),a1.getPassword(),p7.getId());
//        controller.adminService.updateCategory(a1.getUserName(),a1.getPassword(),p6.getId(),categoryBottoms.getId());
//        controller.adminService.deleteReview(a1.getUserName(),a1.getPassword(),3);
//
//        System.out.println("Products after deletion and category modification: ");
//        List<Product> products2=productRepo.getAll();
//        for (Product product: products2) {
//            System.out.println(product);
//        }
//        System.out.println();
//
//        System.out.println("Reviews after deletion: ");
//        List<Review> reviews2 = reviewRepo.getAll();
//        for (Review review: reviews2) {
//            System.out.println(review);
//        }
//        System.out.println();
//
//        System.out.println("Average acceptance rate: ");
//        System.out.println("User1: ");
//        System.out.println(controller.getUserAverageAcceptanceRate(u1.getId()));
//        System.out.println("User2: ");
//        System.out.println(controller.getUserAverageAcceptanceRate(u2.getId()));
//
//        System.out.println();
//        System.out.println(controller.getUserTrustScore(u2.getId()));
//        System.out.println(controller.getUsersTotalNrOfSales(u2.getId()));
//        System.out.println(controller.getUserPositiveReviews(u2.getId()));
//        System.out.println(controller.getUserNegativeReviews(u2.getId()));
//        System.out.println(controller.getFlaggedActions(u2.getId()));
//        System.out.println();
//
//        System.out.println("Complex method that entails three entities(Product, Order, Category)");
//        System.out.println(adminService.sortCategoriesByIncome());
//        System.out.println();
//
//        System.out.println("Filter users by name: ");
//        System.out.println(controller.filterUsersByName("lisa"));
//        System.out.println();
//
//        System.out.println("Filter products by color: ");
//        System.out.println(controller.filterProductsByColor("black"));
//        System.out.println();
//
//        System.out.println("Sort products by price descending: ");
//        System.out.println(controller.sortProducts(1,2));
//        System.out.println();
//
//        System.out.println("Sort products by size ascending: ");
//        System.out.println(controller.sortProducts(3,1));
//        System.out.println();

        console.start();


    }
}
