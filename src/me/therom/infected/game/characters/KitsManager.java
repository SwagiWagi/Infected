package me.therom.infected.game.characters;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class KitsManager
{

	private static KitsManager instance;
	
	public static KitsManager getInstance()
	{
		if (instance == null)
		{
			instance = new KitsManager();
		}
		
		return instance;
	}
	
	ArrayList<ItemStack> doctor1;
	
	private KitsManager() {
		doctor1 = new ArrayList<ItemStack>();
	}
	
	static void setDoctor1(Player player)
	{
		//player.getInventory()
	}

}
