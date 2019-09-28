package me.therom.infected.core;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.therom.infected.game.characters.CustomEntityRegistry;
import me.therom.infected.game.characters.zombie.Zombie;
import me.therom.infected.game.listeners.signs.SignListener;

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

		Bukkit.getPluginManager().registerEvents(new SignListener(), this);
		
		CustomEntityRegistry.registerCustomEntity(54, "Zombie", Zombie.class);
		
		System.out.println("OK");
	}

	public void onDisable()
	{

	}

}