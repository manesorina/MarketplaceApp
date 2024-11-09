import java.util.List;

public class Controller {
    protected final AdminService adminService;
    protected final UserService userService;
    protected final VisitorService visitorService;

    public Controller(AdminService adminService, UserService userService, VisitorService visitorService) {
        this.adminService = adminService;
        this.userService = userService;
        this.visitorService = visitorService;
    }

    public boolean createAccount(String username, String password, String email, String phoneNumber) {
        if (username != null && password != null && email != null && phoneNumber != null && phoneNumber.length() == 10) {
            visitorService.createAccount(username, password, email, phoneNumber);
            return true;
        }
        else return false;
    }

    public int logIn(String username, String password) {
        if (username != null && password != null) {
            boolean isAdmin = adminService.authenticate(username, password);
            boolean isUser = userService.authenticate(username, password);
            if (isUser)
                return 1;
            if (isAdmin)
                return 2;
        }
        return 0;
    }
}
