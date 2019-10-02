package me.therom.infected.game.listeners.zombies;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import me.therom.infected.game.characters.zombie.Zombie;
import me.therom.infected.game.management.ArenaManager;

public class ZombieListener implements Listener
{

	public ZombieListener() { }
	
	@EventHandler
	public void onDeath(EntityDeathEvent e)
	{
		if (e.getEntity() instanceof org.bukkit.entity.Zombie)
		{
			Zombie z = ArenaManager.getInstance().getZombie((org.bukkit.entity.Zombie) e.getEntity());
			
			if (z != null)
			{
				e.setDroppedExp(0);
				e.getDrops().clear();
			
				if (z.getZombie().getKiller() instanceof Player)
				{
					Player p = (Player) z.getZombie().getKiller();
					ArenaManager.getInstance().getArenaPlayer(p).addKill();
					System.out.println(ArenaManager.getInstance().getArenaPlayer(p).getKills());
				}
			}
		}
	}
	
}
