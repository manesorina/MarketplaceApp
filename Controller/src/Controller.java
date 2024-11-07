public class Controller {
    protected final AdminService adminService;
    protected final UserService userService;
    protected final VisitorService visitorService;

    public Controller(AdminService adminService, UserService userService, VisitorService visitorService) {
        this.adminService = adminService;
        this.userService = userService;
        this.visitorService = visitorService;
    }
}
