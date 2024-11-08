import java.util.Scanner;

public class Controller {
    protected final AdminService adminService;
    protected final UserService userService;
    protected final VisitorService visitorService;
    private final Scanner scanner;

    public Controller(AdminService adminService, UserService userService, VisitorService visitorService) {
        this.adminService = adminService;
        this.userService = userService;
        this.visitorService = visitorService;
        this.scanner=new Scanner(System.in);
    }

    public int authenticate(String userName, String password) {
        if (userService.authenticate(userName, password)) {
            return 1;
        } else if (adminService.authenticate(userName, password)) {
            return 2;
        }
        return 0; // Authentication failed
    }

}
