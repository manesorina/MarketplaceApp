package Domain;

public class DatabaseException extends CustomException{
    public DatabaseException(String message){
        super(message,"DATABASE_ERROR");
    }
}
