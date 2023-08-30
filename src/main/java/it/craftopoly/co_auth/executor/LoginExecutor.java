package it.craftopoly.co_auth.executor;

import it.craftopoly.co_auth.CO_Auth;
import it.craftopoly.co_auth.utils.HttpCall;
import it.craftopoly.co_auth.utils.Message;
import it.craftopoly.co_auth.utils.Utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LoginExecutor implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        Player player = (Player) sender;

        if (cmd.getName().equals("login"))
            if(CO_Auth.guests.contains(player.getUniqueId()))
            {
                if (args.length == 1)
                {
                    String password = args[0];
                    String response = HttpCall.login(player.getName(), password);

                    if (Utils.isSuccess(response))
                        CO_Auth.guests.remove(player.getUniqueId());

                    player.sendMessage(response);
                    return false;
                }
            }else{
                player.sendMessage(Message.ALREADY_AUTHENTICATED);
            }
        return false;
    }
}


