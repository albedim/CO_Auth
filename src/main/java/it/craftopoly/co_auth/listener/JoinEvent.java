package it.craftopoly.co_auth.listener;

import it.craftopoly.co_auth.CO_Auth;
import it.craftopoly.co_auth.utils.HttpCall;
import it.craftopoly.co_auth.utils.HttpRequest;
import it.craftopoly.co_auth.utils.Message;

import it.craftopoly.co_auth.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.io.IOException;

public class JoinEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        Player player = e.getPlayer();

        CO_Auth.guests.add(player.getUniqueId());
        Boolean exists = HttpCall.exists(player.getName());

        if(exists)
        {
            try {
                if (Utils.isUsernamePremium(player.getName())) {
                    player.sendMessage(Message.PREMIUM_AUTHENTICATED);
                    CO_Auth.guests.remove(player.getUniqueId());
                }else{
                    player.sendMessage(Message.LOGIN.replace("{username}", player.getName()));
                }
            } catch (IOException exc) {
                player.kickPlayer(exc.getMessage());
            }
        }else{
            player.sendMessage(Message.AUTHENTICATE.replace("{username}", player.getName()));
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e)
    {
        e.setCancelled(CO_Auth.guests.contains(e.getPlayer().getUniqueId()));
    }

    @EventHandler
    public void onAsyncChat(AsyncPlayerChatEvent e)
    {
        e.setCancelled(CO_Auth.guests.contains(e.getPlayer().getUniqueId()));
    }


}
