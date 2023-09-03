package it.craftopoly.co_auth.listener;

import it.craftopoly.co_auth.CO_Auth;
import it.craftopoly.co_auth.utils.HttpCall;
import it.craftopoly.co_auth.utils.Utils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.scheduler.BukkitRunnable;

public class QuitEvent implements Listener {


    @EventHandler
    public void onQuit(PlayerQuitEvent e)
    {
        Player player = e.getPlayer();

        CO_Auth.guests.remove(player.getUniqueId());
    }

}
