package me.therom.infected.game.characters.zombie;

import org.bukkit.Location;

import me.therom.infected.game.ZombieType;

public class ZombieFactory //extends Zombie maybe
{
	private Zombie zombie;
	
	public ZombieFactory()
	{
		//Build the factory
		// TODO Auto-generated constructor stub
	}
	
	public ZombieFactory(String name, Location spawnLocation)
	{
		this.zombie = new Zombie(name, spawnLocation);
	}
	
	public void setZombieType(ZombieType zombieType)
	{
		this.zombie.setZombieType(zombieType);
	}

	public Zombie build()
	{
		if (this.zombie.getZombieType() == null)
		{
			return null;
		}
		
		return this.zombie;
	}
}
