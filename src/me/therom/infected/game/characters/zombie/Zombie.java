package me.therom.infected.game.characters.zombie;

import java.util.Collection;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.therom.infected.core.Core;
import me.therom.infected.game.management.ArenaManager;
import me.therom.infected.utils.Utils;

public abstract class Zombie
{
	private String name;
	private Location spawnLocation;
	protected org.bukkit.entity.Zombie zombie;
	protected boolean isSpawned, isAlive;
	
	protected Zombie(String name, Location spawnLocation) {
		this.name = name;
		this.spawnLocation = spawnLocation;
		this.isSpawned = false;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public Location getSpawnLocation()
	{
		return this.spawnLocation;
	}
	
	public org.bukkit.entity.Zombie getZombie()
	{
		return this.zombie;
	}
	
	protected void setTarget()
	{
		new BukkitRunnable()
		{
			@Override
			public void run()
			{
				Collection<Entity> targets = getZombie().getWorld().getEntitiesByClasses(Player.class);
				
				for (Entity possibleTarget : targets)
				{
					if ((getZombie().getTarget() == null ||
							ArenaManager.getInstance().getPlayersArena((Player) possibleTarget) != null &&
							Utils.distance(getZombie().getLocation(), getZombie().getTarget().getLocation()) >
							Utils.distance(getZombie().getLocation(), possibleTarget.getLocation())) &&
							((Player) possibleTarget).getGameMode() == GameMode.SURVIVAL || ((Player) possibleTarget).getGameMode() == GameMode.ADVENTURE)
					{
						System.out.println("Yes");
						getZombie().setTarget((LivingEntity) possibleTarget);
						return;
					}
				}
			}
		}.runTaskTimer(Core.getCore(), 0, 20);
	}
	
	public Boolean isSpawned()
	{
		return this.isSpawned;
	}
	
	public abstract void spawn();
}
