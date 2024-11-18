public class VisitorFileRepository extends FMRepository<Visitor>{

    public VisitorFileRepository(String filename){
        super(filename);
    }

    public String convertObjectToString(Visitor visitor ){
        return Integer.toString(visitor.getId());
    }

    public Visitor createObjectFromString(String line){
        String[] parts=line.split(",");
        int id=Integer.parseInt(parts[0]);

        Visitor visitor=new Visitor();
        visitor.setId(id);
        return visitor;
    }
}
