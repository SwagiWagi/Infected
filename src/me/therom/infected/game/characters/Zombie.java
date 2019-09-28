package me.therom.infected.game.characters;

import java.lang.reflect.Field;
import java.util.Collection;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_11_R1.CraftWorld;

import me.therom.infected.game.ZombieType;
import net.minecraft.server.v1_11_R1.EntityPlayer;
import net.minecraft.server.v1_11_R1.EntityZombie;
import net.minecraft.server.v1_11_R1.PathfinderGoalMoveTowardsTarget;
import net.minecraft.server.v1_11_R1.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_11_R1.PathfinderGoalSelector;

public class Zombie extends EntityZombie
{
	
	private String name;
	private ZombieType zombieType;
	private Location spawnLocation;
	
	@SuppressWarnings("rawtypes")
	public Zombie(String name, ZombieType zombieType, Location spawnLocation)
	{
		super(((CraftWorld) spawnLocation.getWorld()).getHandle());
		this.name = name;
		this.zombieType = zombieType;
		this.spawnLocation = spawnLocation;
		
        Collection targetB = (Collection) getPrivateField("b", PathfinderGoalSelector.class, targetSelector); targetB.clear();
        Collection targetC = (Collection) getPrivateField("c", PathfinderGoalSelector.class, targetSelector); targetC.clear();
        
		this.setBaby(false);
        this.setHealth(50);
        this.setCustomName(name);
        this.setCustomNameVisible(true);
        this.targetSelector.a(0, new PathfinderGoalNearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, true));
        this.goalSelector.a(20, new PathfinderGoalMoveTowardsTarget(this, 30.0D, 25.0F));
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
