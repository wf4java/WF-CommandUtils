package wf.utils.command.model;

public interface CommandSender {


    public void sendMessage(String message);

    public default boolean isOp() {
        return false;
    };

    public default boolean hasPermission(String permission) {
        return false;
    };

}
