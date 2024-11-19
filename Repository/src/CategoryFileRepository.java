public class CategoryFileRepository extends FMRepository<Category>{

    public CategoryFileRepository(String filename){
        super(filename);
    }

    @Override
    protected String convertObjectToString(Category category) {
        return category.getId()+ "," +
                category.getName();
    }


    @Override
    protected Category createObjectFromString(String line) {
        String[] parts=line.split(",");
        int id=Integer.parseInt(parts[0]);
        CategoryName name=CategoryName.valueOf(parts[1]);

        return new Category(name);
    }
}
