package me.therom.infected.core;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.therom.infected.game.ZombieType;
import me.therom.infected.game.management.ArenaConfig;
import me.therom.infected.game.management.ArenaManager;
import me.therom.infected.utils.ChatUtils;
import me.therom.infected.utils.Utils;

public class CommandImpl implements CommandExecutor
{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (!label.equalsIgnoreCase("infected"))
			return false;

		if (!(sender instanceof Player))
		{
			sender.sendMessage(ChatUtils.INFECTED_PREFIX_NOT_COLORIZED + "Please use this command as a player!");
			return true;
		}

		Player p = (Player) sender;

		if (args.length == 0)
		{
			p.sendMessage(ChatUtils.INFECTED_PREFIX_NORMAL + ChatColor.DARK_RED + "Please check out the manual of the plugin at the Spigot page.");
			return true;
		}
		
		if (args.length == 1)
		{
			if (args[0].equalsIgnoreCase("reloadconfig"))
			{
				Config.getInstance().reloadConfig();
				ArenaConfig.getInstance().reloadConfig();
				p.sendMessage(ChatUtils.INFECTED_PREFIX_NORMAL + ChatColor.GREEN + "Successfully reloaded the config files!");
				return true;
			}
			else
			{
				p.sendMessage(ChatUtils.INFECTED_PREFIX_NORMAL + ChatColor.DARK_RED + "Please check out the manual of the plugin at the Spigot page.");
				return true;
			}
		}
		
		if (args.length == 2)
		{
			if (args[0].equalsIgnoreCase("createarena"))
			{
				if (ArenaManager.getInstance().arenaExists(args[1]))
				{
					p.sendMessage(ChatUtils.ERROR_PREFIX + ChatColor.DARK_RED + "There's already an arena with that ID.");
					return true;
				}
				else
				{
					ArenaManager.getInstance().createArena(args[1], p.getLocation());
					p.sendMessage(ChatUtils.INFECTED_PREFIX_NORMAL + ChatColor.GREEN + "Successfully created arena " + ChatColor.GREEN + args[1] + ChatColor.GREEN + "!");
					return true;
				}
			}
			else if (args[0].equalsIgnoreCase("deletearena"))
			{
				if (ArenaManager.getInstance().arenaExists(args[1]))
				{
					ArenaManager.getInstance().deleteArena(args[1]);
					p.sendMessage(ChatUtils.INFECTED_PREFIX_NORMAL + ChatColor.GREEN + "Successfully removed arena " + ChatColor.GREEN + args[1] + ChatColor.GREEN + "!");
					return true;
				}
				else
				{
					p.sendMessage(ChatUtils.arenaNotExists(args[0]));
					return true;
				}
			}
			else if (args[0].equalsIgnoreCase("setspawn"))
			{
				if (ArenaManager.getInstance().arenaExists(args[1]))
				{
					ArenaManager.getInstance().setSpawn(args[1], p.getLocation());
					p.sendMessage(ChatUtils.INFECTED_PREFIX_NORMAL + ChatColor.GREEN + "Successfully setted spawn location for arena " + ChatColor.GREEN + args[0] + ChatColor.GREEN + "!");
					return true;
				}
				else
				{
					p.sendMessage(ChatUtils.arenaNotExists(args[0]));
					return true;
				}
			}
		}
		
		if (args.length == 3)
		{
			
		}
		
		if (args.length == 4)
		{
			if (!ArenaManager.getInstance().arenaExists(args[1]))
			{
				p.sendMessage(ChatUtils.arenaNotExists(args[1]));
				return true;
			}
			else if (!Utils.isZombieTypeValid(args[3]))
			{
				p.sendMessage(ChatUtils.ERROR_PREFIX + ChatColor.DARK_RED + "Zombie Type is not valid!");
				return true;
			}
			else
			{
				System.out.println("g");
				ArenaManager.getInstance().addZombieSpawn(args[1], p.getLocation(), args[2], ZombieType.valueOf(args[3]));
				p.sendMessage(ChatUtils.INFECTED_PREFIX_NORMAL + ChatColor.GREEN + "Successfully added a zombie!");
				return true;
			}
		}
		
		return true;
	}

}
