public class NotificationSender implements Identifiable{

    int id;
    String message;

    public NotificationSender(String message) {
        this.message = message;
    }

    public void sendNotification(String message, User receiver){};

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
