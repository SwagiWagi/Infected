package me.therom.infected.game.characters;

import org.bukkit.entity.Player;

import me.therom.infected.game.Arena;

public class ArenaPlayer
{
	
	private Arena arena;
	private Player player;
	
	private int kills;
	private int deaths;
	
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
	
	public void addKill()
	{
		this.kills++;
	}
	
	public int getKills()
	{
		return this.kills;
	}
	
	public void addDeath()
	{
		this.deaths++;
	}
	
	public int getDeaths()
	{
		return this.deaths;
	}
}
