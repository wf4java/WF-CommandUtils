package wf.utils.command.subcommand;



import wf.utils.command.model.CommandSender;
import wf.utils.command.model.TriConsumer;
import wf.utils.command.subcommand.executor.Argument;


public class SubCommandBuilder {

    private String command;
    private String permission;

    private TriConsumer<CommandSender, String, Object[]> runnable;
    private Argument[] arguments;


    public SubCommandBuilder setPermission(String permission) {
        this.permission = permission;
        return this;
    }

    public SubCommandBuilder setRunnable(TriConsumer<CommandSender, String, Object[]> runnable) {
        this.runnable = runnable;
        return this;
    }



    public SubCommandBuilder setArguments(Argument... arguments) {
        this.arguments = arguments;
        return this;
    }

    public SubCommandBuilder setCommand(String command) {
        this.command = command;
        return this;
    }

    public SubCommand build() {
        return new SubCommand(command, permission, arguments, runnable);
    }

}
