package me.therom.infected.game.management;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.therom.infected.core.Config;
import me.therom.infected.core.Core;
import me.therom.infected.game.Arena;
import me.therom.infected.game.characters.zombie.Zombie;
import me.therom.infected.utils.ChatUtils;

public class GameManager
{
	private Arena arena;
	
	private int countDownlength = Config.getInstance().getCountdownLength();

	public GameManager(Arena arena)
	{
		this.arena = arena;
	}

	public void startGame()
	{
		for (Player p : arena.getPlayers())
		{
			p.teleport(arena.getSpawn());
		}

		countdown();
		
        new BukkitRunnable()
        {
            @Override
            public void run() {
                manageGame();
            }
        }.runTaskLater(Core.getCore(), countDownlength * 20 + 120);
	}

	private void countdown()
	{
		new BukkitRunnable()
		{
			@Override
			public void run()
			{
				ChatColor cc = ChatColor.GREEN;
				
				if (countDownlength == 0)
				{
					this.cancel();
				}
				else if (countDownlength <= 3)
				{
					cc = ChatColor.DARK_RED;
				}
				else if (countDownlength <= 7)
				{
					cc = ChatColor.GOLD;
				}

				for (Player p : arena.getPlayers())
				{
					ChatUtils.sendTitle(p, cc + "Game starting in", cc + String.valueOf(countDownlength));
				}
				
				countDownlength--;
			}
		}.runTaskTimer(Core.getCore(), 100, 20);
	}

	private void manageGame()
	{
		for (Player p : arena.getPlayers())
		{
			ChatUtils.sendTitle(p, ChatColor.GREEN + "Game has started", "");
		}
		
		waveOne();
	}
	
	private void waveOne()
	{
		for (Zombie zombie : arena.getZombies())
		{
	        zombie.setPosition(zombie.getSpawnLocation().getX(), zombie.getSpawnLocation().getY(), zombie.getSpawnLocation().getZ());
	        zombie.getWorld().addEntity(zombie);
		}
	}
}
