package wf.utils.command.listener;


import wf.utils.command.model.CommandSender;
import wf.utils.command.subcommand.SubCommand;

import java.util.*;


public class CommandHandler<T> {


    private HashMap<String, SubCommand<T>> subcommands = new HashMap<>();
    private final TreeMap<String, SubCommand<T>> sortedSubcommands = new TreeMap<>((str1, str2) -> str1.compareTo(str2) * -1);


    public CommandHandler() {

    }

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


    public List<String> onTabComplete(String input, CommandSender sender) {
        if (input == null) return Collections.emptyList();
        if (input.isBlank()) return Collections.emptyList();
        String[] words = input.split(" ");

        if (words.length == 1)
            return Collections.emptyList();

        String[] args = Arrays.copyOfRange(words, 1, words.length);


        List<String> tabulation = new ArrayList<>();

        for (Map.Entry<String, SubCommand<T>> entry : subcommands.entrySet()) {
            if (!entry.getValue().checkPermission(sender)) continue;

            String[] subcommandArgs = entry.getKey().split("\\.");

            if (args.length < 2) {
                tabulation.add(subcommandArgs[0]);
            } else {
                boolean isIt = true;
                int max = Math.min(args.length - 1, subcommandArgs.length);
                for (int i = 0; i < max; i++)
                    if (!subcommandArgs[i].equalsIgnoreCase(args[i])) {
                        isIt = false;
                        break;
                    }
                if (!isIt) continue;

                if (args.length <= subcommandArgs.length) {
                    tabulation.add(subcommandArgs[args.length - 1]);
                } else if (args.length <= subcommandArgs.length + entry.getValue().getSubCommandExecutor().getArguments().length) {
                    return entry.getValue().getSubCommandExecutor().getArguments()[args.length - subcommandArgs.length - 1]
                            .getType().tabulation(sender, args, args[args.length - 1]);
                }

            }
        }

        return tabulation;
    }


    public void addSubcommand(SubCommand<T> subcommand) {
        subcommand.getSubCommandExecutor().setCommand(String.join(" ", subcommand.getCommand().split("\\.")));
        subcommands.put(subcommand.getCommand(), subcommand);
        sortedSubcommands.put(subcommand.getCommand(), subcommand);
    }


    public Map<String, SubCommand<T>> getSubcommands() {
        return Collections.unmodifiableMap(subcommands);
    }

    public void setSubcommands(HashMap<String, SubCommand<T>> subcommands) {
        this.subcommands = subcommands;
        sortedSubcommands.putAll(subcommands);
    }

    public Map<String, SubCommand<T>> getSortedSubcommands() {
        return Collections.unmodifiableMap(sortedSubcommands);
    }


}












