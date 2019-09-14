package io.github.thebusybiscuit.slimefunluckyblocks;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Rotatable;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;

import io.github.thebusybiscuit.slimefunluckyblocks.surprises.LuckLevel;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.Surprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky.CakeSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky.CookedFoodSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky.DiamondBlockPillarSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky.DiamondBlockSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky.EmeraldBlockSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky.GoldenAppleSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky.IronBlockSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky.LuckyAxeSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky.LuckyBootsSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky.LuckyChestplateSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky.LuckyHelmetSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky.LuckyLeggingsSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky.LuckyPickaxeSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky.LuckyPotionsSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky.LuckySwordSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky.TamedCatsSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky.TamedDogsSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky.UnluckyPotionsSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky.ValuablesSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.lucky.XPRainSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.neutral.ChickenRainSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.neutral.CookieSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.neutral.DyeSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.neutral.FishSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.neutral.GrootSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.neutral.HaySurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.neutral.JebSheepSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.neutral.JerrySlimeSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.neutral.PotatOSSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.neutral.RainbowSheepSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.neutral.RawFoodSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.neutral.VillagersSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.neutral.WanderingTraderSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.pandora.IronGolemsSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.pandora.ReapersSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky.AnvilRainSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky.BryanZombieSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky.ChargedCreeperSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky.CobwebSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky.EnclosedWaterSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky.ExplosionSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky.FakeDiamondBlock;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky.FlyingCreeperSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky.FlyingTNTSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky.GiantSlimeSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky.HighJumpSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky.TNTRainSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky.VoidHoleSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky.WalshrusSurprise;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky.WitchSurprise;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
import me.mrCookieSlime.CSCoreLibPlugin.general.World.CustomSkull;
import me.mrCookieSlime.Slimefun.cscorelib2.updater.BukkitUpdater;
import me.mrCookieSlime.Slimefun.cscorelib2.updater.GitHubBuildsUpdater;
import me.mrCookieSlime.Slimefun.cscorelib2.updater.Updater;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.bstats.bukkit.Metrics;

public class SlimefunLuckyBlocks extends JavaPlugin {

	private Config cfg;
	private final Random random = new Random();
	private final List<Surprise> surprises = new LinkedList<>();
	private final BlockFace[] blockfaces = {BlockFace.NORTH, BlockFace.NORTH_EAST, BlockFace.EAST, BlockFace.SOUTH_EAST, BlockFace.SOUTH, BlockFace.SOUTH_WEST, BlockFace.WEST, BlockFace.NORTH_WEST};
	private final String texture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjNiNzEwYjA4YjUyM2JiYTdlZmJhMDdjNjI5YmEwODk1YWQ2MTEyNmQyNmM4NmJlYjM4NDU2MDNhOTc0MjZjIn19fQ==";
	
