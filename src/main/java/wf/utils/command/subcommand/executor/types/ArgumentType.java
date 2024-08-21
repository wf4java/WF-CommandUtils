package wf.utils.command.subcommand.executor.types;




import wf.utils.command.model.CommandSender;
import wf.utils.command.subcommand.executor.types.standart.*;

import java.util.Collection;
import java.util.List;

public interface ArgumentType {

    public static final ArgumentType STRING = new StringArgument();
    public static final ArgumentType DOUBLE = new DoubleArgument();
    public static final ArgumentType INTEGER = new IntegerArgument();
    public static final ArgumentType LONG = new LongArgument();
    public static final ArgumentType BOOLEAN = new BooleanArgument();
    public static final ArgumentType MULTI_STRING = new MultiStringArgument();


    public static ArgumentType ofEnum(Class<? extends Enum<?>> enumClass) {
        return new OptionalArgument(enumClass);
    }

    public static ArgumentType ofOptions(Collection<String> options) {
        return new OptionalArgument(options);
    }


    public String getMessage();
    public String getMessageCode();
    public String getName();
    public boolean isIt(CommandSender sender, String[] args, String argument);
    public Object get(CommandSender sender, String[] args, String argument);
    public List<String> tabulation(CommandSender sender, String[] args, String argument);


}


