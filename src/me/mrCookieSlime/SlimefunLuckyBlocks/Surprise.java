package me.mrCookieSlime.SlimefunLuckyBlocks;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface Surprise {

	public String getName();
	public LuckLevel getLuckLevel();
	public void activate(Player p, Location l);

	public enum LuckLevel {
		LUCKY,
		UNLUCKY,
		PANDORA;
	}

}
