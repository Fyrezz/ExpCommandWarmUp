package net.fyrezz.me.expcommandwarmup;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.fyrezz.me.expcommandwarmup.cmds.CmdCheckCd;
import net.fyrezz.me.expcommandwarmup.listeners.PlayerListener;

public class Main
  extends JavaPlugin
{
  public static Main instance;
  private FileConfiguration lang;
  private File langFile;
  public AppliedCmdsManager cmdsApplied;
  public WarmupCalculator warmupCalculator;
  
  public Main()
  {
    instance = this;
  }
  
  public void onEnable()
  {
    saveDefaultConfig();
    
    this.langFile = new File(getDataFolder(), "lang.yml");
    saveDefaultLang();
    this.lang = YamlConfiguration.loadConfiguration(this.langFile);
    
    this.cmdsApplied = new AppliedCmdsManager();
    this.warmupCalculator = new WarmupCalculator();
    
    registerListeners();
    
    registerCommands();
  }
  
  public void onDisable()
  {
    instance = null;
  }
  
  public void registerListeners()
  {
    getServer().getPluginManager().registerEvents(new PlayerListener(), this);
  }
  
  public void registerCommands()
  {
    getCommand("checkwarmup").setExecutor(new CmdCheckCd());
  }
  
  public void saveDefaultLang()
  {
    if (!this.langFile.exists()) {
      saveResource("lang.yml", false);
    }
  }
  
  public FileConfiguration getLang()
  {
    if (this.lang == null) {
      this.lang = YamlConfiguration.loadConfiguration(this.langFile);
    }
    return this.lang;
  }
  
  public void pM(Player player, String path)
  {
    player.sendMessage(ChatColor.translateAlternateColorCodes('&', getLang().getString("prefix")) + ChatColor.RESET + " " + ChatColor.translateAlternateColorCodes('&', getLang().getString(path)));
  }
  
  public void pM(Player player, String path, String extra)
  {
    player.sendMessage(ChatColor.translateAlternateColorCodes('&', getLang().getString("prefix")) + ChatColor.RESET + " " + ChatColor.translateAlternateColorCodes('&', getLang().getString(path)) + " " + ChatColor.translateAlternateColorCodes('&', extra));
  }
}
