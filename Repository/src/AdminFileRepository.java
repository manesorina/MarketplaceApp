public class AdminFileRepository extends FMRepository<Admin>{

    public AdminFileRepository(String filename){
        super(filename);
    }

    @Override
    protected String convertObjectToString(Admin admin){
        return admin.getId() + "," +
                admin.getUserName() + "," +
                admin.getPassword() + "," +
                admin.getEmail() + "," +
                admin.getPhone();
    }

    protected Admin createObjectFromString(String line){
        String[] parts=line.split(",");
        int id=Integer.parseInt(parts[0]);
        String username = parts[1];
        String password = parts[2];
        String email = parts[3];
        String phone = parts[4];

        Admin admin=new Admin(username,password,email,phone);
        admin.setId(id);
        return admin;
    }


}
