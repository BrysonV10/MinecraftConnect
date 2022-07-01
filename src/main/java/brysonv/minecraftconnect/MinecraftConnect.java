package brysonv.minecraftconnect;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class MinecraftConnect extends JavaPlugin {
    public static FileConfiguration config;
    public static Boolean playerOnJoin;
    public static Boolean playerOnLeave;
    public static Boolean playerOnDeath;
    public static Boolean chat;
    public static Boolean onServerStart;
    public static Boolean onServerEnd;
    public static String webhook;
    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("[MinecraftConnect] Plugin Started");
        getServer().getPluginManager().registerEvents(new Events(), this);
        this.saveDefaultConfig();
        System.out.println("[MinecraftConnect] Config Loaded");
        config = this.getConfig();
        if(config.getString("webhook").equals("replaceme")){
            System.out.println("[MinecraftConnect] Webhook URL is invalid. Please replace default.");
            System.out.println("[MinecraftConnect] Plugin will not successfully run without Webhook URL updated.");
        }
        playerOnJoin = Boolean.parseBoolean(config.getString("playerOnJoin"));
        playerOnLeave = Boolean.parseBoolean(config.getString("playerOnLeave"));
        playerOnDeath = Boolean.parseBoolean(config.getString("playerOnDeath"));
        chat = Boolean.parseBoolean(config.getString("chat"));
        onServerStart = Boolean.parseBoolean(config.getString("onServerStart"));
        onServerEnd = Boolean.parseBoolean(config.getString("onServerEnd"));
        webhook = config.getString("webhook");
        System.out.println("[MinecraftConnect] Setup Complete");
        if(onServerStart){
            System.out.println("[MinecraftConnect] Sending Server Start Message");
            Gson gson = new GsonBuilder().create();
            Map<String, String> items = new HashMap<>();
            items.put("content", "The server has started");
            String message = null;
            message = gson.toJson(items);
            Messenger.send(message);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        if(onServerEnd){
            System.out.println("[MinecraftConnect] Shutdown Starting - Posting Shutdown Message");
            Gson gson = new GsonBuilder().create();
            Map<String, String> items = new HashMap<>();
            items.put("content", "The server is now shutting down.");
            String message = null;
            message = gson.toJson(items);
            Messenger.send(message);
        }

    }
}
