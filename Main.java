import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        IMRepository<User> userRepo = new IMRepository<User>();
        IMRepository<Admin> adminRepo = new IMRepository<Admin>();
        IMRepository<Product> productRepo = new IMRepository<Product>();
        IMRepository<Review> reviewRepo = new IMRepository<Review>();
        IMRepository<Offer> offerRepo = new IMRepository<Offer>();
        IMRepository<Order> orderRepo = new IMRepository<Order>();
        IMRepository<Category> categoryRepo = new IMRepository<>();
        VisitorService visitorService = new VisitorService(userRepo, productRepo, reviewRepo);
        UserService userService = new UserService(userRepo, productRepo, reviewRepo, orderRepo, offerRepo);
        AdminService adminService = new AdminService(userRepo, productRepo, reviewRepo, adminRepo, categoryRepo);
        Controller controller = new Controller(adminService, userService, visitorService);
        ConsoleApp console = new ConsoleApp(controller);


        Category categoryTops = new Category(CategoryName.TOPS);
        Category categoryDresses= new Category(CategoryName.DRESSES);
        Category categoryShoes = new Category(CategoryName.FOOTWEAR);
        Category categoryAccessories = new Category(CategoryName.ACCESSORIES);
        Category categoryOuterwear = new Category(CategoryName.OUTERWEAR);
        Category categoryBottoms=new Category(CategoryName.BOTTOMS);


        Admin a1=new Admin("JohnDoe","qwerty1234","johnedoe@email.com","0747896547");
        Admin a2=new Admin("JaneSmith","1234abc","janesmith@email.com","0748596321");
        Admin a3=new Admin("MikeSteel","a1b2c3d4","mikesteel@email.com","0748693214");

        adminRepo.create(a1);
        adminRepo.create(a2);
        adminRepo.create(a3);

        //Users
        User u9=new User("test","1234","test@email.com","0748596321",0.0);
        userRepo.create(u9);
        User u2= new User("LisaTeak","xyz987","lisateak@gmail.com","0747558114",4.5);
        User u3= new User("TinaSilver","x1y2z3","tinasilver@gmail.com","0758669327",3.5);
        User u4 = new User("JohnSmith", "password123", "john.smith@gmail.com", "0711223344", 4.5);
        User u6 = new User("ChrisLee", "leePass789", "chris.lee@gmail.com", "0755667788", 4.1);

        userRepo.create(u2);
        userRepo.create(u3);
        userRepo.create(u4);
        userRepo.create(u6);




        //Products

        Product p14 = new Product("Sandals", "white", 39, 14.35, "Birkenstock", "New", 0, 0, u9);
        Product p15 = new Product("Sunglasses", "black", 0, 29.99, "Ray-Ban", "New", 0, 0, u9);
        Product p16 = new Product("Blazer", "navy", 44, 30.00, "Zara", "Good", 0, 0, u9);
        Product p17 = new Product("Shorts", "beige", 34, 7.49, "Nike", "Good", 0, 0, u9);
        Product p18 = new Product("Blazer", "navy", 44, 30.00, "Zara", "Good", 0, 0, u9);
        Product p19 = new Product("Shorts", "beige", 34, 7.49, "Nike", "Good", 0, 0, u9);
        Product p20 = new Product("Shorts", "beige", 34, 7.49, "Nike", "Good", 0, 0, u9);
        Product p21 = new Product("Blazer", "navy", 44, 30.00, "Zara", "Good", 0, 0, u9);
        Product p22 = new Product("Shorts", "beige", 34, 7.49, "Nike", "Good", 0, 0, u9);
        Product p23 = new Product("Shorts", "beige", 34, 7.49, "Nike", "Good", 0, 0, u2);

        p14.setCategory(categoryShoes);
        p15.setCategory(categoryAccessories);
        p16.setCategory(categoryOuterwear);
        p17.setCategory(categoryBottoms);
        p18.setCategory(categoryOuterwear);
        p19.setCategory(categoryBottoms);
        p20.setCategory(categoryBottoms);
        p21.setCategory(categoryBottoms);
        p22.setCategory(categoryOuterwear);
        p23.setCategory(categoryBottoms);

        productRepo.create(p14);
        productRepo.create(p15);
        productRepo.create(p16);
        productRepo.create(p17);
        productRepo.create(p18);
        productRepo.create(p19);
        productRepo.create(p20);
        productRepo.create(p21);
        productRepo.create(p22);
        productRepo.create(p23);





        //Offers
        Offer o1 = new Offer("Would you consider selling this for 7.00?", 14.00, p14, u6, u9);
        Offer o2 = new Offer("Can I get this for 9.50?", 28.00, p15,  u3, u9);
        Offer o3 = new Offer("Would you accept 10.00?", 28.00, p16, u4, u9);
        Offer o4 = new Offer("How about 12.00 for this item?", 7.00, p17, u2, u9);

        offerRepo.create(o1);
        offerRepo.create(o2);
        offerRepo.create(o3);
        offerRepo.create(o4);
        controller.userService.sendOffer(u6.getUserName(),u6.getPassword(),"Would you consider..",p14,14.00);
        controller.userService.sendOffer(u3.getUserName(),u3.getPassword(),"Would you consider..",p15,28.00);
        controller.userService.sendOffer(u4.getUserName(),u4.getPassword(),"Would you consider..",p16,28.00);
        controller.userService.sendOffer(u2.getUserName(),u2.getPassword(),"Would you consider..",p17,7.00);


        controller.userService.acceptOffer(u9.getUserName(),u9.getPassword(),o1.getId());
        controller.userService.acceptOffer(u9.getUserName(),u9.getPassword(),o4.getId());
        controller.userService.acceptOffer(u9.getUserName(),u9.getPassword(),o3.getId());
        controller.userService.acceptOffer(u9.getUserName(),u9.getPassword(),o2.getId());




        //orders
        List<Product> orderProducts0 = List.of(p19);
        Order or1 = new Order(orderProducts0, "sent", "Strada xyz", u3, p19.getListedBy());
        List<Product> orderProducts1 = List.of(p18);
        Order or2 = new Order(orderProducts1, "sent", "Strada xyz", u2, p19.getListedBy());
        List<Product> orderProducts2 = List.of(p20);
        Order or3 = new Order(orderProducts1, "sent", "Strada xyz", u4, p19.getListedBy());
        List<Product> orderProducts3 = List.of(p21);
        Order or4 = new Order(orderProducts1, "sent", "Strada xyz", u6, p19.getListedBy());
        List<Product> orderedProducts4=List.of(p23);
        Order or5=new Order(orderedProducts4,"sent","Strada xyz",u9,p23.getListedBy());

        orderRepo.create(or1);
        orderRepo.create(or2);
        orderRepo.create(or3);
        orderRepo.create(or4);
        orderRepo.create(or5);

        userService.placeOrder(u2.getUserName(),u2.getPassword(),List.of(p18.getId()),"send","Stradax",u9.getId());
        userService.placeOrder(u3.getUserName(),u3.getPassword(),List.of(p19.getId()),"send","Stradax",u9.getId());
        userService.placeOrder(u4.getUserName(),u4.getPassword(),List.of(p20.getId()),"send","Stradax",u9.getId());
        userService.placeOrder(u6.getUserName(),u6.getPassword(),List.of(p21.getId()),"send","Stradax",u9.getId());
        userService.placeOrder(u9.getUserName(),u9.getPassword(),List.of(p21.getId()),"send","Stradax",u2.getId());





        //reviews
        Review r13=new Review(2.5,"Not good!",u2,u9);
        Review r14=new Review(1.5,"Bad!",u4,u9);
        Review r15=new Review(3.8,"Fairly good",u3,u9);
        Review r16=new Review(4.2,"Good service",u6,u9);
        Review r17=new Review(1.00,"Bla bla not good!!!!!",u9,u2);
        Review r18=new Review(1.5,"Bad",u6,u9);

        reviewRepo.create(r13);
        reviewRepo.create(r14);
        reviewRepo.create(r15);
        reviewRepo.create(r16);
        reviewRepo.create(r17);
        reviewRepo.create(r18);

        controller.writeReview(u2.getUserName(),u2.getPassword(),2.5,"Not good",u9.getId());
        controller.writeReview(u4.getUserName(),u4.getPassword(),1.5,"Not good",u9.getId());
        controller.writeReview(u3.getUserName(),u3.getPassword(),3.8,"Good",u9.getId());
        controller.writeReview(u6.getUserName(),u6.getPassword(),4.5,"very good",u9.getId());
        controller.writeReview(u6.getUserName(),u6.getPassword(),1.5,"Bad",u9.getId());

        controller.writeReview(u9.getUserName(),u9.getPassword(),1.00,"Bla Blas not good!!!!!",u2.getId());





        //falgged actions
        controller.adminService.updateCategory(a1.getUserName(),a1.getPassword(),p21.getId(),categoryOuterwear);

        controller.adminService.deleteProduct(a1.getUserName(),a1.getPassword(),p14.getId());
        controller.adminService.updateCategory(a1.getUserName(),a1.getPassword(),p17.getId(),categoryOuterwear);
        //controller.adminService.deleteReview(a1.getUserName(),a1.getPassword(),r17.getId());



        System.out.println(controller.getUserAverageAcceptanceRate(u9.getId()));
        //System.out.println(controller.getUserAverageAcceptanceRate(u6.getId()));
        //System.out.println(controller.getUserTrustScore(u9.getId()));
        //System.out.println(controller.getUsersTotalNrOfSales(u9.getId())); // output 9? adauga si order ul facut de u9
        //System.out.println(controller.getUserPositiveReviews(u9.getId())); //output 4 are trebui 2
        //System.out.println(controller.getUserNegativeReviews(u9.getId()));  //output 6 are trebui 3
        System.out.println(controller.getFlaggedActions(u9.getId()));//output 1 problema la metoda


    }
}
