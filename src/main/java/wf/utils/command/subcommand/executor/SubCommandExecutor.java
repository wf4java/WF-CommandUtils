package wf.utils.command.subcommand.executor;


import wf.utils.command.model.CommandSender;
import wf.utils.command.subcommand.executor.types.MultiArgument;


import java.util.Arrays;

public class SubCommandExecutor {

    private Argument[] arguments;
    private boolean lastArgumentMulti = false;
    private String command = "/command";


    public SubCommandExecutor(Argument... arguments) {
        this.arguments = arguments;
        this.lastArgumentMulti = this.arguments.length != 0 && (this.arguments[this.arguments.length - 1].getType() instanceof MultiArgument);
    }

    public SubCommandExecutor() {
        this.arguments = new Argument[0];
    }

    public SubCommandExecutor(String command, Argument... arguments) {
        this.command = command;
        this.arguments = arguments != null ? arguments : new Argument[0];
        this.lastArgumentMulti = this.arguments.length != 0 && (this.arguments[this.arguments.length - 1].getType() instanceof MultiArgument);
    }


    public Object[] calculate(CommandSender sender, String[] args, int argsOffSet){
        return calculate(sender, argsOffSet == 0 ? args : Arrays.copyOfRange(args, argsOffSet, args.length));
    }


    public Object[] calculate(CommandSender sender, String[] args){
        if(getMinObligatorilyArgs() > args.length){
            sender.sendMessage("Write all arguments!" + "\n" + getArgumentsText());
            return null;
        }

        Object[] objects = new Object[lastArgumentMulti ? args.length : arguments.length];

        for(int i = 0; i < (lastArgumentMulti ? args.length : arguments.length); i++){
            Argument argument = lastArgumentMulti ? (arguments.length <= i ? arguments[arguments.length - 1] : arguments[i]) : arguments[i];
            if(args.length > i){
                if(!argument.typeIsRight(sender, args, args[i])){
                    sender.sendMessage(argument.getType().getMessage() + "\n" + getWrongArgumentText(i));
                    return null;
                }
                objects[i] = argument.get(sender, args, args[i]);
            }else {
                objects[i] = argument.getDefault();
            }
        }

        return objects;

    }


    public String getWrongArgumentText(int num){
        StringBuilder builder = new StringBuilder(command);
        for(int i = 0; i < arguments.length; i++)
            builder.append(" {")
                    .append(i == num ? "R:" : ( i > num ? "Y:" : "G:" ))
                    .append(!arguments[i].isObligatorily() ? "@" : "")
                    .append(arguments[i].getName())
                    .append("}");

        return builder.toString();
    }

    public String getArgumentsText(){
        StringBuilder text = new StringBuilder(command);
        for(Argument argument : arguments)
            text.append(" {").append(!argument.isObligatorily() ? "@" : "").append(argument.getName()).append("}");

        return text.toString();
    }

    public int getMinObligatorilyArgs(){
        return getMinObligatorilyArgs(arguments);
    }

    public static int getMinObligatorilyArgs(Argument[] arguments){
        int min = 0;

        for(int i = 0; i < arguments.length; i++){
            if(!arguments[i].isObligatorily()) break;
            min = i + 1;
        }

        return min;
    }


    public Argument[] getArguments() {
        return arguments;
    }

    public void setArguments(Argument[] arguments) {
        this.arguments = arguments;
        this.lastArgumentMulti = this.arguments.length != 0 && (this.arguments[this.arguments.length - 1].getType() instanceof MultiArgument);
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return "CommandBuilder{" +
                "arguments=" + Arrays.toString(arguments) +
                ", command='" + command + '\'' +
                '}';
    }
}

