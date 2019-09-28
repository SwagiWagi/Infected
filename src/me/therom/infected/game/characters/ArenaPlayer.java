package me.therom.infected.game.characters;

import org.bukkit.entity.Player;

import me.therom.infected.game.Arena;

public class ArenaPlayer
{
	
	Arena arena;
	Player player;
	
	public ArenaPlayer(Arena arena, Player player)
	{
		this.arena = arena;
		this.player = player;
	}
	
	public Arena getArena()
	{
		return this.arena;
	}
	
	public Player getPlayer()
	{
		return this.player;
	}
	
}
