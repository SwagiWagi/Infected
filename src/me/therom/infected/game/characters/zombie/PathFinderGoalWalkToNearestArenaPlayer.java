package me.therom.infected.game.characters.zombie;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.event.entity.EntityTargetEvent;

import com.google.common.base.Predicate;

import net.minecraft.server.v1_11_R1.Entity;
import net.minecraft.server.v1_11_R1.EntityCreature;
import net.minecraft.server.v1_11_R1.EntityHuman;
import net.minecraft.server.v1_11_R1.EntityInsentient;
import net.minecraft.server.v1_11_R1.EntityLiving;
import net.minecraft.server.v1_11_R1.EntityPlayer;
import net.minecraft.server.v1_11_R1.PathfinderGoal;
import net.minecraft.server.v1_11_R1.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_11_R1.PathfinderGoalTarget;
import net.minecraft.server.v1_11_R1.ScoreboardTeamBase;

public class PathFinderGoalWalkToNearestArenaPlayer extends PathfinderGoal {
	private static final Logger a = LogManager.getLogger();
	private final EntityInsentient b;
	private final Predicate<Entity> c;
	private final PathfinderGoalNearestAttackableTarget.DistanceComparator d;
	private EntityLiving e;

	@SuppressWarnings("unchecked")
	public PathFinderGoalWalkToNearestArenaPlayer(EntityInsentient entityinsentient) {
		this.b = entityinsentient;
		if (entityinsentient instanceof EntityCreature) {
			a.warn("Use NearestAttackableTargetGoal.class for PathfinerMob mobs!");
		}

		//this shit
		this.c = new Predicate() {
			public boolean a(@Nullable Entity entity) {
				if (!(entity instanceof EntityHuman))
					return false;
				if (((EntityHuman) entity).abilities.isInvulnerable) {
					return false;
				}
				double d0 = PathFinderGoalWalkToNearestArenaPlayer.this.f();

				if (entity.isSneaking()) {
					d0 *= 0.800000011920929D;
				}

				if (entity.isInvisible()) {
					float f = ((EntityHuman) entity).cO();

					if (f < 0.1F) {
						f = 0.1F;
					}

					d0 *= (0.7F * f);
				}

				return (entity.g(PathFinderGoalWalkToNearestArenaPlayer.this.b) > d0) ? false 
						: PathfinderGoalTarget.a(PathFinderGoalWalkToNearestArenaPlayer.this.b, (EntityLiving) entity,
								false, true);
			}

			public boolean apply(@Nullable Object object) {
				return a((Entity) object);
			}
		};
		this.d = new PathfinderGoalNearestAttackableTarget.DistanceComparator(entityinsentient);
	}
	
	public boolean a() {
		double d0 = f();
		//List list = this.b.world.a(EntityHuman.class, this.b.getBoundingBox().grow(d0, 4.0D, d0), this.c);
		List list = this.b.world.a(EntityHuman.class, this.b.getBoundingBox().grow(d0, this.f(), d0));
		
		Collections.sort(list, this.d);
		if (list.isEmpty()) {
			return false;
		}
		
		//this.e = (EntityLiving) list.get(0);
		List l = this.b.getBukkitEntity().getNearbyEntities(100D, 100D, 100D);
		Iterator iterator = l.iterator();
		while (iterator.hasNext())
		{
			Object entity = iterator.next();
			
			if (!(entity instanceof CraftPlayer))
			{
				iterator.remove();
			}
		}
		
		Collections.sort(l, this.d);
		
		System.out.println(l.size());
		
		if (!l.isEmpty())
		{
			//this.b.ses
			if (this.e != ((CraftPlayer) l.get(0)).getHandle())
			{
				this.e = ((CraftPlayer) l.get(0)).getHandle();
			}
		}
		return true;
	}

	public boolean b() {
		EntityLiving entityliving = this.b.getGoalTarget();

		if (entityliving == null)
			return false;
		if (!entityliving.isAlive())
			return false;
		if (entityliving instanceof EntityHuman && ((EntityHuman) entityliving).abilities.isInvulnerable) {
			return false;
		}
		ScoreboardTeamBase scoreboardteambase = this.b.aQ();
		ScoreboardTeamBase scoreboardteambase1 = entityliving.aQ();

		if (scoreboardteambase != null && scoreboardteambase1 == scoreboardteambase) {
			return false;
		}
		double d0 = f();

		return (this.b.h(entityliving) > d0 * d0) ? false
				: (!(entityliving instanceof EntityPlayer
						&& ((EntityPlayer) entityliving).playerInteractManager.isCreative()));
	}

	public void c() {
		this.b.setGoalTarget(this.e, EntityTargetEvent.TargetReason.CLOSEST_PLAYER, true);
		super.c();
	}

	public void d() {
		this.b.setGoalTarget(null);
		super.c();
	}

	protected double f() {
		//returning the "zombie view" length
		//return Double.MAX_VALUE;
		return 1300D;
	}
}
