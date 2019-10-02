package me.therom.infected.game.waves;

import java.util.ArrayList;

import me.therom.infected.game.characters.zombie.Zombie;

public abstract class Wave {

	private ArrayList<Zombie> zombies;
	protected boolean inProgress;
	
	public Wave(ArrayList<Zombie> zombies)
	{
		this.zombies = zombies;
	}
	
	public void startWave()
	{
		this.inProgress = true;
		
		for (Zombie z : this.zombies)
		{
			z.spawn();
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
	
	public Zombie getZombie(Zombie zombie)
	{
		for (Zombie z : this.zombies)
		{
			if (zombie == z)
			{
				return z;
			}
		}
		
		return null;
	}
}
