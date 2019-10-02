package me.therom.infected.game.management;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

import me.therom.infected.core.Config;
import me.therom.infected.core.Core;
import me.therom.infected.game.Arena;
import me.therom.infected.game.characters.ArenaPlayer;
import me.therom.infected.game.waves.ConstantWave;
import me.therom.infected.game.waves.RegularWave;
import me.therom.infected.game.waves.Wave;
import me.therom.infected.utils.ChatUtils;

public class GameManager
{
	private Arena arena;
	
	private int countDownlength = Config.getInstance().getCountdownLength();
	
	private ArrayList<Wave> waves;

	public GameManager(Arena arena)
	{
		this.arena = arena;
		this.waves = new ArrayList<Wave>();
	}

	public void startGame()
	{
		for (ArenaPlayer p : arena.getPlayers())
		{
			p.getPlayer().teleport(arena.getSpawn());
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

	public Wave getWave(int index)
	{
		return this.waves.get(index);
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

				for (ArenaPlayer p : arena.getPlayers())
				{
					ChatUtils.sendTitle(p.getPlayer(), cc + "Game starting in", cc + String.valueOf(countDownlength));
				}
				
				countDownlength--;
			}
		}.runTaskTimer(Core.getCore(), 100, 20);
	}

	private void manageGame()
	{
		for (ArenaPlayer p : arena.getPlayers())
		{
			ChatUtils.sendTitle(p.getPlayer(), ChatColor.GREEN + "Game has started", "");
		}
		
		waves();
	}
	
	private void waves()
	{
		Wave cw = new ConstantWave(this.arena.getZombies());
		Wave rw = new RegularWave(this.arena.getZombies());
		
		rw.startWave();
		cw.startWave();
		
		this.waves.add(rw);
		this.waves.add(cw);
	}
	
}
