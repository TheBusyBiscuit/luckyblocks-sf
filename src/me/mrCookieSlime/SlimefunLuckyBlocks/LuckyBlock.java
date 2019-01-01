package me.mrCookieSlime.SlimefunLuckyBlocks;

import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
import me.mrCookieSlime.CSCoreLibPlugin.general.World.CustomSkull;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.HandledBlock;

public class LuckyBlock extends HandledBlock {
	
	private static Category category;
	
	static {
		try {
			category = new Category(new CustomItem(CustomSkull.getItem(SlimefunLuckyBlocks.texture), "&rLucky Blocks", "&a> Click to open"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public LuckyBlock(ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe) {
		super(category, item, id, recipeType, recipe);
	}

}
