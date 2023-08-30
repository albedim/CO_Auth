package it.craftopoly.co_auth;

import it.craftopoly.co_auth.executor.LoginExecutor;
import it.craftopoly.co_auth.executor.RegisterExecutor;
import it.craftopoly.co_auth.listener.JoinEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class CO_Auth extends JavaPlugin {

    public static ArrayList<Object> guests = new ArrayList<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("login").setExecutor(new LoginExecutor());
        getCommand("register").setExecutor(new RegisterExecutor());
        Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
