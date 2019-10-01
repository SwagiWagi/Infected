package me.therom.infected.game.listeners.signs;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.block.SignChangeEvent;

import me.therom.infected.core.Config;

public class SignManager
{

	private static SignManager instance;

	public static SignManager getInstance()
	{
		if (instance == null)
		{
			instance = new SignManager();
		}

		return instance;
	}

	private SignManager() {}

	public boolean isJoinSign(Block b)
	{
		if (b.getType() == Material.SIGN || b.getType() == Material.SIGN_POST || b.getType() == Material.WALL_SIGN)
		{
			Sign sign = (Sign) b.getState();

			String[] signStructure = Config.getInstance().getSignStructure();

			for (int i = 0; i < 4; i++)
			{
				if (signStructure[i] == null)
				{
					signStructure[i] = "";
				}

				if (!signStructure[i].equals(sign.getLine(i)))
				{
					return false;
				}
			}

			return true;
		}

		return false;
	}

	public boolean isSignCreatedAJoinSign(SignChangeEvent e)
	{
		String[] signStructure = Config.getInstance().getSignStructure();

		for (int i = 0; i < 4; i++)
		{
			if (signStructure[i] == null)
			{
				signStructure[i] = "";
			}
			
			if (!signStructure[i].equals(e.getLine(i)))
			{
				return false;
			}
		}

		return true;
	}
}
