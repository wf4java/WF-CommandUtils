package wf.utils.command.subcommand.executor.types.standart;



import wf.utils.command.model.CommandSender;
import wf.utils.command.utils.TypeUtils;
import wf.utils.command.subcommand.executor.types.ArgumentType;

import java.util.Arrays;
import java.util.List;

public class DoubleArgument implements ArgumentType {

    @Override
    public String getMessage() {
        return "This argument is not valid, enter an number!";
    }

    @Override
    public String getMessageCode() {
        return "COMMAND.DEFAULT.ARGUMENT.DOUBLE_ARGUMENT_WRONG";
    }

    @Override
    public String getName() {
        return "double";
    }

    @Override
    public boolean isIt(CommandSender sender, String[] args, String argument) {
        return TypeUtils.isDouble(argument);
    }

    @Override
    public Object get(CommandSender sender, String[] args, String argument) {
        return Double.parseDouble(argument);
    }

    @Override
    public List<String> tabulation(CommandSender sender, String[] args, String arg) {
        return Arrays.asList("0.0", "1.0", "5.0", "10.0");
    }

}
