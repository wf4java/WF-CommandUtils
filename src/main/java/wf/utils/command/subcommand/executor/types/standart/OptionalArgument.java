package wf.utils.command.subcommand.executor.types.standart;


import wf.utils.command.model.CommandSender;
import wf.utils.command.subcommand.executor.types.ArgumentType;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OptionalArgument implements ArgumentType {


    private final Set<String> options = new HashSet<>();

    public OptionalArgument(Class<? extends Enum<?>> enumClass) {
        for(Enum<?> e : enumClass.getEnumConstants())
            options.add(e.name());
    }

    public OptionalArgument(Collection<String> options) {
        this.options.addAll(options);
    }

    @Override
    public String getMessage() {
        return "This argument is not valid, select one of the options(" + String.join(",", options) + ")!";
    }

    @Override
    public String getMessageCode() {
        return "COMMAND.DEFAULT.ARGUMENT.OPTIONAL_ARGUMENT_WRONG";
    }

    @Override
    public String getName() {
        return "option";
    }

    @Override
    public boolean isIt(CommandSender sender, String[] args, String argument) {
        return options.contains(argument);
    }

    @Override
    public Object get(CommandSender sender, String[] args, String argument) {
        return argument;
    }

    @Override
    public List<String> tabulation(CommandSender sender, String[] args, String arg) {
        return options.stream().toList();
    }
}
