package me.therom.infected.game.management;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

import me.therom.infected.game.Arena;
import me.therom.infected.game.ZombieType;
import me.therom.infected.game.characters.Zombie;

public class ArenaManager
{
	
	// Singleton
	
	private static ArenaManager instance;

	public static ArenaManager getInstance() {

		if (instance == null) {

			instance = new ArenaManager();
		}

		return instance;
	}

	private HashMap<String, Arena> arenas;
	
	// ALWAYS CHECK FOR arenaExists BEFORE CALLING ANY OF THOSE FUNCTIONS
	
	private ArenaManager()
	{
		arenas = new HashMap<String, Arena>();
		
		for (String id : ArenaConfig.getInstance().getConfiguration().getConfigurationSection("").getKeys(false))
		{
			arenas.put(id, new Arena(id,
					getArenaSpawnLocation(id),
					getArenaMaxPlayers(id),
					getArenaMaxTimePlayable(id),
					getArenaZombies(id)));
		}
	}
	
	private int getArenaMaxPlayers(String id)
	{
		return ArenaConfig.getInstance().getConfiguration().getInt(id + ".max-players");
	}
	
	private int getArenaMaxTimePlayable(String id)
	{
		return ArenaConfig.getInstance().getConfiguration().getInt(id + ".max-time-playable");
	}
	
	public void setSpawn(String id, Location location)
	{
		ArenaConfig.getInstance().setSpawn(id, location);
	}
	
	public Arena getArenaById(String id)
	{
		return arenas.get(id);
	}
	
	public boolean arenaExists(String id)
	{
		return arenas.containsKey(id);
	}
	
	private Location getArenaSpawnLocation(String id)
	{
		return new Location(
				Bukkit.getWorld(ArenaConfig.getInstance().getConfiguration().getString(id + ".spawn-location.world")),
				ArenaConfig.getInstance().getConfiguration().getDouble(id + ".spawn-location.x"),
				ArenaConfig.getInstance().getConfiguration().getDouble(id + ".spawn-location.y"),
				ArenaConfig.getInstance().getConfiguration().getDouble(id + ".spawn-location.z"),
				(float) ArenaConfig.getInstance().getConfiguration().getDouble(id + ".spawn-location.yaw"),
				(float) ArenaConfig.getInstance().getConfiguration().getDouble(id + ".spawn-location.pitch"));
	}

	public void createArena(String id, Location location)
	{
		ArenaConfig.getInstance().getConfiguration().createSection(id);
		
		ArenaConfig.getInstance().setSpawn(id, location);
		
		ArenaConfig.getInstance().getConfiguration().set(id + ".max-players", 0);
		ArenaConfig.getInstance().getConfiguration().set(id + ".max-time-playable", 0);
		
		ArenaConfig.getInstance().saveConfig();
		
		arenas.put(id, getArenaById(id));
	}
	
	public void deleteArena(String id)
	{
		ArenaConfig.getInstance().getConfiguration().set(id, null);
		ArenaConfig.getInstance().saveConfig();
		
		arenas.remove(id);
	}
	
	public Arena getArenaToAddAPlayerTo()
	{
		Arena arena = arenas.entrySet().iterator().next().getValue();
		
		for (Arena a : arenas.values())
		{
			if (a.isJoinable() &&
					a.getPlayers().size() > arena.getPlayers().size())
			{
				arena = a;
			}
		}
		
		if (arena == null)
		{
			return null;
		}
		
		return arena.isJoinable() ? arena : null;
	}

	public void addZombieSpawn(String id, Location location, String zombieName, ZombieType zombieType)
	{
		ArenaConfig.getInstance().addZombieSpawn(id, location, zombieName, zombieType);
	}
	
	private ArrayList<Zombie> getArenaZombies(String id)
	{
		ArrayList<Zombie> zombies = new ArrayList<Zombie>();
		
		for (String arenaId : ArenaConfig.getInstance().getConfiguration().getConfigurationSection("").getKeys(false))
		{
			if (arenaId.equals(id))
			{
				ConfigurationSection section = ArenaConfig.getInstance().getConfiguration().getConfigurationSection(arenaId + ".zombies");
				if (section == null)
				{
					return zombies;
				}
				
				for (String zombie : section.getKeys(false))
				{
					zombies.add(new Zombie(
							zombie, ZombieType.valueOf(ArenaConfig.getInstance().getConfiguration().getString(arenaId + ".zombies." + zombie + ".zombie-type")), 
							new Location(
							Bukkit.getWorld(ArenaConfig.getInstance().getConfiguration().getString(arenaId + ".zombies." + zombie + ".spawn-location.world")),
							ArenaConfig.getInstance().getConfiguration().getDouble(arenaId + ".zombies." + zombie + ".spawn-location.x"),
							ArenaConfig.getInstance().getConfiguration().getDouble(arenaId + ".zombies." + zombie + ".spawn-location.y"),
							ArenaConfig.getInstance().getConfiguration().getDouble(arenaId + ".zombies." + zombie + ".spawn-location.z"))));
				}
			}
		}
		
		return zombies;
	}
}
