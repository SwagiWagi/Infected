package me.therom.infected.core;

import org.bukkit.configuration.file.FileConfiguration;

public class Config
{
	
	private static Config instance;
	
	public static Config getInstance()
	{
		if (instance == null)
		{
			instance = new Config();
		}
		
		return instance;
	}
	
	private Config() {
		config = Core.getCore().getConfig();
	}

	private static FileConfiguration config;
	
	public boolean isConfigOk()
	{
		//bla bla some logic bla bla
		
		return true;
	}
	
	public FileConfiguration getConfig()
	{
		return config;
	}
	
	public int getCountdownLength()
	{
		return getConfig().getInt("countdown-length");
	}
	
	public void reloadConfig()
	{
		Core.getCore().reloadConfig();
		//reload the arneas.yml
	}

	public String[] getSignStructure()
	{
		String[] structure = new String[4];
		
		int c = 0;
		
		for (String s : getConfig().getStringList("join-sign"))
		{
			structure[c] = s;
			c++;
		}
		
		return structure;
	}
	
}
