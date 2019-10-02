package me.therom.infected.utils;

import org.apache.commons.lang3.EnumUtils;
import org.bukkit.Location;

import me.therom.infected.game.characters.zombie.ZombieType;

public class Utils
{

	private Utils() {}
	
	public static boolean isZombieTypeValid(String zombieType)
	{
		return EnumUtils.isValidEnum(ZombieType.class, zombieType);
	}
	
    public static double distance(Location a, Location b){
        return Math.sqrt(square(a.getX() - b.getX()) + square(a.getY() - b.getY()) + square(a.getZ() - b.getZ()));
    }
   
    private static double square(double x){
        return x * x;
    }
	
}
