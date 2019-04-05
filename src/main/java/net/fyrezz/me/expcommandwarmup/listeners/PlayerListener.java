package net.fyrezz.me.expcommandwarmup.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.scheduler.BukkitRunnable;

import net.fyrezz.me.expcommandwarmup.Main;

public class PlayerListener
  implements Listener
{
  private static final FileConfiguration config = Main.instance.getConfig();
  private Main plugin = Main.instance;
  
  @EventHandler(priority=EventPriority.HIGHEST)
  public void onPlayerCommandPreProcess(PlayerCommandPreprocessEvent event)
  {
    final String commandWithoutSlash = event.getMessage().replace("/", "");
    final Player player = event.getPlayer();
    if ((!player.isOp()) || ((player.isOp()) && (!config.getBoolean("opsbypasscheck")))) {
      for (String cmd : this.plugin.cmdsApplied.getCmdsApplied()) {
        if (event.getMessage().startsWith(cmd))
        {
          double secondsWarmup = this.plugin.warmupCalculator.getWarmup(player);
          new BukkitRunnable()
          {
            public void run()
            {
              player.performCommand(commandWithoutSlash);
            }
          }.runTaskLater(this.plugin, (long) (20.0D * secondsWarmup));
          this.plugin.pM(player, "warmupstarted", "&c(&n" + secondsWarmup + "s&r&c)");
          event.setCancelled(true);
        }
      }
    }
  }
}
