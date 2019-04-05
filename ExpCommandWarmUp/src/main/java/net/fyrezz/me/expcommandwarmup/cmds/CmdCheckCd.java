package net.fyrezz.me.expcommandwarmup.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.fyrezz.me.expcommandwarmup.Main;

public class CmdCheckCd
  implements CommandExecutor
{
  private Main plugin = Main.instance;
  
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
  {
    if ((sender instanceof Player))
    {
      Player player = (Player)sender;
      this.plugin.pM(player, "warmupinfo", "&c&n" + Double.toString(this.plugin.warmupCalculator.getWarmup(player)) + "s.");
    }
    else
    {
      sender.sendMessage("Player command only!");
    }
    return false;
  }
}
