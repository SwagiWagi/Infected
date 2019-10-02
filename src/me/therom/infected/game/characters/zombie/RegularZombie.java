package me.therom.infected.game.characters.zombie;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;

import net.minecraft.server.v1_11_R1.PathfinderGoalMeleeAttack;

public class RegularZombie extends Zombie
{

	RegularZombie(String name, Location spawnLocation)
	{
		super(name, spawnLocation);
		setZombieProperties();
	}

	@Override
	protected void setZombieProperties() {
		this.setBaby(false);
        this.setHealth(50);
        this.setCustomName(this.getName());
        this.setCustomNameVisible(true);
        this.targetSelector.a(0, new PathfinderGoalWalktoTile(this, 1, Bukkit.getPlayerExact("SwagiWagi")));
        //this.targetSelector.a(0, new PathFinderGoalWalkToNearestArenaPlayer(this));
    	//this.targetSelector.a(0, new PathfinderGoalNearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, 5, true, false, null));
		this.goalSelector.a(0, new PathfinderGoalMeleeAttack(this, 1, true));
	}
		
	@EventHandler
	protected void onDeath(EntityDeathEvent e)
	{
		System.out.println("fireed");
		if (e.getEntity().getClass() == Zombie.class)
		{
			System.out.println(1);
			e.getDrops().clear();
		}
	}
	
}