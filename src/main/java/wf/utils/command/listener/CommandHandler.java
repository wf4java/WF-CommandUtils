package wf.utils.command.listener;

import wf.utils.command.model.CommandSender;
import wf.utils.command.subcommand.SubCommand;

import java.util.Arrays;
import java.util.Map;

public class CommandHandler<T> extends AbstractCommandHandler<T> {



    public boolean onCommand(String input, CommandSender sender, T t) {
        if (input == null) return false;
        if (input.isBlank()) return false;
        String[] args = input.split(" ");


        for (Map.Entry<String, SubCommand<T>> entry : subcommands.entrySet()) {
            String[] subcommandArgs = entry.getKey().split("\\.");
            String full = String.join(".", Arrays.copyOfRange(args, 0, subcommandArgs.length));
            if (full.equalsIgnoreCase(String.join(".", subcommandArgs))) {
                entry.getValue().onCommand(sender, t, args, subcommandArgs.length);
                return true;
            }
        }

        sender.sendMessage("Command not found!");
        return false;
    }


}
