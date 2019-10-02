package me.therom.infected.game;

import java.util.ArrayList;

import org.bukkit.Location;

import me.therom.infected.game.characters.ArenaPlayer;
import me.therom.infected.game.characters.zombie.Zombie;
import me.therom.infected.game.management.GameManager;
import me.therom.infected.utils.ChatUtils;
import net.md_5.bungee.api.ChatColor;

public class Arena
{
	private String id;
	
	private Location spawn;
	
	private int maxPlayers;
	
	private int maxTimePlayAble;
	
	private ArrayList<ArenaPlayer> players;
	
	private ArrayList<Zombie> zombies;
	
	private GameManager gameManager;
	
	private boolean joinable = true;
	
	public Arena(String id, Location spawn, int maxPlayers, int maxTimePlayAble, ArrayList<Zombie> zombies)
	{
		this.id = id;
		this.spawn = spawn;
		this.maxPlayers = maxPlayers;
		this.maxTimePlayAble = maxTimePlayAble;
		this.players = new ArrayList<ArenaPlayer>();
		this.zombies = zombies;
	}
	
	public String getId()
	{
		return id;
	}

	public Location getSpawn()
	{
		return spawn;
	}

	public int getMaxPlayers()
	{
		return maxPlayers;
	}

	public int getMaxTimePlayAble()
	{
		return maxTimePlayAble;
	}

	public ArrayList<ArenaPlayer> getPlayers()
	{
		return this.players;
	}
	
	public void setPlayers(ArrayList<ArenaPlayer> players)
	{
		this.players = players;
	}
	
	public void addPlayer(ArenaPlayer player)
	{
		this.players.add(player);
		
		player.getPlayer().sendMessage(ChatUtils.INFECTED_PREFIX_NORMAL + ChatColor.GREEN + "Joined queue, arena: " + ChatColor.GREEN + getId() + ChatColor.GREEN + ".");
		
		for (ArenaPlayer p : players)
		{
			p.getPlayer().sendMessage(ChatUtils.INFECTED_PREFIX_NORMAL + ChatColor.GREEN + player.getPlayer().getDisplayName() + ChatColor.GREEN + " Has joined the game!");
		}
		
		if (players.size() == getMaxPlayers())
		{
			setJoinable(false);
			gameManager = new GameManager(this);
			getGameManager().startGame();
		}
	}
	
	public GameManager getGameManager()
	{
		return this.gameManager;
	}
	
	public void setGameManager(GameManager gameManager)
	{
		this.gameManager = gameManager;
	}

	public boolean isJoinable()
	{
		return joinable;
	}

	public void setJoinable(boolean joinable)
	{
		this.joinable = joinable;
	}

	public ArrayList<Zombie> getZombies()
	{
		return this.zombies;
	}
}