	@Override
	public void onEnable() {
		cfg = new Config(this);

		// Setting up bStats
		new Metrics(this);

		// Setting up the Auto-Updater
		Updater updater;

		if (!getDescription().getVersion().startsWith("DEV - ")) {
			// We are using an official build, use the BukkitDev Updater
			updater = new BukkitUpdater(this, getFile(), 92132);
		}
		else {
			// If we are using a development build, we want to switch to our custom 
			updater = new GitHubBuildsUpdater(this, getFile(), "TheBusyBiscuit/luckyblocks-sf/master");
		}
		
		// Only run the Updater if it has not been disabled
		if (cfg.getBoolean("options.auto-update")) updater.start();
		
		Category category = null;
		
		ItemStack luckyBlock = null;
		ItemStack veryLuckyBlock = null;
		ItemStack veryUnluckyBlock = null;
		ItemStack pandorasBox = null;
		
		try {
			category = new Category(new CustomItem(CustomSkull.getItem(texture), "&rLucky Blocks", "&a> Click to open"));
			
			luckyBlock = new CustomItem(CustomSkull.getItem(texture), "&rLucky Block", "&7Luck: &r0");
			veryLuckyBlock = new CustomItem(CustomSkull.getItem(texture), "&rVery lucky Block", "&7Luck: &a+80");
			veryUnluckyBlock = new CustomItem(CustomSkull.getItem(texture), "&rVery unlucky Block", "&7Luck: &c-80");
			pandorasBox = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODZjN2RkZTUxMjg3MWJkNjA3Yjc3ZTY2MzVhZDM5ZjQ0ZjJkNWI0NzI5ZTYwMjczZjFiMTRmYmE5YTg2YSJ9fX0="), "&5Pandora's Box", "&7Luck: &c&oERROR");
		} catch (Exception x) {
			getLogger().log(Level.SEVERE, "An Error occured while initializing Lucky Block Items for LuckyBlocks v" + getDescription().getVersion(), x);
		}
		
		new LuckyBlock(category, luckyBlock, "LUCKY_BLOCK", RecipeType.ENHANCED_CRAFTING_TABLE,
		new ItemStack[] {SlimefunItems.GOLD_12K, SlimefunItems.GOLD_12K, SlimefunItems.GOLD_12K, SlimefunItems.GOLD_12K, new ItemStack(Material.DISPENSER), SlimefunItems.GOLD_12K, SlimefunItems.GOLD_12K, SlimefunItems.GOLD_12K, SlimefunItems.GOLD_12K})
		.register(random, surprises, s -> s.getLuckLevel() != LuckLevel.PANDORA);
		
		new LuckyBlock(category, veryLuckyBlock, "LUCKY_BLOCK_LUCKY", RecipeType.ENHANCED_CRAFTING_TABLE,
		new ItemStack[] {null, SlimefunItems.GOLD_12K, null, SlimefunItems.GOLD_12K, luckyBlock, SlimefunItems.GOLD_12K, null, SlimefunItems.GOLD_12K, null})
		.register(random, surprises, s -> s.getLuckLevel() == LuckLevel.LUCKY);
		
		new LuckyBlock(category, veryUnluckyBlock, "LUCKY_BLOCK_UNLUCKY", RecipeType.ENHANCED_CRAFTING_TABLE,
		new ItemStack[] {null, new ItemStack(Material.SPIDER_EYE), null, new ItemStack(Material.SPIDER_EYE), luckyBlock, new ItemStack(Material.SPIDER_EYE), null, new ItemStack(Material.SPIDER_EYE), null})
		.register(random, surprises, s -> s.getLuckLevel() == LuckLevel.UNLUCKY);
		
		new LuckyBlock(category, pandorasBox, "PANDORAS_BOX", RecipeType.ENHANCED_CRAFTING_TABLE,
		new ItemStack[] {new ItemStack(Material.OAK_PLANKS), new ItemStack(Material.LAPIS_BLOCK), new ItemStack(Material.OAK_PLANKS), new ItemStack(Material.LAPIS_BLOCK), luckyBlock, new ItemStack(Material.LAPIS_BLOCK), new ItemStack(Material.OAK_PLANKS), new ItemStack(Material.LAPIS_BLOCK), new ItemStack(Material.OAK_PLANKS)})
		.register(random, surprises, s -> s.getLuckLevel() == LuckLevel.PANDORA);
		
		new WorldGenerator(this);
		registerSurprises();
		
		getLogger().log(Level.INFO, "Loaded {0} different Surprises!", surprises.size());
	}

