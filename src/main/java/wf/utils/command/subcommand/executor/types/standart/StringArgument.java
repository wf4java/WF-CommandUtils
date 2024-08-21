package wf.utils.command.subcommand.executor.types.standart;




import wf.utils.command.model.CommandSender;
import wf.utils.command.subcommand.executor.types.ArgumentType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringArgument implements ArgumentType {

    private String name;

    public StringArgument() { }

    public StringArgument(String name) {
        this.name = name;
    }


    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public String getMessageCode() {
        return null;
    }

    @Override
    public String getName() {
        return "string";
    }

    @Override
    public boolean isIt(CommandSender sender, String[] args, String argument) {
        return true;
    }

    @Override
    public Object get(CommandSender sender, String[] args, String argument) {
        return argument;
    }

    @Override
    public List<String> tabulation(CommandSender sender, String[] args, String arg) {
        if(name == null) return new ArrayList<>(0);
        return List.of(name);
    }


}
