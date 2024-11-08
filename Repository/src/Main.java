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



        //repo.delete(1);



        Admin a1=new Admin("JohnDoe","qwerty1234","johnedoe@email.com","0747896547");
        Admin a2=new Admin("JaneSmith","1234abc","janesmith@email.com","0748596321");
        adminRepo.create(a1);
        adminRepo.create(a2);

        //System.out.println(a1.authenticate("johnDoe","qwerty1234"));
        //System.out.print(a2.authenticate("johnDoe","1234"));
        //List<Product> favourites= Arrays.asList({})

        User u1= new User("MikeHouse","1q2w3e4r","mikehouse@gmail.com","0748996332",0);
        User u2= new User("LisaTeak","xyz987","lisateak@gmail.com","0747558114",4.5);
        User u3= new User("TinaSilver","x1y2z3","tinasilver@gmail.com","0758669327",0);
        userRepo.create(u1);
        userRepo.create(u2);
        userRepo.create(u3);

        //System.out.println(userRepo.getAll());

        Product p1=new Product("TShirt","blue",36,8.99,"Nike","Worn",0,0,u1);
        Product p2=new Product("Pants","black",38,10.99,"Nike","Worn",0,0,u2);
        Product p3=new Product("Shoes","yellow",40,11.45,"Puma","New",0,0,u1);
        Product p4=new Product("Dress","pink",40,15.23,"H&M","Good",0,0,u1);

        Category categoryTops = new Category(CategoryName.TOPS);
        Category categoryDresses= new Category(CategoryName.DRESSES);
        Category categoryShoes = new Category(CategoryName.FOOTWEAR);
        p1.setCategory(categoryTops);
        p2.setCategory(categoryTops);
        p3.setCategory(categoryShoes);
        p4.setCategory(categoryDresses);
        productRepo.create(p1);
        productRepo.create(p2);
        productRepo.create(p3);
        productRepo.create(p4);

        Review r1=new Review(4.5,"I liked the service",u3,u1);
        Review r2=new Review(1.5,"Very bad service",u2,u3);
        reviewRepo.create(r1);
        reviewRepo.create(r2);



        AdminService adminService = new AdminService(userRepo, productRepo, reviewRepo, adminRepo);
        UserService userService=new UserService(userRepo,productRepo,reviewRepo,orderRepo,offerRepo);

        VisitorService visitorService=new VisitorService(userRepo,productRepo,reviewRepo);
        Category category = p2.getCategory();
       // System.out.println(category.getName());

        List<Product> orderProducts = Arrays.asList(p3, p4);
        Order or1=new Order(orderProducts,"sent","Strada xyz",u3);

        System.out.println(userService.placeOrder(u1,or1));
        //System.out.println(or1.getTotalPrice());
        System.out.println(userService.writeReview(r1));
        System.out.println(userService.displayOrders(u3.getId()));





        Category newCategory = new Category(CategoryName.OUTERWEAR);

       // System.out.println(adminService.updateCategory(a1.getId(),p2.getId(),newCategory));
        //Category category2 = p2.getCategory();
        //System.out.println(category2.getName());



        //System.out.println(visitorService.searchProductsByUsername(u2.getUserName()));
        Offer o1=new Offer("Would you sell he product with 7.00? ",7.00,p1,null);
        offerRepo.create(o1);
        //System.out.println(userService.sendOffer(u1,u2.getId(),o1,p1.getId()));
       // System.out.println(userService.acceptOffer(u2.getId(),o1));



        Visitor v1 = new Visitor();
        //VisitorService visitorService = new VisitorService(userRepo, productRepo, reviewRepo);
        System.out.println(visitorService.createAccount("ana", "123", "blabla", "030492"));


        //System.out.println(reviewRepo.getAll());
//        System.out.println(a1.deleteReview(r2));
//        System.out.println(reviewRepo.getAll());

//        System.out.println(userRepo.getAll());
//        adminService.deleteUser(a1.getId(), a1.userName, a1.password, u1.getId());
//        System.out.println(userRepo.getAll());












    }


}
