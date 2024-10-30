public class Visitor {
    public int visitorID;

    public Visitor(int visitorID) {
        this.visitorID = visitorID;
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


}
