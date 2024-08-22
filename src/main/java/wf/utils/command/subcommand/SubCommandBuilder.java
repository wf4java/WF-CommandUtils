package wf.utils.command.subcommand;



import wf.utils.command.model.CommandSender;
import wf.utils.command.model.TriConsumer;
import wf.utils.command.subcommand.executor.Argument;


public class SubCommandBuilder<T> {

    private String command;
    private String permission;

    private TriConsumer<CommandSender, T, Object[]> runnable;
    private Argument[] arguments;


    public SubCommandBuilder<T> setPermission(String permission) {
        this.permission = permission;
        return this;
    }

    public SubCommandBuilder<T> setRunnable(TriConsumer<CommandSender, T, Object[]> runnable) {
        this.runnable = runnable;
        return this;
    }



    public SubCommandBuilder<T> setArguments(Argument... arguments) {
        this.arguments = arguments;
        return this;
    }

    public SubCommandBuilder<T> setCommand(String command) {
        this.command = command;
        return this;
    }

    public SubCommand<T> build() {
        return new SubCommand(command, permission, arguments, runnable);
    }

}
