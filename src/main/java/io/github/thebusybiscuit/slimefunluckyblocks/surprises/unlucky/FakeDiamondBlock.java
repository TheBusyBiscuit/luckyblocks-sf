package io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;
import me.mrCookieSlime.Slimefun.api.BlockStorage;

public final class FakeDiamondBlock implements Surprise {
	
	@Override
	public String getName() {
		return "Normal and Fake Diamond Block";
	}

	@Override
	public void activate(Random random, Player p, Location l) {
		l.getBlock().getRelative(BlockFace.UP).getRelative(BlockFace.UP).setType(Material.DIAMOND_BLOCK);
		l.getBlock().getRelative(BlockFace.UP).setType(Material.DIAMOND_BLOCK);
		p.sendTitle("", ChatColor.translateAlternateColorCodes('&', "&7&oOne is real, one is not..."), 10, 20, 10);
		BlockStorage.store(random.nextInt(10) < 5 ? l.getBlock().getRelative(BlockFace.UP): l.getBlock(), "LUCKY_BLOCK_UNLUCKY");
	}

	@Override
	public LuckLevel getLuckLevel() {
		return LuckLevel.UNLUCKY;
	}

}
