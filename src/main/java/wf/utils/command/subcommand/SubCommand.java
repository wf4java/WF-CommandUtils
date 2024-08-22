package wf.utils.command.subcommand;


import wf.utils.command.model.CommandSender;
import wf.utils.command.model.TriConsumer;
import wf.utils.command.subcommand.executor.Argument;
import wf.utils.command.subcommand.executor.SubCommandExecutor;

public class SubCommand<T> {

    private String command;
    private String permission;
    private SubCommandExecutor subCommandExecutor;
    private TriConsumer<CommandSender, T, Object[]> runnable;


    public SubCommand() {

    }

    public SubCommand(String command, String permission, Argument[] arguments, TriConsumer<CommandSender, T, Object[]> runnable) {
        this.command = command;
        this.permission = permission;
        this.runnable = runnable;
        this.subCommandExecutor = new SubCommandExecutor(command, arguments);
    }


    public void onCommand(CommandSender sender, T t, String[] args, int argsPosition) {
        if (!checkPermission(sender)) {
            sender.sendMessage("You not have permission!");
            return;
        }

        Object[] output = subCommandExecutor.calculate(sender, args, argsPosition);
        if (output == null) return;
        runnable.accept(sender, t, output);
    }


    public boolean checkPermission(CommandSender sender) {
        if (permission == null) return true;
        if (sender.isOp() || sender.hasPermission("*")) return true;
        return sender.hasPermission(permission);

    }


    public static <T> SubCommandBuilder<T> builder(Class<T> type) {
        return new SubCommandBuilder<>();
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public SubCommandExecutor getSubCommandExecutor() {
        return subCommandExecutor;
    }

    public void setSubCommandExecutor(SubCommandExecutor subCommandExecutor) {
        this.subCommandExecutor = subCommandExecutor;
    }

    public TriConsumer<CommandSender, T, Object[]> getRunnable() {
        return runnable;
    }

    public void setRunnable(TriConsumer<CommandSender, T, Object[]> runnable) {
        this.runnable = runnable;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return "Subcommand{" +
                "permission=" + permission +
                ", commandBuilder=" + subCommandExecutor +
                ", runnable=" + runnable +
                '}';
    }
}
