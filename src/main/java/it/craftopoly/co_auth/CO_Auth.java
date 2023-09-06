package it.craftopoly.co_auth;

import it.craftopoly.co_auth.executor.ConnectTelegramExecutor;
import it.craftopoly.co_auth.executor.DisconnectTelegramExecutor;
import it.craftopoly.co_auth.executor.LoginExecutor;
import it.craftopoly.co_auth.executor.RegisterExecutor;
import it.craftopoly.co_auth.listener.JoinEvent;
import it.craftopoly.co_auth.listener.LoginEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class CO_Auth extends JavaPlugin {


    private static CO_Auth plugin;
    public static ArrayList<Object> guests = new ArrayList<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        saveDefaultConfig();
        getCommand("login").setExecutor(new LoginExecutor());
        getCommand("connect-telegram").setExecutor(new ConnectTelegramExecutor());
        getCommand("disconnect-telegram").setExecutor(new DisconnectTelegramExecutor());
        getCommand("register").setExecutor(new RegisterExecutor());
        Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);
        Bukkit.getPluginManager().registerEvents(new LoginEvent(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static CO_Auth getInstance()
    {
        return plugin;
    }
}
