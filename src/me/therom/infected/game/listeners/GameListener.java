package me.therom.infected.game.listeners;

import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import me.therom.infected.game.Arena;
import me.therom.infected.game.characters.ArenaPlayer;
import me.therom.infected.game.management.ArenaManager;

public class GameListener implements Listener
{
	

	@EventHandler
	private void onDeath(EntityDeathEvent e)
	{
		if (e.getEntity() instanceof Player)
		{
			Player p = (Player) e.getEntity();
			Arena a = ArenaManager.getInstance().getPlayersArena(p);

			if (a == null)
				return;

			ArenaPlayer k = ArenaManager.getInstance().getArenaPlayerFromArena(p.getKiller(), a);

			if (k != null)
			{
				k.addKill();
			}

			ArenaPlayer d = ArenaManager.getInstance().getArenaPlayerFromArena(p, a);

			if (d != null)
			{
				d.addDeath();
			}
		}
		else if (e.getEntity() instanceof Zombie)
		{
			//if ()
		}
	}

}
