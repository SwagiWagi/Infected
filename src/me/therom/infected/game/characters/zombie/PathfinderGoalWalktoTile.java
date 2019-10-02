package me.therom.infected.game.characters.zombie;

import org.bukkit.entity.Entity;

import net.minecraft.server.v1_11_R1.EntityCreature;
import net.minecraft.server.v1_11_R1.PathfinderGoal;

public class PathfinderGoalWalktoTile extends PathfinderGoal {

	float speed;
	private EntityCreature entitycreature;
	private Entity targetPlayer;
	
	public PathfinderGoalWalktoTile(EntityCreature entitycreature, float speed, Entity targetPlayer) {
		this.speed = speed;
		this.entitycreature = entitycreature;
		this.targetPlayer = targetPlayer;
	}

	@Override
	public boolean a() {
		return true;
	}

	@Override
	public void c() {
		this.entitycreature.getNavigation().a(
				this.targetPlayer.getLocation().getX(), 
				this.targetPlayer.getLocation().getY(), 
				this.targetPlayer.getLocation().getZ(), 
				speed);
	}

}