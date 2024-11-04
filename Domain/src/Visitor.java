public class Visitor implements Identifiable{
    public int id;

    public Visitor() {

    }

    public String[] searchItems(){
        return new String[0];
    };

    public String[] searchUsers(){
        return new String[0];
    };

    public boolean createAccount(String username, String Email, String password, String phone){
        return false;
    };

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
