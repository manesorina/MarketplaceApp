public class Visitor implements Identifiable{
    public int id;

    public Visitor() {

    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
