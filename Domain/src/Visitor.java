public class Visitor {
    public int VId;

    public Visitor(int VId) {
        this.VId = VId;
    }

    public String[] searchItems(){};

    public String[] searchUsers(){};

    public boolean createAccount(String username, String Email, String password, String phone){};


}
