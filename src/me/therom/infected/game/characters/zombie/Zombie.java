package me.therom.infected.game.characters.zombie;

import java.util.Collection;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_11_R1.CraftWorld;

import me.therom.infected.utils.Utils;
import net.minecraft.server.v1_11_R1.EntityZombie;
import net.minecraft.server.v1_11_R1.PathfinderGoalSelector;

public abstract class Zombie extends EntityZombie
{
	private String name;
	private Location spawnLocation;
	
	protected Zombie(String name, Location spawnLocation) {
		super(((CraftWorld) spawnLocation.getWorld()).getHandle());

		this.name = name;
		this.spawnLocation = spawnLocation;
		
		Collection<?> goalB = (Collection<?>) Utils.getPrivateField("b", PathfinderGoalSelector.class, goalSelector); goalB.clear();
	    Collection<?> goalC = (Collection<?>) Utils.getPrivateField("c", PathfinderGoalSelector.class, goalSelector); goalC.clear();
	    Collection<?> targetB = (Collection<?>) Utils.getPrivateField("b", PathfinderGoalSelector.class, targetSelector); targetB.clear();
        Collection<?> targetC = (Collection<?>) Utils.getPrivateField("c", PathfinderGoalSelector.class, targetSelector); targetC.clear();
	}
	
	protected abstract void setZombieProperties();

	public String getName()
	{
		return this.name;
	}
	
	public Location getSpawnLocation()
	{
		return this.spawnLocation;
	}
	
	public void spawnZombie()
	{
        this.setPosition(this.getSpawnLocation().getX(), this.getSpawnLocation().getY(), this.getSpawnLocation().getZ());
        this.getWorld().addEntity(this);
	}
	
}
