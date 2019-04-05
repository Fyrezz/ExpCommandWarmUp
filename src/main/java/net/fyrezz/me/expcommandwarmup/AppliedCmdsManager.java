package net.fyrezz.me.expcommandwarmup;

import java.util.ArrayList;
import java.util.List;

public class AppliedCmdsManager
{
  private List<String> loadedCmds = new ArrayList<String>();
  
  public AppliedCmdsManager()
  {
    loadCmds();
  }
  
  public void loadCmds()
  {
    for (String cmd : Main.instance.getConfig().getStringList("commands"))
    {
      this.loadedCmds.add("/" + cmd);
      Main.instance.getServer().getConsoleSender().sendMessage("[ExpCommandWarmUp] Added command /" + cmd);
    }
    if (this.loadedCmds == null) {
      Main.instance.getServer().getConsoleSender().sendMessage("[ExpCommandWarmUp] ERROR No commands defined!");
    }
  }
  
  public List<String> getCmdsApplied()
  {
    if (this.loadedCmds == null)
    {
      List<String> cmdsGiven = new ArrayList<String>();
      cmdsGiven.add("");
      return cmdsGiven;
    }
    return this.loadedCmds;
  }
}