	private void registerSurprises() {
		// Lucky Surprises
		registerSuprise(new CookedFoodSurprise());
		registerSuprise(new GoldenAppleSurprise());
		registerSuprise(new DiamondBlockSurprise());
		registerSuprise(new DiamondBlockPillarSurprise());
		registerSuprise(new EmeraldBlockSurprise());
		registerSuprise(new IronBlockSurprise());
		registerSuprise(new TamedDogsSurprise());
		registerSuprise(new TamedCatsSurprise());
		registerSuprise(new ValuablesSurprise());
		registerSuprise(new LuckySwordSurprise());
		registerSuprise(new LuckyPickaxeSurprise());
		registerSuprise(new LuckyAxeSurprise());
		registerSuprise(new XPRainSurprise());
		registerSuprise(new LuckyHelmetSurprise());
		registerSuprise(new LuckyChestplateSurprise());
		registerSuprise(new LuckyLeggingsSurprise());
		registerSuprise(new LuckyBootsSurprise());
		registerSuprise(new LuckyPotionsSurprise());
		registerSuprise(new UnluckyPotionsSurprise());
		registerSuprise(new CakeSurprise());
		
		// Neutral Surprises
		registerSuprise(new GrootSurprise());
		registerSuprise(new RawFoodSurprise());
		registerSuprise(new FishSurprise());
		registerSuprise(new WanderingTraderSurprise());
		registerSuprise(new RainbowSheepSurprise());
		registerSuprise(new ChickenRainSurprise());
		registerSuprise(new DyeSurprise());
		registerSuprise(new HaySurprise());
		registerSuprise(new CookieSurprise());
		registerSuprise(new JebSheepSurprise());
		registerSuprise(new VillagersSurprise());
		registerSuprise(new PotatOSSurprise());
		registerSuprise(new JerrySlimeSurprise());

		// Unlucky Surprises
		registerSuprise(new ChargedCreeperSurprise());
		registerSuprise(new WitchSurprise());
		registerSuprise(new ExplosionSurprise());
		registerSuprise(new VoidHoleSurprise());
		registerSuprise(new AnvilRainSurprise());
		registerSuprise(new EnclosedWaterSurprise());
		registerSuprise(new TNTRainSurprise());
		registerSuprise(new FlyingCreeperSurprise());
		registerSuprise(new FlyingTNTSurprise());
		registerSuprise(new FakeDiamondBlock());
		registerSuprise(new BryanZombieSurprise());
		registerSuprise(new WalshrusSurprise());
		registerSuprise(new HighJumpSurprise());
		registerSuprise(new CobwebSurprise());
		registerSuprise(new GiantSlimeSurprise());
		
		// Pandora Box Surprises
		registerSuprise(new ReapersSurprise());
		registerSuprise(new IronGolemsSurprise());
	}

	public static ItemStack createPotion(Color color, PotionEffect effect, boolean lucky) {
		ItemStack potion = new ItemStack(lucky ? Material.POTION: Material.SPLASH_POTION);
		PotionMeta pm = (PotionMeta) potion.getItemMeta();
		pm.setDisplayName(ChatColor.translateAlternateColorCodes('&', (lucky ? "&6Lucky" : "&cUnlucky") + " potion"));
		pm.setColor(color);
		pm.addCustomEffect(effect, false);
		potion.setItemMeta(pm);
		return potion;
	}

	public void registerSuprise(Surprise surprise) {
		if (cfg.contains("events." + surprise.getName())) {
			if (cfg.getBoolean("events." + surprise.getName())) {
				surprises.add(surprise);
			}
		}
		else {
			cfg.setValue("events." + surprise.getName(), true);
			cfg.save();
			surprises.add(surprise);
		}
	}

	public void spawnLuckyBlock(Block b) {
		b.setType(Material.PLAYER_HEAD);
		Rotatable s = (Rotatable) b.getBlockData();
		s.setRotation(blockfaces[random.nextInt(blockfaces.length)]);
		b.setBlockData(s);
		
		try {
			CustomSkull.setSkull(b, texture);
		} catch (Exception x) {
			getLogger().log(Level.SEVERE, "An Error occured while trying to spawn a Lucky Block v" + getDescription().getVersion(), x);
		}
		
		BlockStorage.store(b, "LUCKY_BLOCK");
	}
	
	public Random getRandom() {
		return random;
	}

	public Config getCfg() {
		return cfg;
	}

}
