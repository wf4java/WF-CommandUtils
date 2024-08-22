
import wf.utils.command.listener.StringCommandHandler;
import wf.utils.command.subcommand.SubCommand;
import wf.utils.command.subcommand.executor.Argument;
import wf.utils.command.subcommand.executor.types.ArgumentType;

public class Test {


    public static void main(String[] args) {
        StringCommandHandler commandHandler = new StringCommandHandler();

        commandHandler.addSubcommand(
                SubCommand.builder(String.class)
                        .setCommand("set.data")
                        .setArguments(
                                new Argument("number", ArgumentType.INTEGER),
                                new Argument("boolean", ArgumentType.BOOLEAN, false, true)
                        )
                        .setRunnable((sender, command, result) -> {
                            System.out.println("Result: " + result[0]);
                            System.out.println("Result: " + result[1]);
                        })
                        .build()
        );


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


        commandHandler.onCommand("set data 5", message -> System.out.println("Error: " + message));
        commandHandler.onCommand("remove data 4", message -> System.out.println("Error: " + message));
        commandHandler.onCommand("remove data false", message -> System.out.println("Error: " + message));

    }


}
