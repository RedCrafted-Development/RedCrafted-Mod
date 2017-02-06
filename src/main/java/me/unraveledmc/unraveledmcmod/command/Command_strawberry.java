//reuben4545
package me.unraveledmc.unraveledmcmod.command;

import java.util.Random;
import me.unraveledmc.unraveledmcmod.rank.Rank;
import me.unraveledmc.unraveledmcmod.util.FUtil;
import org.bukkit.Achievement;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@CommandPermissions(level = Rank.SUPER_ADMIN, source = SourceType.BOTH)
@CommandParameters(description = "For the people that play RedCrafted, usage = "/<command>")
public class Command_strawberry extends FreedomCommand
{

    public static final String STRAWBERRY_LYRICS = "Grown straight from the sunny side of xXGlassShardsXx's house in the UK.";
    private final Random random = new Random();

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        final StringBuilder output = new StringBuilder();

        final String[] words = STRAWBERRY_LYRICS.split(" ");
        for (final String word : words)
        {
            output.append(ChatColor.COLOR_CHAR).append(Integer.toHexString(1 + random.nextInt(14))).append(word).append(" ");
        }

        final ItemStack heldItem = new ItemStack(Material.BEETROOT);
        final ItemMeta heldItemMeta = heldItem.getItemMeta();
        heldItemMeta.setDisplayName((new StringBuilder()).append(ChatColor.GOLD).append("Fresh ").append(ChatColor.RED).append("Strawberry").toString());
        heldItem.setItemMeta(heldItemMeta);

        for (final Player player : server.getOnlinePlayers())
        {
            final int firstEmpty = player.getInventory().firstEmpty();
            if (firstEmpty >= 0)
            {
                player.getInventory().setItem(firstEmpty, heldItem);
            }

        FUtil.bcastMsg(output.toString());

        return true;
    }
}
