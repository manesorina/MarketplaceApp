public class NotificationSender {

    int notificationID;
    String message;

    public NotificationSender(int notificationID, String message) {
        this.notificationID = notificationID;
        this.message = message;
    }

    public void sendNotification(String message, User receiver){};
}
