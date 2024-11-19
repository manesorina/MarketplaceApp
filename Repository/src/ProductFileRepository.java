public class ProductFileRepository extends FMRepository<Product>{

    public ProductFileRepository(String filepath){
        super(filepath);

    }

    protected String convertObjectToString(Product product){
        return product.getId()+ "," +
                product.getCategory()+ "," +
                product.getName()+ "," +
                product.getColor()+ "," +
                product.getSize()+ "," +
                product.getPrice()+ "," +
                product.getBrand()+ "," +
                product.getCondition()+ "," +
                product.getNrViews()+ "," +
                product.getNrLikes()+ "," +
                product.getListedBy()+ "," +
                product.isAvailable();
    }


    protected Product createObjectFromString(String line){
        String[] parts=line.split(",");
        int id=Integer.parseInt(parts[0]);
        //CategoryName categoryName=CategoryName.valueOf(parts[1].toUpperCase());
        //Category category=new Category(categoryName);
        String name=parts[1];
        String color=parts[2];
        int size=Integer.parseInt(parts[3]);
        double price=Double.parseDouble(parts[4]);
        String brand=parts[5];
        String condition=parts[6];
        int nrViews=Integer.parseInt(parts[7]);
        int nrLikes=Integer.parseInt(parts[8]);
        int listedBy=Integer.parseInt(parts[9]);
        boolean isAvailable=Boolean.parseBoolean(parts[10]);

        Product product= new Product(name,color,size,price,brand,condition,nrViews,nrLikes,listedBy);
        //product.setCategory(category.getId());
        return product;

    }
}