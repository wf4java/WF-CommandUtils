package wf.utils.command.subcommand.executor.types.standart;


import wf.utils.command.model.CommandSender;
import wf.utils.command.subcommand.executor.types.ArgumentType;
import wf.utils.command.utils.TypeUtils;

import java.util.Arrays;
import java.util.List;

public class LongArgument implements ArgumentType {

    @Override
    public String getMessage() {
        return "This argument is not valid, enter an number(long integer)!";
    }

    @Override
    public String getMessageCode() {
        return "COMMAND.DEFAULT.ARGUMENT.LONG_ARGUMENT_WRONG";
    }

    @Override
    public String getName() {
        return "long_integer";
    }

    @Override
    public boolean isIt(CommandSender sender, String[] args, String argument) {
        return TypeUtils.isInteger(argument);
    }

    @Override
    public Object get(CommandSender sender, String[] args, String argument) {
        return Long.parseLong(argument);
    }

    @Override
    public List<String> tabulation(CommandSender sender, String[] args, String arg) {
        return Arrays.asList("0", "1", "5", "10");
    }
}
