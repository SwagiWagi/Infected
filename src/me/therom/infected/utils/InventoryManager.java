package me.therom.infected.utils;

public class InventoryManager
{

	private static InventoryManager instance;

	public static InventoryManager getInstance()
	{
		if (instance == null)
		{
			instance = new InventoryManager();
		}
		
		return instance;
	}

	private InventoryManager()
	{

	}
	
	

}
