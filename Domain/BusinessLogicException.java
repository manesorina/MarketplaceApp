package Domain;

public class BusinessLogicException extends CustomException{
    public BusinessLogicException(String message){
        super(message,"BUSINESS_LOGIC_ERROR");
    }
}
