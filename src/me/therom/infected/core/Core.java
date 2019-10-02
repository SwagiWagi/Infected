package me.therom.infected.core;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.therom.infected.game.characters.CustomEntityRegistry;
import me.therom.infected.game.characters.zombie.RegularZombie;
import me.therom.infected.game.listeners.GameListener;
import me.therom.infected.game.listeners.signs.SignListener;
import me.therom.infected.game.listeners.zombies.ZombieListener;

public class Core extends JavaPlugin
{

	private static Core instance;

	public static Core getCore()
	{
		return instance;
	}

	public void onEnable()
	{
		instance = this;

		this.saveDefaultConfig();

		Bukkit.getPluginCommand("infected").setExecutor(new CommandImpl());

		// Do config validation
		if (!Config.getInstance().isConfigOk())
		{
			System.out.println("Config not ok fuck man comeon");
			Bukkit.getPluginManager().disablePlugin(this);
		}
		
		registerEvents();

		registerCustomEntities();
	}

	public void onDisable()
	{

	}
	
	private void registerEvents()
	{
		Bukkit.getPluginManager().registerEvents(new SignListener(), this);
		Bukkit.getPluginManager().registerEvents(new ZombieListener(), this);
		Bukkit.getPluginManager().registerEvents(new GameListener(), this);
	}
	
	private void registerCustomEntities()
	{
		CustomEntityRegistry.registerCustomEntity(54, "Zombie", RegularZombie.class);
	}

}