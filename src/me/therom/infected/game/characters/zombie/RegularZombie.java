package me.therom.infected.game.characters.zombie;

import org.bukkit.Location;

import net.minecraft.server.v1_11_R1.EntityPlayer;
import net.minecraft.server.v1_11_R1.PathfinderGoalMeleeAttack;
import net.minecraft.server.v1_11_R1.PathfinderGoalNearestAttackableTarget;

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
        
    	this.targetSelector.a(0, new PathfinderGoalNearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, true));
		this.goalSelector.a(20, new PathfinderGoalMeleeAttack(this, 1, true));
	}
	
}