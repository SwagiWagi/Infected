package me.therom.infected.utils;

import java.lang.reflect.Field;

import org.apache.commons.lang3.EnumUtils;

import me.therom.infected.game.characters.zombie.ZombieType;

public class Utils
{

	private Utils() {}
	
	public static boolean isZombieTypeValid(String zombieType)
	{
		return EnumUtils.isValidEnum(ZombieType.class, zombieType);
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
