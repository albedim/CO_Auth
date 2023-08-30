package it.craftopoly.co_auth.executor;

import it.craftopoly.co_auth.CO_Auth;
import it.craftopoly.co_auth.utils.HttpCall;
import it.craftopoly.co_auth.utils.Utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RegisterExecutor implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        if (cmd.getName().equals("register"))
            if (args.length == 1)
            {
                Player player = (Player) sender;
                String password = args[0];
                String response = HttpCall.create(player.getName(), password);

                if (Utils.isSuccess(response))
                    CO_Auth.guests.remove(player.getUniqueId());

                player.sendMessage(response);
                return false;

            }
        return false;
    }
}


