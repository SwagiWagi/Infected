package me.therom.infected.game.listeners.signs;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import me.therom.infected.game.Arena;
import me.therom.infected.game.characters.ArenaPlayer;
import me.therom.infected.game.management.ArenaManager;
import me.therom.infected.utils.ChatUtils;

public class SignListener implements Listener
{

	@EventHandler
	public void onPlayerInteract(SignChangeEvent e)
	{
		if (e.getBlock() == null)
		{
			return;
		}
		
		if (SignManager.getInstance().isSignCreatedAJoinSign(e))
		{
			e.getPlayer().sendMessage(ChatUtils.INFECTED_PREFIX_NORMAL + ChatColor.GREEN + "Sign created successfully!");
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e)
	{
		if (e.getClickedBlock() == null || e.getAction() != Action.RIGHT_CLICK_BLOCK)
		{
			return;
		}
		
		if (SignManager.getInstance().isJoinSign(e.getClickedBlock()))
		{
			Arena arena = ArenaManager.getInstance().getArenaToAddAPlayerTo();
			
			if (arena == null)
			{
				e.getPlayer().sendMessage(ChatUtils.ERROR_PREFIX + ChatColor.GOLD + "There are no available arenas right now.");
			}
			
			arena.addPlayer(new ArenaPlayer(arena, e.getPlayer()));			
		}
	}

}
