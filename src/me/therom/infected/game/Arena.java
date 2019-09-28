package me.therom.infected.game;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.therom.infected.game.characters.Zombie;
import me.therom.infected.utils.ChatUtils;
import net.md_5.bungee.api.ChatColor;

public class Arena
{
	private String id;
	
	private Location spawn;
	
	private int maxPlayers;
	
	private int maxTimePlayAble;
	
	private ArrayList<Player> players;
	
	private ArrayList<Zombie> zombies;
	
	private GameManager gameManager;
	
	private boolean joinable = true;
	
	public Arena(String id, Location spawn, int maxPlayers, int maxTimePlayAble, ArrayList<Zombie> zombies)
	{
		this.id = id;
		this.spawn = spawn;
		this.maxPlayers = maxPlayers;
		this.maxTimePlayAble = maxTimePlayAble;
		this.players = new ArrayList<Player>();
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

	public ArrayList<Player> getPlayers()
	{
		return this.players;
	}
	
	public void setPlayers(ArrayList<Player> players)
	{
		this.players = players;
	}
	
	public void addPlayer(Player player)
	{
		this.players.add(player);
		
		player.sendMessage(ChatUtils.INFECTED_PREFIX_NORMAL + ChatColor.GREEN + "Joined queue, arena: " + ChatColor.GREEN + getId() + ChatColor.GREEN + ".");
		
		for (Player p : players)
		{
			p.sendMessage(ChatUtils.INFECTED_PREFIX_NORMAL + ChatColor.GREEN + player.getDisplayName() + ChatColor.GREEN + " Has joined the game!");
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