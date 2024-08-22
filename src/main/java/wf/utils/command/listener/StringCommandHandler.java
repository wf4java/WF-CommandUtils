package wf.utils.command.listener;

import wf.utils.command.model.CommandSender;

public class StringCommandHandler extends CommandHandler<String> {


    public boolean onCommand(String input, CommandSender sender) {
        return super.onCommand(input, sender, input);
    }

}
