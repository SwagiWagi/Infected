package me.therom.infected.game.characters.zombie;

import java.lang.reflect.Field;
import java.util.Collection;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_11_R1.CraftWorld;

import me.therom.infected.game.ZombieType;
import net.minecraft.server.v1_11_R1.EntityPlayer;
import net.minecraft.server.v1_11_R1.EntityZombie;
import net.minecraft.server.v1_11_R1.PathfinderGoalMeleeAttack;
import net.minecraft.server.v1_11_R1.PathfinderGoalMoveTowardsTarget;
import net.minecraft.server.v1_11_R1.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_11_R1.PathfinderGoalSelector;

public class Zombie extends EntityZombie
{
	
	private String name;
	private ZombieType zombieType;
	private Location spawnLocation;
	
	@SuppressWarnings("rawtypes")
	Zombie(String name, Location spawnLocation)
	{
		super(((CraftWorld) spawnLocation.getWorld()).getHandle());
		this.name = name;
		this.spawnLocation = spawnLocation;
		
	    Collection goalB = (Collection) getPrivateField("b", PathfinderGoalSelector.class, goalSelector); goalB.clear();
	    Collection goalC = (Collection) getPrivateField("c", PathfinderGoalSelector.class, goalSelector); goalC.clear();
        Collection targetB = (Collection) getPrivateField("b", PathfinderGoalSelector.class, targetSelector); targetB.clear();
        Collection targetC = (Collection) getPrivateField("c", PathfinderGoalSelector.class, targetSelector); targetC.clear();
        
		this.setBaby(false);
        this.setHealth(50);
        this.setCustomName(name);
        this.setCustomNameVisible(true);
	}
	
	void setZombieType(ZombieType zombieType)
	{
		if (zombieType.equals(ZombieType.regular))
		{
			this.targetSelector.a(0, new PathfinderGoalNearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, true));
			this.goalSelector.a(20, new PathfinderGoalMeleeAttack(this, 1, true));
		}
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public ZombieType getZombieType()
	{
		return this.zombieType;
	}
	
	public Location getSpawnLocation()
	{
		return this.spawnLocation;
	}
	
	@SuppressWarnings("rawtypes")
	public static Object getPrivateField(String fieldName, Class clazz, Object object)
    {
        Field field;
        Object o = null;

        try
        {
            field = clazz.getDeclaredField(fieldName);

            field.setAccessible(true);

            o = field.get(object);
        }
        catch(Exception ex)
        {
        	return null;
        }	

        return o;
    }

}
