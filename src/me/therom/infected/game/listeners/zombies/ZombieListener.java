package me.therom.infected.game.listeners.zombies;

import org.bukkit.craftbukkit.v1_11_R1.entity.CraftZombie;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import me.therom.infected.game.characters.zombie.RegularZombie;

public class ZombieListener implements Listener
{

	public ZombieListener() { }

	@EventHandler
	public void onDeath(EntityDeathEvent e)
	{
		if (e.getEntity() instanceof Zombie)
		{
			if (((CraftZombie)e.getEntity()).getHandle() instanceof RegularZombie)
			{
				e.setDroppedExp(0);
				e.getDrops().clear();
			}
		}
	}
	
}
