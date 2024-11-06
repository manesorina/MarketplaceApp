public class Main {
    public static void main(String[] args) {

        Product p1=new Product("blue",36,8.99,"Nike","Worn",0);
        Product p2=new Product("black",38,10.99,"Nike","Worn",0);

        //Object Product;
        IMRepository<Product> repo=new IMRepository<>();
        repo.create(p1);
        repo.create(p2);
        repo.getAll();


        System.out.println(p1.getId());
        System.out.println(p2.getId());

        repo.delete(1);

        System.out.print(repo.getAll());

    }


}
