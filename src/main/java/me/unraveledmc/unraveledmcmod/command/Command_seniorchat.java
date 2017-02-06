package me.unraveledmc.unraveledmcmod.command;

import me.unraveledmc.unraveledmcmod.player.FPlayer;
import me.unraveledmc.unraveledmcmod.rank.Rank;
import me.unraveledmc.unraveledmcmod.util.FUtil;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.SENIOR_ADMIN, source = SourceType.BOTH)
@CommandParameters(
        description = "AdminChat - Talk privately with other senior admins. Using <command> itself will toggle SeniorAdminChat on and off for all messages.",
        usage = "/<command> [message...]",
        aliases = "p,sc")
public class Command_seniorchat extends FreedomCommand
{

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length == 0)
        {
            if (senderIsConsole)
            {
                msg("Only in-game players can toggle SeniorChat.");
                return true;
            }

            FPlayer userinfo = plugin.pl.getPlayer(playerSender);
            userinfo.setSeniorChat(!userinfo.inSeniorChat());
            msg("Toggled Senior Chat " + (userinfo.inSeniorChat() ? "on" : "off") + ".");
            msg ("Remember to disable Admin Chat(/ac /o) otherwise Senior Chat doesn't work");
        }
        else
        {
            plugin.cm.seniorChat(sender, StringUtils.join(args, " "));
        }
       

        return true;
    }
}

