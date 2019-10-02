package me.therom.infected.game.waves;

import java.util.ArrayList;

import me.therom.infected.game.characters.zombie.Zombie;
import net.minecraft.server.v1_11_R1.EntityLiving;

public abstract class Wave {

	private ArrayList<Zombie> zombies;
	private boolean inProgress;
	
	public Wave(ArrayList<Zombie> zombies)
	{
		this.zombies = zombies;
	}
	
	public void startWave()
	{
		this.inProgress = true;
		
		for (Zombie z : this.zombies)
		{
			z.spawnZombie();
		}
	}
	
	public boolean isInProgress()
	{
		return this.inProgress;
	}
	
	public void setFinished()
	{
		this.inProgress = false;
	}
	
	public ArrayList<Zombie> getZombies()
	{
		return this.zombies;
	}
	
	public Zombie getZombie(EntityLiving zombie)
	{
		for (Zombie z : this.zombies)
		{
			if (z.getUniqueID() == zombie.getUniqueID())
			{
				return z;
			}
		}
		
		return null;
	}
}
