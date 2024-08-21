package wf.utils.command.subcommand.executor.types.standart;


import wf.utils.command.model.CommandSender;
import wf.utils.command.utils.TypeUtils;
import wf.utils.command.subcommand.executor.types.ArgumentType;

import java.util.Arrays;
import java.util.List;


public class BooleanArgument implements ArgumentType {


    @Override
    public String getMessage() {
        return "This argument is not valid, enter true/false!";
    }

    @Override
    public String getMessageCode() {
        return "COMMAND.DEFAULT.ARGUMENT.BOOLEAN_ARGUMENT_WRONG";
    }

    @Override
    public String getName() {
        return "boolean";
    }

    @Override
    public boolean isIt(CommandSender sender, String[] args, String argument) {
        return TypeUtils.isBoolean(argument);
    }

    @Override
    public Object get(CommandSender sender, String[] args, String argument) {
        return Boolean.parseBoolean(argument);
    }

    @Override
    public List<String> tabulation(CommandSender sender, String[] args, String arg) {
        return Arrays.asList("true", "false");
    }
}
