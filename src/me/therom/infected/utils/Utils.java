package me.therom.infected.utils;

import org.apache.commons.lang3.EnumUtils;

import me.therom.infected.game.ZombieType;

public class Utils
{

	public static boolean isZombieTypeValid(String zombieType)
	{
		return EnumUtils.isValidEnum(ZombieType.class, zombieType);
	}

}
