package me.therom.infected.game.management;

import java.io.File;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.therom.infected.core.Core;
import me.therom.infected.game.characters.zombie.ZombieType;

public class ArenaConfig
{
	
	private static ArenaConfig instance;
	
	public static ArenaConfig getInstance()
	{
		if (instance == null)
		{
			instance = new ArenaConfig();
		}
		
		return instance;
	}
	
	private ArenaConfig() {
		f = new File(Core.getCore().getDataFolder(), "arenas.yml");
		
		fc = YamlConfiguration.loadConfiguration(f);
	}

	private File f;
	private FileConfiguration fc;
	
	FileConfiguration getConfiguration()
	{
		return fc;
	}
	
	void saveConfig()
	{
		try
		{
			getConfiguration().save(f);
		}
		catch (IOException e) {}
	}
	
	public void reloadConfig()
	{
		f = new File(Core.getCore().getDataFolder(), "arenas.yml");
		
		fc = YamlConfiguration.loadConfiguration(f);
	}
	
	void setSpawn(String id, Location location)
	{
		getConfiguration().set(id + ".spawn-location.world", location.getWorld().getName());
		getConfiguration().set(id + ".spawn-location.x", location.getX());
		getConfiguration().set(id + ".spawn-location.y", location.getY());
		getConfiguration().set(id + ".spawn-location.z", location.getZ());
		getConfiguration().set(id + ".spawn-location.yaw", location.getYaw());
		getConfiguration().set(id + ".spawn-location.pitch", location.getPitch());
		
		saveConfig();
	}
	
	void addZombieSpawn(String id, Location location, String zombieName, ZombieType zombieType)
	{
		getConfiguration().set(id + ".zombies." + zombieName + ".zombie-type", zombieType.toString());

		getConfiguration().set(id + ".zombies." + zombieName + ".spawn-location.world", location.getWorld().getName());
		getConfiguration().set(id + ".zombies." + zombieName + ".spawn-location.x", location.getX());
		getConfiguration().set(id + ".zombies." + zombieName + ".spawn-location.y", location.getY());
		getConfiguration().set(id + ".zombies." + zombieName + ".spawn-location.z", location.getZ());
		
		saveConfig();
	}
}
