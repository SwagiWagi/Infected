package me.therom.infected.game.characters.zombie;

import org.bukkit.Location;

import me.therom.infected.game.ZombieType;

public class ZombieBuilder
{
	private Zombie zombie;
	
	public ZombieBuilder(String name, Location spawnLocation, ZombieType zombieType)
	{
		switch (zombieType)
		{
		case regular:
			this.zombie = new RegularZombie(name, spawnLocation);
			break;
		default:
			this.zombie = new RegularZombie(name, spawnLocation);
			break;
		}
	}

	public Zombie build()
	{
		return this.zombie;
	}
}
