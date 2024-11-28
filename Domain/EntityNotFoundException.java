package Domain;

public class EntityNotFoundException extends CustomException{
    public EntityNotFoundException(String message){
        super(message,"ERROR_ENTITY_NOT_FOUND");
    }
}
