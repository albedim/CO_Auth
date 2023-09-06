package it.craftopoly.co_auth.executor;

import it.craftopoly.co_auth.CO_Auth;
import it.craftopoly.co_auth.utils.HttpCall;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class DisconnectTelegramExecutor implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        Player player = (Player) sender;

        if (cmd.getName().equals("disconnect-telegram"))
            if(!CO_Auth.guests.contains(player.getUniqueId()))
            {
                String res = HttpCall.disconnectTelegram(player.getUniqueId().toString());
                player.sendMessage(res);
            }else{
                player.sendMessage(CO_Auth.getInstance().getConfig().getString("messages.no_enough_permissions"));
            }
        return false;
    }
}


