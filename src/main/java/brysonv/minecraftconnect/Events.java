package brysonv.minecraftconnect;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;


public class Events implements Listener {
    @EventHandler
    public void OnJoin(PlayerLoginEvent event) {
        if(MinecraftConnect.playerOnJoin){
            System.out.println("Login Event Triggered");
            Gson gson = new GsonBuilder().create();
            Map<String, String> items = new HashMap<>();
            items.put("content", "**" + event.getPlayer().getDisplayName() + "** has joined the server.");
            String message = null;
            message = gson.toJson(items);
            Messenger.send(message);
        }
    }

    @EventHandler
    public void OnLeave(PlayerQuitEvent event){
        if(MinecraftConnect.playerOnLeave){
            Gson gson = new GsonBuilder().create();
            Map<String, String> items = new HashMap<>();
            items.put("content", "**" + event.getPlayer().getDisplayName()+ "** has left the server.");
            String message = null;
            message = gson.toJson(items);
            Messenger.send(message);
        }
    }
    @EventHandler
    public void OnDeath(PlayerDeathEvent event){
        if(MinecraftConnect.playerOnDeath){
            Gson gson = new GsonBuilder().create();
            Map<String, String> items = new HashMap<>();
            items.put("content", event.getDeathMessage());
            String message = null;
            message = gson.toJson(items);
            Messenger.send(message);
        }
    }

    @EventHandler
    public void OnChat(AsyncPlayerChatEvent event){
        if(MinecraftConnect.chat){
            Gson gson = new GsonBuilder().create();
            Map<String, String> items = new HashMap<>();
            items.put("content", event.getPlayer().getDisplayName() + " said: *"+ event.getMessage()+"*");
            String message = null;
            message = gson.toJson(items);
            Messenger.send(message);
        }
    }
}
