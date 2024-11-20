package Repository;

import Domain.Product;

public class ProductFileRepository extends FileRepository<Product> {
    public ProductFileRepository(String filepath){
        super(filepath);

    }

    protected String convertObjectToString(Product product){
        String serialized= product.getId()+ "," +
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

        System.out.println("Serialized product: " + serialized);
        return serialized;
    }


    protected Product createObjectFromString(String line){
        String[] parts=line.split(",");
        int id=Integer.parseInt(parts[0]);
        int category=Integer.parseInt(parts[1]);
        String name=parts[2];
        String color=parts[3];
        int size=Integer.parseInt(parts[4]);
        double price=Double.parseDouble(parts[5]);
        String brand=parts[6];
        String condition=parts[7];
        int nrViews=Integer.parseInt(parts[8]);
        int nrLikes=Integer.parseInt(parts[9]);
        int listedBy=Integer.parseInt(parts[10]);
        boolean isAvailable=Boolean.parseBoolean(parts[11]);

        Product product= new Product(name,color,size,price,brand,condition,nrViews,nrLikes,listedBy);
        product.setCategory(category);
        product.setId(id);
        return product;

    }
}