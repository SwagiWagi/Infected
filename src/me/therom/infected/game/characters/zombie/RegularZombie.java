package me.therom.infected.game.characters.zombie;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;

public class RegularZombie extends Zombie
{
	
	RegularZombie(String name, Location spawnLocation)
	{
		super(name, spawnLocation);
	}

	@Override
	public void spawn()
	{
		zombie = (org.bukkit.entity.Zombie) this.getSpawnLocation().getWorld().spawnEntity(this.getSpawnLocation(), EntityType.ZOMBIE);
		
		zombie.setBaby(false);
		zombie.setHealth(5);
		zombie.setCustomName(this.getName());
		zombie.setCustomNameVisible(true);
		zombie.setCanPickupItems(false);
		zombie.setPassenger(null);
		
		this.isSpawned = true;
		this.isAlive = true;
		this.setTarget();
	}
		
	@EventHandler
	protected void onDeath(EntityDeathEvent e)
	{
		System.out.println("fireed");
		if (e.getEntity().getClass() == Zombie.class)
		{
			System.out.println(1);
			e.getDrops().clear();
		}
	}

}