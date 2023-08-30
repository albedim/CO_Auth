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
                    player.sendMessage(CO_Auth.getInstance().getConfig().getString("messages.premium_authenticated"));
                    CO_Auth.guests.remove(player.getUniqueId());
                }else{
                    new BukkitRunnable() {
                        @Override
                        public void run()
                        {
                            if(!CO_Auth.guests.contains(player.getUniqueId()))
                                return;
                            player.sendMessage("§8-------------------------------------");
                            Utils.sendMessage(
                                    player,
                                    CO_Auth.getInstance().getConfig().getString("messages.login")
                                            .replace("{username}", player.getName())
                            );
                            player.sendMessage("§8-------------------------------------");
                            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 300, 300);
                        }
                    }.runTaskTimer(CO_Auth.getInstance(), 0, 740);
                }
            } catch (IOException exc) {
                player.kickPlayer(exc.getMessage());
            }
        }else{
            new BukkitRunnable() {
                @Override
                public void run()
                {
                    if(!CO_Auth.guests.contains(player.getUniqueId()))
                        return;

                    player.sendMessage("§8-------------------------------------");
                    Utils.sendMessage(
                            player,
                            CO_Auth.getInstance().getConfig().getString("messages.authenticate")
                                    .replace("{username}", player.getName())
                    );
                    player.sendMessage("§8-------------------------------------");
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 300, 300);
                }
            }.runTaskTimer(CO_Auth.getInstance(), 0, 740);
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e)
    {
        e.setCancelled(CO_Auth.guests.contains(e.getPlayer().getUniqueId()));
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInteract(PlayerInteractEvent e)
    {
        e.setCancelled(CO_Auth.guests.contains(e.getPlayer().getUniqueId()));
    }

    @EventHandler
    public void onAsyncChat(AsyncPlayerChatEvent e)
    {
        e.setCancelled(CO_Auth.guests.contains(e.getPlayer().getUniqueId()));
    }


}
