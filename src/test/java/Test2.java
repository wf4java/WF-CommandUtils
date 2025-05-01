
import wf.utils.command.listener.CommandSameHandler;
import wf.utils.command.listener.StringCommandHandler;
import wf.utils.command.subcommand.SubCommand;
import wf.utils.command.subcommand.executor.Argument;
import wf.utils.command.subcommand.executor.types.ArgumentType;

public class Test2 {


    public static void main(String[] args) {
        CommandSameHandler<String> commandHandler = new CommandSameHandler<>();


        commandHandler.addSubcommand(
                SubCommand.builder(String.class)
                        .setCommand("remove.data")
                        .setArguments(
                                new Argument("number", ArgumentType.INTEGER)
                        )
                        .setRunnable((sender, command, result) -> {
                            System.out.println("Result: " + result[0]);
                        })
                        .build()
        );


        commandHandler.onCommand("remove datt 1", message -> System.out.println("Error: " + message), null);
        commandHandler.onCommand("remove asdd 2", message -> System.out.println("Error: " + message), null);
        commandHandler.onCommand("rdmove data 3", message -> System.out.println("Error: " + message), null);
        commandHandler.onCommand("rdmove date 4", message -> System.out.println("Error: " + message), null);

        //Result: 1
        //Error: Command not found!
        //Result: 3
        //Result: 4
    }


}
