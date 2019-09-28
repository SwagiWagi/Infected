package me.therom.infected.utils;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_11_R1.IChatBaseComponent;
import net.minecraft.server.v1_11_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_11_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_11_R1.PacketPlayOutTitle.EnumTitleAction;

public class ChatUtils
{
	public static final String INFECTED_PREFIX_NORMAL = ChatColor.GREEN + "[" + ChatColor.YELLOW + "Infected" + ChatColor.GREEN + "] ";
	public static final String ERROR_PREFIX = INFECTED_PREFIX_NORMAL + ChatColor.DARK_RED + "[ERROR] ";
	public static final String INFECTED_PREFIX_NOT_COLORIZED = "[Infected] ";
	
	// Only use one dot after number such as 1.2 2.2 2.5
	@Deprecated
	public static void sendTitle(
			Player player, 
			String text, 
			ChatColor chatColor, 
			int fadeInInTicks, 
			int durationInTicks, 
			int fadeOutInTicks)
	{
		IChatBaseComponent chatTitle = ChatSerializer.a("{\"text\": \"" + text + "\",color:" + chatColor.name().toLowerCase() + "}");
		
		PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, chatTitle);
		
		PacketPlayOutTitle length = new PacketPlayOutTitle(fadeInInTicks, durationInTicks, fadeOutInTicks);
		
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(title);
		
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(length);
	}

	// Only use one dot after number such as 1.2 2.2 2.5
	@Deprecated
	public static void sendSubtitle(
			Player player, 
			String text, 
			ChatColor chatColor, 
			int fadeInInTicks, 
			int durationInTicks, 
			int fadeOutInTicks)
	{
		IChatBaseComponent chatTitle = ChatSerializer.a("{\"text\": \"" + text + "\",color:" + chatColor.name().toLowerCase() + "}");
		
		PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, chatTitle);
		
		PacketPlayOutTitle length = new PacketPlayOutTitle(fadeInInTicks, durationInTicks, fadeOutInTicks);
		
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(title);
		
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(length);
	}

	@SuppressWarnings("deprecation")
	public static void sendTitle(Player p, String line1, String line2)
	{
		p.sendTitle(line1, line2);
	}
	
	public static String arenaNotExists(String arena)
	{
		return ChatUtils.ERROR_PREFIX + ChatColor.DARK_RED + "Arena " + ChatColor.DARK_RED + arena + ChatColor.DARK_RED + " does not exists!";
	}
}
