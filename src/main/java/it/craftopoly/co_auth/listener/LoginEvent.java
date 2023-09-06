package it.craftopoly.co_auth.listener;

import com.google.gson.JsonObject;
import it.craftopoly.co_auth.CO_Auth;
import it.craftopoly.co_auth.utils.DateUtils;
import it.craftopoly.co_auth.utils.HttpCall;
import it.craftopoly.co_auth.utils.Utils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.scheduler.BukkitRunnable;


public class LoginEvent implements Listener {

    @EventHandler
    public void onLogin(PlayerLoginEvent e)
    {
        Player player = e.getPlayer();
        HttpCall.sync(player.getUniqueId().toString(), player.getName());
        JsonObject ban = HttpCall.getBan(player.getName());
        System.out.println(ban);
        if(ban != null) {
            e.setKickMessage(getBanMessage(ban));
            e.setResult(PlayerLoginEvent.Result.KICK_BANNED);
        }
    }

    private String getBanMessage(JsonObject ban)
    {
        return "§a§lCRAFTOPOLY\n\n" +
                "§7Sei stato bannato!\n\n" +
                "§8 ▪ §7Bannato da: §a"+ban.get("banned_by").getAsJsonObject().get("username").getAsString() + "\n" +
                "§8 ▪ §7Bannato il: §a"+ DateUtils.fixDate(ban.get("banned_on").getAsString().split(" ")[0]) +
                " " + ban.get("banned_on").getAsString().split(" ")[1].split(":")[0] + ":" +
                ban.get("banned_on").getAsString().split(" ")[1].split(":")[1] + "\n" +
                "§8 ▪ §7Fine del ban: §a"+ (
                        !ban.get("ends_on").getAsString().equals("perma") ? (
                                DateUtils.fixDate(ban.get("ends_on").getAsString().split(" ")[0]) +
                                " " + ban.get("ends_on").getAsString().split(" ")[1].split(":")[0] + ":" +
                                ban.get("ends_on").getAsString().split(" ")[1].split(":")[1]
                        ) : "Mai") + "\n" +
                "§8 ▪ §7Motivazione: §a"+ ban.get("reason").getAsString() + "\n\n" +
                "§7Se pensi sia un errore richiedi lo sban\ncreando un ticket sul nostro sito o su discord.\n\n"+
                "§8 ▪ §7§n"+ CO_Auth.getInstance().getConfig().getString("messages.website") + "\n" +
                "§8 ▪ §7§n"+ CO_Auth.getInstance().getConfig().getString("messages.discord");
    }
}
