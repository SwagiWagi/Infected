package me.therom.infected.game.waves;

import java.util.ArrayList;

import org.bukkit.scheduler.BukkitRunnable;

import me.therom.infected.core.Core;
import me.therom.infected.game.characters.zombie.Zombie;

public class ConstantWave extends Wave
{

	public ConstantWave(ArrayList<Zombie> zombies)
	{
		super(zombies);
	}
	
	@Override
	public void startWave()
	{
		this.inProgress = true;
		
		new BukkitRunnable()
		{
			@Override
			public void run()
			{
				for (Zombie z : getZombies())
				{
					if (z.isSpawned() && z.getZombie().isDead())
					{
						z.spawn();
					}
				}
			}
		}.runTaskTimer(Core.getCore(), 0, 20 * 15);
	}

}
