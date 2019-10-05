package io.github.thebusybiscuit.slimefunluckyblocks;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.HandledBlock;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockBreakHandler;
import me.mrCookieSlime.Slimefun.api.BlockStorage;

public class LuckyBlock extends HandledBlock {

	public LuckyBlock(Category category, ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe) {
		super(category, item, id, recipeType, recipe);
	}
	
	public void register(Random random, Collection<Surprise> surprises, Predicate<Surprise> predicate) {
		BlockBreakHandler handler = (e, tool, fortune, drops) -> {
			String id = BlockStorage.checkID(e.getBlock());
			if (id != null && id.equals(getID())) {
				BlockStorage.retrieve(e.getBlock());
				e.setCancelled(true);
				e.getBlock().setType(Material.AIR);
				
				List<Surprise> luckySurprises = surprises.stream().filter(predicate).collect(Collectors.toList());
				luckySurprises.get(random.nextInt(luckySurprises.size())).activate(random, e.getPlayer(), e.getBlock().getLocation());
				return true;
			}
			return false;
		};
		
		super.register(false, handler);
	}

}
