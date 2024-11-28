package Domain;

public class ValidationException extends CustomException{
    public ValidationException(String message){
        super(message,"VALIDATION_ERROR");
    }

}
