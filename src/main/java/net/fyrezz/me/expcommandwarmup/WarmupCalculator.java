package net.fyrezz.me.expcommandwarmup;

import org.bukkit.entity.Player;

public class WarmupCalculator
{
  private Main plugin = Main.instance;
  double maxseconds;
  double minseconds;
  double lvlMultiplier;
  
  public WarmupCalculator()
  {
    this.maxseconds = this.plugin.getConfig().getInt("maxwarmup");
    this.minseconds = this.plugin.getConfig().getInt("minwarmup");
    this.lvlMultiplier = this.plugin.getConfig().getDouble("reductionperlevel");
  }
  
  public double getWarmup(Player player)
  {
    int level = player.getLevel();
    
    double totalReduction = level * this.lvlMultiplier;
    if (this.maxseconds - totalReduction < this.minseconds) {
      return this.minseconds;
    }
    return this.maxseconds - totalReduction;
  }
}
