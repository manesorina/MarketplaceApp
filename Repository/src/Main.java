import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        IMRepository<Product> productRepo=new IMRepository<>();
        IMRepository<Admin> adminRepo=new IMRepository<>();
        IMRepository<User> userRepo=new IMRepository<>();
        IMRepository<Review> reviewRepo=new IMRepository<>();
        IMRepository<Offer> offerRepo=new IMRepository<>();
        IMRepository<Order> orderRepo=new IMRepository<>();
        IMRepository<Category> categoryRepo=new IMRepository<>();



        //repo.delete(1);



        Admin a1=new Admin("JohnDoe","qwerty1234","johnedoe@email.com","0747896547");
        Admin a2=new Admin("JaneSmith","1234abc","janesmith@email.com","0748596321");
        Admin a3=new Admin("MikeSteel","a1b2c3d4","mikesteel@email.com","0748693214");

        adminRepo.create(a1);
        adminRepo.create(a2);
        adminRepo.create(a3);



        User u1= new User("MikeHouse","1q2w3e4r","mikehouse@gmail.com","0748996332",2.4);
        User u2= new User("LisaTeak","xyz987","lisateak@gmail.com","0747558114",4.5);
        User u3= new User("TinaSilver","x1y2z3","tinasilver@gmail.com","0758669327",3.5);
        User u4 = new User("JohnSmith", "password123", "john.smith@gmail.com", "0711223344", 4.5);
        User u5 = new User("AnnaTaylor", "securePass", "anna.taylor@gmail.com", "0722334455", 3.8);
        User u6 = new User("ChrisLee", "leePass789", "chris.lee@gmail.com", "0755667788", 4.1);
        User u7 = new User("SarahYoung", "sarahY123", "sarah.young@gmail.com", "0733445566", 3.2);
        User u8 = new User("DavidBrown", "davidB2021", "david.brown@gmail.com", "0767889900", 4.0);

        userRepo.create(u1);
        userRepo.create(u2);
        userRepo.create(u3);
        userRepo.create(u4);
        userRepo.create(u5);
        userRepo.create(u6);
        userRepo.create(u7);
        userRepo.create(u8);


        //System.out.println(userRepo.getAll());

        Product p1=new Product("TShirt","blue",36,8.99,"Nike","Worn",0,0,u1);
        Product p2=new Product("Pants","black",38,10.99,"Nike","Worn",0,0,u2);
        Product p3=new Product("Shoes","yellow",40,11.45,"Puma","New",0,0,u1);
        Product p4=new Product("Dress","pink",40,15.23,"H&M","Good",0,0,u1);
        Product p5=new Product("Vintage Belt","brown",40,8.56,"H&M","Good",0,0,u3);
        Product p6=new Product("TShirt","white",36,9.64,"Zara","Worn",0,0,u3);
        Product p7 = new Product("Hat", "red", 57, 6.49, "Adidas", "Like New", 0, 0, u4);
        Product p8 = new Product("Jeans", "blue", 32, 12.99, "Levi's", "Good", 0, 0, u5);
        Product p9 = new Product("Jacket", "green", 42, 25.75, "North Face", "New", 0, 0, u6);
        Product p10 = new Product("Sneakers", "black", 41, 19.99, "Reebok", "Worn", 0, 0, u7);
        Product p11 = new Product("Skirt", "purple", 36, 9.89, "H&M", "Good", 0, 0, u8);
        Product p12 = new Product("Scarf", "orange", 0, 4.99, "S.Oliver", "Like New", 0, 0, u5);
        Product p13 = new Product("TShirt", "green", 40, 8.75, "Nike", "Worn", 0, 0, u4);
        Product p14 = new Product("Sandals", "white", 39, 14.35, "Birkenstock", "New", 0, 0, u6);
        Product p15 = new Product("Sunglasses", "black", 0, 29.99, "Ray-Ban", "New", 0, 0, u8);
        Product p16 = new Product("Blazer", "navy", 44, 30.00, "Zara", "Good", 0, 0, u7);
        Product p17 = new Product("Shorts", "beige", 34, 7.49, "Nike", "Good", 0, 0, u3);

        Category categoryTops = new Category(CategoryName.TOPS);
        Category categoryDresses= new Category(CategoryName.DRESSES);
        Category categoryShoes = new Category(CategoryName.FOOTWEAR);
        Category categoryAccessories = new Category(CategoryName.ACCESSORIES);
        Category categoryOuterwear = new Category(CategoryName.OUTERWEAR);
        Category categoryBottoms=new Category(CategoryName.BOTTOMS);
        p1.setCategory(categoryTops);
        p2.setCategory(categoryTops);
        p3.setCategory(categoryShoes);
        p4.setCategory(categoryDresses);
        p5.setCategory(categoryAccessories);
        p7.setCategory(categoryAccessories);
        p8.setCategory(categoryTops);
        p9.setCategory(categoryOuterwear);
        p10.setCategory(categoryShoes);
        p11.setCategory(categoryDresses);
        p12.setCategory(categoryAccessories);
        p13.setCategory(categoryTops);
        p14.setCategory(categoryShoes);
        p15.setCategory(categoryAccessories);
        p16.setCategory(categoryOuterwear);
        p17.setCategory(categoryBottoms);
        productRepo.create(p1);
        productRepo.create(p2);
        productRepo.create(p3);
        productRepo.create(p4);
        productRepo.create(p5);
        productRepo.create(p6);
        productRepo.create(p7);
        productRepo.create(p8);
        productRepo.create(p9);
        productRepo.create(p10);
        productRepo.create(p11);
        productRepo.create(p12);
        productRepo.create(p13);
        productRepo.create(p14);
        productRepo.create(p15);
        productRepo.create(p16);
        productRepo.create(p17);

        Review r1=new Review(4.5,"I liked the service",u3,u1);
        Review r2=new Review(1.5,"Very bad service",u2,u3);
        Review r3 = new Review(3.0, "Average experience, could be better", u1, u4);
        Review r4 = new Review(5.0, "Excellent service, highly recommend!", u4, u5);
        Review r5 = new Review(2.0, "Not satisfied with the response time", u5, u2);
        Review r6 = new Review(4.0, "Good quality, but a bit overpriced", u6, u3);
        Review r7 = new Review(4.8, "Fantastic experience and fast delivery", u7, u1);
        Review r8 = new Review(3.5, "Decent service, but room for improvement", u8, u6);
        Review r9 = new Review(4.2, "Pleasant transaction, will return", u3, u8);
        Review r10 = new Review(2.8, "Delayed delivery but product as described", u2, u5);
        Review r11 = new Review(5.0, "Perfect! Could not be happier with the service", u1, u7);
        Review r12 = new Review(1.0, "Terrible communication, would not recommend", u6, u4);

        reviewRepo.create(r1);
        reviewRepo.create(r2);
        reviewRepo.create(r3);
        reviewRepo.create(r4);
        reviewRepo.create(r5);
        reviewRepo.create(r6);
        reviewRepo.create(r7);
        reviewRepo.create(r8);
        reviewRepo.create(r9);
        reviewRepo.create(r10);
        reviewRepo.create(r11);
        reviewRepo.create(r12);



        AdminService adminService = new AdminService(userRepo, productRepo, reviewRepo, adminRepo, categoryRepo);
        UserService userService=new UserService(userRepo,productRepo,reviewRepo,orderRepo,offerRepo);

        VisitorService visitorService=new VisitorService(userRepo,productRepo,reviewRepo);
        //Category category = p2.getCategory();
       // System.out.println(category.getName());

        List<Product> orderProducts0 = Arrays.asList(p3, p4);
        Order or1=new Order(orderProducts0,"sent","Strada xyz",u3);

        List<Product> orderProducts1 = Arrays.asList(p1, p5);
        Order or2 = new Order(orderProducts1, "delivered", "Strada ABC", u2); // Review r2

        List<Product> orderProducts2 = List.of(p2);
        Order or3 = new Order(orderProducts2, "delivered", "Strada DEF", u5); // Review r5

        List<Product> orderProducts3 = Arrays.asList(p3, p6);
        Order or4 = new Order(orderProducts3, "shipped", "Strada GHI", u6); // Review r6

        List<Product> orderProducts4 = Arrays.asList(p1, p4);
        Order or5 = new Order(orderProducts4, "delivered", "Strada JKL", u7); // Review r7

        List<Product> orderProducts5 = Arrays.asList(p2, p5);
        Order or6 = new Order(orderProducts5, "shipped", "Strada MNO", u8); // Review r8

        List<Product> orderProducts6 = List.of(p3);
        Order or7 = new Order(orderProducts6, "delivered", "Strada PQR", u3); // Review r9

        List<Product> orderProducts7 = Arrays.asList(p4, p5);
        Order or8 = new Order(orderProducts7, "sent", "Strada STU", u1); // Review r11

        List<Product> orderProducts8 = List.of(p6);
        Order or9 = new Order(orderProducts8, "shipped", "Strada VWX", u6); // Review r12

        orderRepo.create(or1);
        orderRepo.create(or2);
        orderRepo.create(or3);
        orderRepo.create(or4);
        orderRepo.create(or5);
        orderRepo.create(or6);
        orderRepo.create(or7);
        orderRepo.create(or8);
        orderRepo.create(or9);

        userService.placeOrder(u1,or9);
        userService.placeOrder(u2, or3);
        userService.placeOrder(u1, or4);
        userService.placeOrder(u1, or5);
        userService.placeOrder(u3, or6);
        userService.placeOrder(u1, or7);
        userService.placeOrder(u3, or8);
        userService.placeOrder(u3, or9);



        //System.out.println(userService.placeOrder(u1,or1));
        //System.out.println(or1.getTotalPrice());
        //System.out.println(userService.writeReview(r1));
        //System.out.println(userService.displayOrders(u3.getUserName(),u3.getPassword()));







       // System.out.println(adminService.updateCategory(a1.getId(),p2.getId(),newCategory));
        //Category category2 = p2.getCategory();
        //System.out.println(category2.getName());



        //System.out.println(visitorService.searchProductsByUsername(u2.getUserName()));
        Offer o1 = new Offer("Would you consider selling this for 7.00?", 7.00, p1, false, u2, u1);
        Offer o2 = new Offer("Can I get this for 9.50?", 9.50, p2, false, u3, u2);
        Offer o3 = new Offer("Would you accept 10.00?", 10.00, p3, false, u4, u1);
        Offer o4 = new Offer("How about 12.00 for this item?", 12.00, p4, false, u2, u5);
        Offer o5 = new Offer("Would you consider selling this for 7.50?", 7.50, p5, false, u3, u6);
        Offer o6 = new Offer("Is 8.00 okay for this product?", 8.00, p6, false, u4, u3);
        Offer o7 = new Offer("Could we settle on 8.75?", 8.75, p1, false, u7, u1);
        Offer o8 = new Offer("I would like to offer 11.00.", 11.00, p2, false, u8, u5);
        Offer o9 = new Offer("Is 6.50 a fair price?", 6.50, p3, false, u1, u2);
        Offer o10 = new Offer("Can you do 13.00 for this?", 13.00, p4, false, u8, u1);
        Offer o11 = new Offer("I can pay 7.25, would that work?", 7.25, p5, false, u2, u6);
        Offer o12 = new Offer("Would you accept 19.99 for these sneakers?", 19.99, p10, false, u3, u7);
// Save the offers to the repository
        offerRepo.create(o1);
        offerRepo.create(o2);
        offerRepo.create(o3);
        offerRepo.create(o4);
        offerRepo.create(o5);
        offerRepo.create(o6);
        offerRepo.create(o7);
        offerRepo.create(o8);
        offerRepo.create(o9);
        offerRepo.create(o10);
        offerRepo.create(o11);
        offerRepo.create(o12);
        //System.out.println(userService.sendOffer(u1,u2.getId(),o1,p1.getId()));
       // System.out.println(userService.acceptOffer(u2.getId(),o1));



        Visitor v1 = new Visitor();
        //VisitorService visitorService = new VisitorService(userRepo, productRepo, reviewRepo);
        System.out.println(visitorService.createAccount("ana", "123", "blabla", "030492"));


        //System.out.println(reviewRepo.getAll());
//        System.out.println(a1.deleteReview(r2));
//        System.out.println(reviewRepo.getAll());

//        System.out.println(userRepo.getAll());
//           adminService.deleteUser(a1.userName, a1.password, u1.getUserName());
//        System.out.println(userRepo.getAll());












    }


}
