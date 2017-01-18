package me.unraveledmc.unraveledmcmod;

import me.unraveledmc.unraveledmcmod.config.ConfigEntry;
import me.unraveledmc.unraveledmcmod.util.FUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerPing extends FreedomService
{

    public ServerPing(UnraveledMCMod plugin)
    {
        super(plugin);
    }

    @Override
    protected void onStart()
    {
    }

    @Override
    protected void onStop()
    {
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onServerPing(ServerListPingEvent event)
    {
        final String ip = event.getAddress().getHostAddress().trim();

        if (plugin.bm.isIpBanned(ip))
        {
            event.setMotd(ChatColor.RED + "You are banned.");
            return;
        }

        if (ConfigEntry.ADMIN_ONLY_MODE.getBoolean())
        {
            event.setMotd(ChatColor.RED + "Server is closed.");
            return;
        }

        if (Bukkit.hasWhitelist())
        {
            event.setMotd(ChatColor.RED + "Whitelist enabled.");
            return;
        }

        if (Bukkit.getOnlinePlayers().size() >= Bukkit.getMaxPlayers())
        {
            event.setMotd(ChatColor.RED + "Server is full.");
            return;
        }

        String lineone = ConfigEntry.MOTD_LINE_ONE.getString().replace("%mcversion%", plugin.si.getVersion());
        String linetwo = ConfigEntry.MOTD_LINE_TWO.getString().replace("%mcversion%", plugin.si.getVersion());
        String baseMotd = lineone + " \\n" + linetwo;
        baseMotd = baseMotd.replace("\\n", "\n");
        baseMotd = FUtil.colorize(baseMotd);

        if (!ConfigEntry.MOTD_COLORFUL_MOTD.getBoolean())
        {
            event.setMotd(baseMotd);
            return;
        }

        // Colorful MOTD
        final StringBuilder motd = new StringBuilder();
        for (String word : baseMotd.split(" "))
        {
            motd.append(FUtil.randomChatColor()).append(word).append(" ");
        }

        event.setMotd(motd.toString().trim());
    }

}
