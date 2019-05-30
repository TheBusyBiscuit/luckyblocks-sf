package me.mrCookieSlime.SlimefunLuckyBlocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Rotatable;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Cat;
import org.bukkit.entity.Cat.Type;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Zombie;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;
import me.mrCookieSlime.CSCoreLibPlugin.PluginUtils;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.CSCoreLibPlugin.compatibility.MaterialHelper;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
import me.mrCookieSlime.CSCoreLibPlugin.general.Particles.FireworkShow;
import me.mrCookieSlime.CSCoreLibPlugin.general.World.CustomSkull;
import me.mrCookieSlime.CSCoreLibSetup.CSCoreLibLoader;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.BlockBreakHandler;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.SlimefunLuckyBlocks.Surprise.LuckLevel;

public class SlimefunLuckyBlocks extends JavaPlugin {

	static ItemStack luckyblock;
	static Config cfg;
	static List<Surprise> surprises;
	static Map<LuckLevel, List<Surprise>> luckylist;
	static BlockFace[] bf = {BlockFace.NORTH, BlockFace.NORTH_EAST, BlockFace.EAST, BlockFace.SOUTH_EAST, BlockFace.SOUTH, BlockFace.SOUTH_WEST, BlockFace.WEST, BlockFace.NORTH_WEST};
	static final String texture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjNiNzEwYjA4YjUyM2JiYTdlZmJhMDdjNjI5YmEwODk1YWQ2MTEyNmQyNmM4NmJlYjM4NDU2MDNhOTc0MjZjIn19fQ==";

	private ItemStack unluckyblock;

	@Override
	public void onEnable() {
		CSCoreLibLoader loader = new CSCoreLibLoader(this);
		if (loader.load()) {
			try {
				PluginUtils utils = new PluginUtils(this);
				utils.setupConfig();
				utils.setupUpdater(92131, getFile());
				cfg = utils.getConfig();
				surprises = new ArrayList<Surprise>();
				luckylist = new HashMap<LuckLevel, List<Surprise>>();

				registerSurprises();

				luckyblock = new CustomItem(CustomSkull.getItem(texture), "&rLucky Block", "&7Luck: &r0");
				unluckyblock = new CustomItem(CustomSkull.getItem(texture), "&rVery unlucky Block", "&7Luck: &c-80");

				new LuckyBlock(luckyblock, "LUCKY_BLOCK", RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {SlimefunItems.GOLD_12K, SlimefunItems.GOLD_12K, SlimefunItems.GOLD_12K, SlimefunItems.GOLD_12K, new ItemStack(Material.DISPENSER), SlimefunItems.GOLD_12K, SlimefunItems.GOLD_12K, SlimefunItems.GOLD_12K, SlimefunItems.GOLD_12K})
				.register(false, new BlockBreakHandler() {
					@Override
					public boolean onBlockBreak(BlockBreakEvent e, ItemStack arg1, int arg2, List<ItemStack> arg3) {
						SlimefunItem item = BlockStorage.check(e.getBlock());
						if (item != null && item.getID().equals("LUCKY_BLOCK")) {
							BlockStorage.retrieve(e.getBlock());
							e.setCancelled(true);
							e.getBlock().setType(Material.AIR);
							SlimefunLuckyBlocks.surprises.get(CSCoreLib.randomizer().nextInt(SlimefunLuckyBlocks.surprises.size())).activate(e.getPlayer(), e.getBlock().getLocation());
							return true;
						}
						return false;
					}
				});

				new LuckyBlock(new CustomItem(CustomSkull.getItem(texture), "&rVery lucky Block", "&7Luck: &a+80"), "LUCKY_BLOCK_LUCKY", RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {null, SlimefunItems.GOLD_12K, null, SlimefunItems.GOLD_12K, luckyblock, SlimefunItems.GOLD_12K, null, SlimefunItems.GOLD_12K, null})
				.register(false, new BlockBreakHandler() {
					@Override
					public boolean onBlockBreak(BlockBreakEvent e, ItemStack arg1, int arg2, List<ItemStack> arg3) {
						SlimefunItem item = BlockStorage.check(e.getBlock());
						if (item != null && item.getID().equals("LUCKY_BLOCK_LUCKY")) {
							BlockStorage.retrieve(e.getBlock());
							e.setCancelled(true);
							e.getBlock().setType(Material.AIR);
							SlimefunLuckyBlocks.luckylist.get(LuckLevel.LUCKY).get(CSCoreLib.randomizer().nextInt(SlimefunLuckyBlocks.luckylist.get(LuckLevel.LUCKY).size())).activate(e.getPlayer(), e.getBlock().getLocation());
							return true;
						}
						return false;
					}
				});

				new LuckyBlock(unluckyblock, "LUCKY_BLOCK_UNLUCKY", RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {null, new ItemStack(Material.SPIDER_EYE), null, new ItemStack(Material.SPIDER_EYE), luckyblock, new ItemStack(Material.SPIDER_EYE), null, new ItemStack(Material.SPIDER_EYE), null})
				.register(false, new BlockBreakHandler() {
					@Override
					public boolean onBlockBreak(BlockBreakEvent e, ItemStack i, int arg2, List<ItemStack> arg3) {
						SlimefunItem item = BlockStorage.check(e.getBlock());
						if (item != null && item.getID().equals("LUCKY_BLOCK_UNLUCKY")) {
							BlockStorage.retrieve(e.getBlock());
							e.setCancelled(true);
							e.getBlock().setType(Material.AIR);
							SlimefunLuckyBlocks.luckylist.get(LuckLevel.UNLUCKY).get(CSCoreLib.randomizer().nextInt(SlimefunLuckyBlocks.luckylist.get(LuckLevel.UNLUCKY).size())).activate(e.getPlayer(), e.getBlock().getLocation());
							return true;
						}
						return false;
					}
				});

				new LuckyBlock(new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODZjN2RkZTUxMjg3MWJkNjA3Yjc3ZTY2MzVhZDM5ZjQ0ZjJkNWI0NzI5ZTYwMjczZjFiMTRmYmE5YTg2YSJ9fX0="), "&5Pandora's Box", "&7Luck: &c&oERROR"), "PANDORAS_BOX", RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {new ItemStack(Material.OAK_PLANKS), new ItemStack(Material.LAPIS_BLOCK), new ItemStack(Material.OAK_PLANKS), new ItemStack(Material.LAPIS_BLOCK), luckyblock, new ItemStack(Material.LAPIS_BLOCK), new ItemStack(Material.OAK_PLANKS), new ItemStack(Material.LAPIS_BLOCK), new ItemStack(Material.OAK_PLANKS)})
				.register(false, new BlockBreakHandler() {
					@Override
					public boolean onBlockBreak(BlockBreakEvent e, ItemStack i, int arg2, List<ItemStack> arg3) {
						SlimefunItem item = BlockStorage.check(e.getBlock());
						if (item != null && item.getID().equals("PANDORAS_BOX")) {
							BlockStorage.retrieve(e.getBlock());
							e.setCancelled(true);
							e.getBlock().setType(Material.AIR);
							SlimefunLuckyBlocks.luckylist.get(LuckLevel.PANDORA).get(CSCoreLib.randomizer().nextInt(SlimefunLuckyBlocks.luckylist.get(LuckLevel.PANDORA).size())).activate(e.getPlayer(), e.getBlock().getLocation());
							return true;
						}
						return false;
					}
				});

				new Generator(this);
			} 
			catch(Exception x) {
			}
		}
	}

	private void registerSurprises() {
		registerSuprise(new Surprise() {
			@Override
			public String getName() {
				return "Cooked Food";
			}

			@Override
			public void activate(Player p, Location l) {
				FireworkShow.launchRandom(p, 3);
				l.getWorld().dropItemNaturally(l, new ItemStack(Material.COOKED_BEEF, 4));
				l.getWorld().dropItemNaturally(l, new ItemStack(Material.COOKED_CHICKEN, 4));
				l.getWorld().dropItemNaturally(l, new ItemStack(Material.COOKED_PORKCHOP, 4));
				l.getWorld().dropItemNaturally(l, new ItemStack(Material.COOKED_COD, 4));
				l.getWorld().dropItemNaturally(l, new ItemStack(Material.COOKED_MUTTON, 4));
				l.getWorld().dropItemNaturally(l, new ItemStack(Material.COOKED_RABBIT, 4));
				l.getWorld().dropItemNaturally(l, new ItemStack(Material.COOKED_SALMON, 4));
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.LUCKY;
			}
		});

		registerSuprise(new Surprise() {
			@Override
			public String getName() {
				return "Golden Apples";
			}

			@Override
			public void activate(Player p, Location l) {
				l.getWorld().dropItemNaturally(l, new ItemStack(Material.ENCHANTED_GOLDEN_APPLE));
				l.getWorld().dropItemNaturally(l, new ItemStack(Material.GOLDEN_APPLE, 3));
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.LUCKY;
			}
		});

		registerSuprise(new Surprise() {
			@Override
			public String getName() {
				return "Diamond Block with Lightning";
			}

			@Override
			public void activate(Player p, Location l) {
				l.getBlock().setType(Material.DIAMOND_BLOCK);
				l.getWorld().strikeLightning(l.add(0, 1, 0));
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.LUCKY;
			}
		});

		registerSuprise(new Surprise() {
			@Override
			public String getName() {
				return "I am Groot";
			}

			@Override
			public void activate(Player p, Location l) {
				l.getBlock().getRelative(BlockFace.DOWN).setType(Material.DIRT);
				l.getBlock().setType(Material.OAK_SAPLING);
				p.sendTitle("", ChatColor.translateAlternateColorCodes('&', "&6I am Groot!"), 10, 20, 10);
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.LUCKY;
			}
		});

		registerSuprise(new Surprise() {
			@Override
			public String getName() {
				return "Raw Food";
			}

			@Override
			public void activate(Player p, Location l) {
				l.getWorld().dropItemNaturally(l, new ItemStack(Material.BEEF, 4));
				l.getWorld().dropItemNaturally(l, new ItemStack(Material.CHICKEN, 4));
				l.getWorld().dropItemNaturally(l, new ItemStack(Material.PORKCHOP, 4));
				l.getWorld().dropItemNaturally(l, new ItemStack(Material.COD, 4));
				l.getWorld().dropItemNaturally(l, new ItemStack(Material.SALMON, 4));
				l.getWorld().dropItemNaturally(l, new ItemStack(Material.RABBIT, 4));
				l.getWorld().dropItemNaturally(l, new ItemStack(Material.MUTTON, 4));
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.LUCKY;
			}
		});

		registerSuprise(new Surprise() {
			@Override
			public String getName() {
				return "Fish";
			}

			@Override
			public void activate(Player p, Location l) {
				FireworkShow.launchRandom(p, 3);
				l.getWorld().dropItemNaturally(l, new ItemStack(Material.COD));
				l.getWorld().dropItemNaturally(l, new ItemStack(Material.SALMON));
				l.getWorld().dropItemNaturally(l, new ItemStack(Material.PUFFERFISH));
				l.getWorld().dropItemNaturally(l, new ItemStack(Material.TROPICAL_FISH));
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.LUCKY;
			}
		});

		registerSuprise(new Surprise() {
			@Override
			public String getName() {
				return "Stained Clay Pillar with Diamond Block on top";
			}

			@Override
			public void activate(Player p, Location l) {
				for (int i = 0; i < 8; i++) {
					l.getWorld().spawnFallingBlock(l.add(0, (i + 1) * 4, 0), MaterialHelper.TerracottaColours[i].createBlockData());
				}
				l.getWorld().spawnFallingBlock(l.add(0, 9 * 4, 0), Material.DIAMOND_BLOCK.createBlockData());
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.LUCKY;
			}
		});

		registerSuprise(new Surprise() {
			@Override
			public String getName() {
				return "Charged Creeper";
			}

			@Override
			public void activate(Player p, Location l) {
				l.getWorld().spawnEntity(l, EntityType.CREEPER);
				l.getWorld().strikeLightning(l);
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.UNLUCKY;
			}
		});

		registerSuprise(new Surprise() {
			@Override
			public String getName() {
				return "Witch and Bats";
			}

			@Override
			public void activate(Player p, Location l) {
				l.getWorld().spawnEntity(l, EntityType.WITCH);
				for (int i = 0; i < 16; i++) {
					l.getWorld().spawnEntity(l, EntityType.BAT);
				}
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.UNLUCKY;
			}
		});

		registerSuprise(new Surprise() {
			@Override
			public String getName() {
				return "Emerald Block";
			}

			@Override
			public void activate(Player p, Location l) {
				l.getWorld().spawnFallingBlock(l.add(0, 5, 0), Material.EMERALD_BLOCK.createBlockData());
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.LUCKY;
			}
		});

		registerSuprise(new Surprise() {
			@Override
			public String getName() {
				return "Iron Block";
			}

			@Override
			public void activate(Player p, Location l) {
				l.getWorld().spawnFallingBlock(l.add(0, 5, 0), Material.IRON_BLOCK.createBlockData());
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.LUCKY;
			}
		});

		registerSuprise(new Surprise() {
			@Override
			public String getName() {
				return "Explosion";
			}

			@Override
			public void activate(Player p, Location l) {
				l.getWorld().createExplosion(l, 7F);
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.UNLUCKY;
			}
		});

		registerSuprise(new Surprise() {
			@Override
			public String getName() {
				return "Void Hole";
			}

			@Override
			public void activate(Player p, Location l) {
				for (int x = p.getLocation().getBlockX() - 1; x <= p.getLocation().getBlockX() + 1; x++) {
					for (int z = p.getLocation().getBlockZ() - 1; z <= p.getLocation().getBlockZ() + 1; z++) {
						for (int y = p.getLocation().getBlockY() + 1; y >= 0; y--) {
							l.getWorld().getBlockAt(x, y, z).setType(Material.AIR);
						}
					}
				}
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.UNLUCKY;
			}
		});

		registerSuprise(new Surprise() {
			@Override
			public String getName() {
				return "Anvil Rain";
			}

			@Override
			public void activate(Player p, Location l) {
				for (int x = p.getLocation().getBlockX() - 1; x <= p.getLocation().getBlockX() + 1; x++) {
					for (int z = p.getLocation().getBlockZ() - 1; z <= p.getLocation().getBlockZ() + 1; z++) {
						for (int y = p.getLocation().getBlockY() - 1; y <= p.getLocation().getBlockY() + 2; y++) {
							if (y == p.getLocation().getBlockY() - 1) p.getWorld().getBlockAt(x, y, z).setType(Material.OBSIDIAN);
							else if (!(p.getLocation().getBlockX() == x && p.getLocation().getBlockZ() == z)) p.getWorld().getBlockAt(x, y, z).setType(Material.IRON_BARS);
						}
					}
				}
				p.getWorld().spawnFallingBlock(p.getLocation().add(0, 16, 0), Material.ANVIL.createBlockData());
				p.getWorld().spawnFallingBlock(p.getLocation().add(0, 24, 0), Material.ANVIL.createBlockData());
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.UNLUCKY;
			}
		});

		registerSuprise(new Surprise() {
			@Override
			public String getName() {
				return "Enclosed Water Pool";
			}

			@Override
			public void activate(Player p, Location l) {
				for (int x = p.getLocation().getBlockX() - 1; x <= p.getLocation().getBlockX() + 1; x++) {
					for (int z = p.getLocation().getBlockZ() - 1; z <= p.getLocation().getBlockZ() + 1; z++) {
						for (int y = p.getLocation().getBlockY() - 1; y <= p.getLocation().getBlockY() + 2; y++) {
							if (y == p.getLocation().getBlockY() - 1 || y == p.getLocation().getBlockY() + 2) p.getWorld().getBlockAt(x, y, z).setType(Material.OBSIDIAN);
							else if (p.getLocation().getBlockX() == x && p.getLocation().getBlockZ() == z) {
								p.getWorld().getBlockAt(x, y, z).setType(Material.WATER);
							}
							else p.getWorld().getBlockAt(x, y, z).setType(Material.OBSIDIAN);
						}
					}
				}
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.UNLUCKY;
			}
		});

		registerSuprise(new Surprise() {

			@Override
			public String getName() {
				return "TNT Rain";
			}

			@Override
			public void activate(Player p, Location l) {
				l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.PRIMED_TNT).setVelocity(new Vector(0, 0.5, 0.15));
				l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.PRIMED_TNT).setVelocity(new Vector(0.15, 0.5, 0.15));
				l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.PRIMED_TNT).setVelocity(new Vector(0.15, 0.5, 0));

				l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.PRIMED_TNT).setVelocity(new Vector(0, 0.5, -0.15));
				l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.PRIMED_TNT).setVelocity(new Vector(-0.15, 0.5, -0.15));
				l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.PRIMED_TNT).setVelocity(new Vector(-0.15, 0.5, 0));

				l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.PRIMED_TNT).setVelocity(new Vector(-0.15, 0.5, 0.15));
				l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.PRIMED_TNT).setVelocity(new Vector(0.15, 0.5, -0.15));
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.UNLUCKY;
			}
		});

		registerSuprise(new Surprise() {

			@Override
			public String getName() {
				return "Tamed Dogs";
			}

			@Override
			public void activate(Player p, Location l) {
				for (int i = 0; i < 8; i++) {
					Wolf dog = (Wolf) l.getWorld().spawnEntity(l.add(CSCoreLib.randomizer().nextInt(4) - CSCoreLib.randomizer().nextInt(8), 1, CSCoreLib.randomizer().nextInt(4) - CSCoreLib.randomizer().nextInt(8)), EntityType.WOLF);
					dog.setAdult();
					dog.setCollarColor(DyeColor.values()[CSCoreLib.randomizer().nextInt(DyeColor.values().length)]);
					dog.setOwner(p);
				}
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.LUCKY;
			}
		});

		registerSuprise(new Surprise() {

			@Override
			public String getName() {
				return "Tamed Cats";
			}

			@Override
			public void activate(Player p, Location l) {
				for (int i = 0; i < 8; i++) {
					Cat cat = (Cat) l.getWorld().spawnEntity(l.add(CSCoreLib.randomizer().nextInt(4) - CSCoreLib.randomizer().nextInt(8), 1, CSCoreLib.randomizer().nextInt(4) - CSCoreLib.randomizer().nextInt(8)), EntityType.CAT);
					cat.setAdult();
					cat.setOwner(p);
					cat.setCatType(Type.values()[CSCoreLib.randomizer().nextInt(Type.values().length)]);
				}
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.LUCKY;
			}
		});

		registerSuprise(new Surprise() {

			@Override
			public String getName() {
				return "Mister Rainbow";
			}

			@Override
			public void activate(Player p, Location l) {
				String string = "Mr. Rainbow";
				ChatColor[] colors = new ChatColor[] {ChatColor.AQUA, ChatColor.BLUE, ChatColor.DARK_AQUA, ChatColor.DARK_BLUE, ChatColor.DARK_GREEN, ChatColor.DARK_PURPLE, ChatColor.DARK_RED, ChatColor.GOLD, ChatColor.GREEN, ChatColor.LIGHT_PURPLE, ChatColor.RED, ChatColor.YELLOW};
				StringBuilder name = new StringBuilder();
				for (int i = 0; i < string.length(); i++) {
					name.append(colors[CSCoreLib.randomizer().nextInt(colors.length)]);
					name.append(string.charAt(i));
				}
				for (int i = 0; i < 8; i++) {
					Sheep sheep = (Sheep) l.getWorld().spawnEntity(l, EntityType.SHEEP);
					sheep.setAdult();
					sheep.setColor(DyeColor.values()[CSCoreLib.randomizer().nextInt(DyeColor.values().length)]);
					sheep.setCustomName(name.toString());
					sheep.setCustomNameVisible(true);
				}
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.LUCKY;
			}
		});

		registerSuprise(new Surprise() {

			@Override
			public String getName() {
				return "Flying Creepers";
			}

			@Override
			public void activate(Player p, Location l) {
				for (int i = 0; i < 4; i++) {
					Bat bat = (Bat) l.getWorld().spawnEntity(l, EntityType.BAT);
					bat.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999999, 255));
					bat.getPassengers().add(l.getWorld().spawnEntity(l, EntityType.CREEPER));
				}
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.UNLUCKY;
			}
		});

		registerSuprise(new Surprise() {

			@Override
			public String getName() {
				return "Flying TNT";
			}

			@Override
			public void activate(Player p, Location l) {
				for (int i = 0; i < 4; i++) {
					Bat bat = (Bat) l.getWorld().spawnEntity(l, EntityType.BAT);
					bat.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999999, 255));
					bat.getPassengers().add(l.getWorld().spawnEntity(l, EntityType.PRIMED_TNT));
				}
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.UNLUCKY;
			}
		});

		registerSuprise(new Surprise() {

			@Override
			public String getName() {
				return "Valuables";
			}

			@Override
			public void activate(Player p, Location l) {
				FireworkShow.launchRandom(p, 3);
				l.getWorld().dropItemNaturally(l, new ItemStack(Material.EMERALD, 4));
				l.getWorld().dropItemNaturally(l, new ItemStack(Material.DIAMOND, 4));
				l.getWorld().dropItemNaturally(l, new ItemStack(Material.GOLD_INGOT, 4));
				l.getWorld().dropItemNaturally(l, new ItemStack(Material.IRON_INGOT, 4));
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.LUCKY;
			}
		});

		registerSuprise(new Surprise() {

			@Override
			public String getName() {
				return "Normal and Fake Diamond Block";
			}

			@Override
			public void activate(Player p, Location l) {
				l.getBlock().getRelative(BlockFace.UP).setType(Material.DIAMOND_BLOCK);
				l.getBlock().setType(Material.DIAMOND_BLOCK);
				p.sendTitle("", ChatColor.translateAlternateColorCodes('&', "&7&oOne is real, one is not..."), 10, 20, 10);
				BlockStorage.store(CSCoreLib.randomizer().nextInt(10) < 5? l.getBlock().getRelative(BlockFace.UP): l.getBlock(), unluckyblock);
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.UNLUCKY;
			}
		});

		registerSuprise(new Surprise() {

			@Override
			public String getName() {
				return "Lucky Sword";
			}

			@Override
			public void activate(Player p, Location l) {
				l.getWorld().dropItemNaturally(l, new CustomItem(Material.GOLDEN_SWORD, "&e&lLucky Sword", new String[] {"DAMAGE_ALL-10", "LOOT_BONUS_MOBS-10", "FIRE_ASPECT-5", "DURABILITY-10"}, 0));
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.LUCKY;
			}
		});

		registerSuprise(new Surprise() {

			@Override
			public String getName() {
				return "Lucky Pickaxe";
			}

			@Override
			public void activate(Player p, Location l) {
				l.getWorld().dropItemNaturally(l, new CustomItem(Material.GOLDEN_PICKAXE, "&e&lLucky Pickaxe", new String[] {"DIG_SPEED-10", "LOOT_BONUS_BLOCKS-10", "DURABILITY-10"}, 0));
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.LUCKY;
			}
		});

		registerSuprise(new Surprise() {

			@Override
			public String getName() {
				return "Lucky Axe";
			}

			@Override
			public void activate(Player p, Location l) {
				l.getWorld().dropItemNaturally(l, new CustomItem(Material.GOLDEN_AXE, "&e&lLucky Axe", new String[] {"DAMAGE_ALL-10", "DIG_SPEED-10", "LOOT_BONUS_BLOCKS-10", "DURABILITY-10"}, 0));
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.LUCKY;
			}
		});

		registerSuprise(new Surprise() {

			@Override
			public String getName() {
				return "XP Rain";
			}

			@Override
			public void activate(Player p, Location l) {
				l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.THROWN_EXP_BOTTLE).setVelocity(new Vector(0, 0.5, 0.15));
				l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.THROWN_EXP_BOTTLE).setVelocity(new Vector(0.15, 0.5, 0.15));
				l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.THROWN_EXP_BOTTLE).setVelocity(new Vector(0.15, 0.5, 0));

				l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.THROWN_EXP_BOTTLE).setVelocity(new Vector(0, 0.5, -0.15));
				l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.THROWN_EXP_BOTTLE).setVelocity(new Vector(-0.15, 0.5, -0.15));
				l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.THROWN_EXP_BOTTLE).setVelocity(new Vector(-0.15, 0.5, 0));

				l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.THROWN_EXP_BOTTLE).setVelocity(new Vector(-0.15, 0.5, 0.15));
				l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.THROWN_EXP_BOTTLE).setVelocity(new Vector(0.15, 0.5, -0.15));
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.LUCKY;
			}
		});

		registerSuprise(new Surprise() {

			@Override
			public String getName() {
				return "Chicken Rain";
			}

			@Override
			public void activate(Player p, Location l) {
				l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.EGG).setVelocity(new Vector(0, 0.5, 0.15));
				l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.EGG).setVelocity(new Vector(0.15, 0.5, 0.15));
				l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.EGG).setVelocity(new Vector(0.15, 0.5, 0));

				l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.EGG).setVelocity(new Vector(0, 0.5, -0.15));
				l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.EGG).setVelocity(new Vector(-0.15, 0.5, -0.15));
				l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.EGG).setVelocity(new Vector(-0.15, 0.5, 0));

				l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.EGG).setVelocity(new Vector(-0.15, 0.5, 0.15));
				l.getWorld().spawnEntity(l.add(0, 2, 0), EntityType.EGG).setVelocity(new Vector(0.15, 0.5, -0.15));
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.LUCKY;
			}
		});

		registerSuprise(new Surprise() {

			@Override
			public String getName() {
				return "Bryan";
			}

			@Override
			public void activate(Player p, Location l) {
				Zombie zombie = (Zombie) l.getWorld().spawnEntity(l, EntityType.ZOMBIE);
				zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50D);
				zombie.setHealth(50D);
				zombie.getEquipment().setItemInMainHand(new CustomItem(Material.GOLDEN_AXE, "&e&lLucky Axe", new String[] {"DAMAGE_ALL-10", "DIG_SPEED-10", "LOOT_BONUS_BLOCKS-10", "DURABILITY-10"}, 0));
				zombie.getEquipment().setItemInMainHandDropChance(0F);
				zombie.setCanPickupItems(false);
				zombie.setCustomName(ChatColor.translateAlternateColorCodes('&', "&eBryan"));
				zombie.setCustomNameVisible(true);
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.UNLUCKY;
			}
		});

		registerSuprise(new Surprise() {

			@Override
			public String getName() {
				return "Walshrus";
			}

			@Override
			public void activate(Player p, Location l) {
				Zombie zombie = (Zombie) l.getWorld().spawnEntity(l, EntityType.ZOMBIE);
				zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40D);
				zombie.setHealth(40D);
				try {
					zombie.getEquipment().setHelmet(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzk2NmYwZWJkNzdmMWJjZDY1NmZhMmRjM2VmMDMwM2UyNmE2YTNkZTQ5OGMzOTk5ZDM5ZmRjYWNjNWY1YWQifX19"));
				} catch (Exception e) {
					e.printStackTrace();
				}
				zombie.getEquipment().setHelmetDropChance(0F);
				zombie.getEquipment().setItemInMainHand(new CustomItem(Material.GOLDEN_SWORD, "&e&lLucky Sword", new String[] {"DAMAGE_ALL-10", "LOOT_BONUS_MOBS-10", "FIRE_ASPECT-5", "DURABILITY-10"}, 0));
				zombie.getEquipment().setItemInMainHandDropChance(0F);
				zombie.setCanPickupItems(false);
				zombie.setCustomName(ChatColor.translateAlternateColorCodes('&', "&4Walshrus"));
				zombie.setCustomNameVisible(true);
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.UNLUCKY;
			}
		});

		registerSuprise(new Surprise() {

			@Override
			public String getName() {
				return "Lucky Baton";
			}

			@Override
			public void activate(Player p, Location l) {
				l.getWorld().dropItemNaturally(l, new CustomItem(Material.BLAZE_ROD, "&e&lLucky Stick", new String[] {"KNOCKBACK-10"}, 0));
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.LUCKY;
			}
		});

		registerSuprise(new Surprise() {

			@Override
			public String getName() {
				return "Lucky Helmet";
			}

			@Override
			public void activate(Player p, Location l) {
				l.getWorld().dropItemNaturally(l, new CustomItem(Material.DIAMOND_HELMET, "&e&lLucky Helmet", new String[] {"PROTECTION_ENVIRONMENTAL-10", "PROTECTION_PROJECTILE-10", "PROTECTION_EXPLOSIONS-10", "THORNS-10", "DURABILITY-10"}, 0));
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.LUCKY;
			}
		});

		registerSuprise(new Surprise() {

			@Override
			public String getName() {
				return "Lucky Chestplate";
			}

			@Override
			public void activate(Player p, Location l) {
				l.getWorld().dropItemNaturally(l, new CustomItem(Material.DIAMOND_CHESTPLATE, "&e&lLucky Chestplate", new String[] {"PROTECTION_ENVIRONMENTAL-10", "PROTECTION_PROJECTILE-10", "PROTECTION_EXPLOSIONS-10", "THORNS-10", "DURABILITY-10"}, 0));
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.LUCKY;
			}
		});

		registerSuprise(new Surprise() {

			@Override
			public String getName() {
				return "Lucky Leggings";
			}

			@Override
			public void activate(Player p, Location l) {
				l.getWorld().dropItemNaturally(l, new CustomItem(Material.DIAMOND_LEGGINGS, "&e&lLucky Leggings", new String[] {"PROTECTION_ENVIRONMENTAL-10", "PROTECTION_PROJECTILE-10", "PROTECTION_EXPLOSIONS-10", "THORNS-10", "DURABILITY-10"}, 0));
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.LUCKY;
			}
		});

		registerSuprise(new Surprise() {

			@Override
			public String getName() {
				return "Lucky Boots";
			}

			@Override
			public void activate(Player p, Location l) {
				l.getWorld().dropItemNaturally(l, new CustomItem(Material.DIAMOND_BOOTS, "&e&lLucky Boots", new String[] {"PROTECTION_ENVIRONMENTAL-10", "PROTECTION_PROJECTILE-10", "PROTECTION_EXPLOSIONS-10", "THORNS-10", "DURABILITY-10"}, 0));
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.LUCKY;
			}
		});

		registerSuprise(new Surprise() {

			@Override
			public String getName() {
				return "Good Potions";
			}

			@Override
			public void activate(Player p, Location l) {
				l.getWorld().dropItemNaturally(l, createPotion(Color.FUCHSIA, new PotionEffect(PotionEffectType.REGENERATION, 45, 0), true));
				l.getWorld().dropItemNaturally(l, createPotion(Color.SILVER, new PotionEffect(PotionEffectType.SPEED, 180, 0), true));
				l.getWorld().dropItemNaturally(l, createPotion(Color.ORANGE, new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 180, 0), true));
				l.getWorld().dropItemNaturally(l, createPotion(Color.RED, new PotionEffect(PotionEffectType.HEAL, 0, 0), true));
				l.getWorld().dropItemNaturally(l, createPotion(Color.NAVY, new PotionEffect(PotionEffectType.NIGHT_VISION, 180, 0), true));
				l.getWorld().dropItemNaturally(l, createPotion(Color.MAROON, new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 180, 0), true));
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.LUCKY;
			}
		});

		registerSuprise(new Surprise() {

			@Override
			public String getName() {
				return "Bad Potions";
			}

			@Override
			public void activate(Player p, Location l) {
				l.getWorld().dropItemNaturally(l, createPotion(Color.GREEN, new PotionEffect(PotionEffectType.POISON, 45, 0), false));
				l.getWorld().dropItemNaturally(l, createPotion(Color.PURPLE, new PotionEffect(PotionEffectType.WEAKNESS, 90, 0), false));
				l.getWorld().dropItemNaturally(l, createPotion(Color.GRAY, new PotionEffect(PotionEffectType.SLOW, 90, 0), false));
				l.getWorld().dropItemNaturally(l, createPotion(Color.MAROON, new PotionEffect(PotionEffectType.HARM, 0, 0), false));
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.LUCKY;
			}
		});

		registerSuprise(new Surprise() {

			@Override
			public String getName() {
				return "Dyes";
			}

			@Override
			public void activate(Player p, Location l) {
				l.getWorld().dropItemNaturally(l, new ItemStack(Material.CYAN_DYE));
				l.getWorld().dropItemNaturally(l, new ItemStack(Material.LIGHT_BLUE_DYE));
				l.getWorld().dropItemNaturally(l, new ItemStack(Material.LIME_DYE));
				l.getWorld().dropItemNaturally(l, new ItemStack(Material.MAGENTA_DYE));
				l.getWorld().dropItemNaturally(l, new ItemStack(Material.ORANGE_DYE));
				l.getWorld().dropItemNaturally(l, new ItemStack(Material.PINK_DYE));
				l.getWorld().dropItemNaturally(l, new ItemStack(Material.PURPLE_DYE));
				p.sendTitle(p.getName(), ChatColor.translateAlternateColorCodes('&', " has almost &b&odyed"), 10, 20, 10);
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.LUCKY;
			}
		});

		registerSuprise(new Surprise() {

			@Override
			public String getName() {
				return "Hay there";
			}

			@Override
			public void activate(Player p, Location l) {
				l.getWorld().dropItemNaturally(l, new ItemStack(Material.WHEAT));
				p.sendTitle(ChatColor.translateAlternateColorCodes('&', "&bHay &rthere,"), "how's it going?", 10, 20, 10);
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.LUCKY;
			}
		});

		registerSuprise(new Surprise() {

			@Override
			public String getName() {
				return "Cookies";
			}

			@Override
			public void activate(Player p, Location l) {
				l.getWorld().dropItemNaturally(l, new ItemStack(Material.COOKIE));
				p.sendTitle("", ChatColor.translateAlternateColorCodes('&',"&bCOOOKKKKIIIIIEESSS!!!!"), 10, 20, 10);
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.LUCKY;
			}
		});

		registerSuprise(new Surprise() {

			@Override
			public String getName() {
				return "Cake";
			}

			@Override
			public void activate(Player p, Location l) {
				l.getBlock().setType(Material.CAKE);
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.LUCKY;
			}
		});

		registerSuprise(new Surprise() {

			@Override
			public String getName() {
				return "jeb_ Sheep";
			}

			@Override
			public void activate(Player p, Location l) {
				Sheep sheep = (Sheep) l.getWorld().spawnEntity(l, EntityType.SHEEP);
				sheep.setCustomName("jeb_");
				sheep.setCustomNameVisible(true);
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.LUCKY;
			}
		});

		registerSuprise(new Surprise() {

			@Override
			public String getName() {
				return "Reaper";
			}

			@Override
			public void activate(Player p, Location l) {
				Zombie zombie = (Zombie) l.getWorld().spawnEntity(l, EntityType.ZOMBIE);
				zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50D);
				zombie.setHealth(50D);
				try {
					zombie.getEquipment().setHelmet(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTkzN2FmMjYzMzI2ZTJiNDA5MDQyNzFiODMxYzNiMTc2ZWEyMWYwMTg2YmZhZjRlMTZlZWUxZTI4OWRkYWQ4In19fQ=="));
				} catch (Exception e) {
					e.printStackTrace();
				}
				zombie.getEquipment().setHelmetDropChance(0F);
				zombie.getEquipment().setItemInMainHand(new CustomItem(Material.IRON_HOE, "&e&lLucky Hoe", new String[] {"DAMAGE_ALL-10", "LOOT_BONUS_MOBS-10", "FIRE_ASPECT-5", "DURABILITY-10"}, 0));
				zombie.getEquipment().setItemInMainHandDropChance(0F);
				zombie.setCanPickupItems(false);
				zombie.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999999, 255));
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.UNLUCKY;
			}
		});

		registerSuprise(new Surprise() {

			@Override
			public String getName() {
				return "Up up and away";
			}

			@Override
			public void activate(Player p, Location l) {
				p.sendTitle("", ChatColor.translateAlternateColorCodes('&', "&bUp up and away!"), 10, 20, 10);
				p.setVelocity(new Vector(0, 2.75, 0));
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.UNLUCKY;
			}
		});

		registerSuprise(new Surprise() {

			@Override
			public String getName() {
				return "Webs";
			}

			@Override
			public void activate(Player p, Location l) {
				p.getLocation().getBlock().setType(Material.COBWEB);
				p.getEyeLocation().getBlock().setType(Material.COBWEB);
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.UNLUCKY;
			}
		});

		registerSuprise(new Surprise() {

			@Override
			public String getName() {
				return "Giant Slime";
			}

			@Override
			public void activate(Player p, Location l) {
				Slime slime = (Slime) l.getWorld().spawnEntity(l, EntityType.SLIME);
				slime.setSize(7);
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.UNLUCKY;
			}
		});

		registerSuprise(new Surprise() {

			@Override
			public String getName() {
				return "Villager Number X";
			}

			@Override
			public void activate(Player p, Location l) {
				for (int i = 0; i < 8; i++) {
					Villager v = (Villager) l.getWorld().spawnEntity(l, EntityType.VILLAGER);
					v.setAdult();
					v.setCustomName("Villager #" + (6 + CSCoreLib.randomizer().nextInt(30)));
					v.setCustomNameVisible(true);
				}
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.LUCKY;
			}
		});

		registerSuprise(new Surprise() {

			@Override
			public String getName() {
				return "PotatOS";
			}

			@Override
			public void activate(Player p, Location l) {
				l.getWorld().dropItemNaturally(l, new CustomItem(Material.POTATO, "&e&lPotatOS"));
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.LUCKY;
			}
		});

		registerSuprise(new Surprise() {

			@Override
			public String getName() {
				return "Iron Golem Invasion";
			}

			@Override
			public void activate(Player p, Location l) {
				for (int i = 0; i < 10; i++) {
					IronGolem golem = (IronGolem) l.getWorld().spawnEntity(l, EntityType.IRON_GOLEM);
					golem.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 999999999, 1));
					golem.setTarget(p);
				}
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.PANDORA;
			}
		});

		registerSuprise(new Surprise() {

			@Override
			public String getName() {
				return "Jerry the Slime";
			}

			@Override
			public void activate(Player p, Location l) {
				Slime slime = (Slime) l.getWorld().spawnEntity(l, EntityType.SLIME);
				slime.setSize(1);
				slime.setCustomName(ChatColor.translateAlternateColorCodes('&', "&2Jerry"));
				slime.setCustomNameVisible(true);
			}

			@Override
			public LuckLevel getLuckLevel() {
				return LuckLevel.LUCKY;
			}
		});
	}

	private static ItemStack createPotion(Color color, PotionEffect effect, boolean lucky) {
		ItemStack potion = new ItemStack(Material.POTION);
		PotionMeta pm = (PotionMeta) potion.getItemMeta();
		pm.setDisplayName(ChatColor.translateAlternateColorCodes('&', (lucky ? "&6Lucky" : "&cUnlucky") + " potion"));
		pm.setColor(color);
		pm.addCustomEffect(effect, false);
		potion.setItemMeta(pm);
		return potion;
	}

	public static void registerSuprise(Surprise surprise) {
		if (cfg.contains("events." + surprise.getName())) {
			if (cfg.getBoolean("events." + surprise.getName())) {
				surprises.add(surprise);
				List<Surprise> list = new ArrayList<Surprise>();
				if (luckylist.containsKey(surprise.getLuckLevel())) list = luckylist.get(surprise.getLuckLevel());
				list.add(surprise);
				luckylist.put(surprise.getLuckLevel(), list);
			}
		}
		else {
			cfg.setValue("events." + surprise.getName(), true);
			cfg.save();
			surprises.add(surprise);
			List<Surprise> list = new ArrayList<Surprise>();
			if (luckylist.containsKey(surprise.getLuckLevel())) list = luckylist.get(surprise.getLuckLevel());
			list.add(surprise);
			luckylist.put(surprise.getLuckLevel(), list);
		}
	}

	public static void spawnLuckyBlock(Block b) {
		b.setType(Material.PLAYER_HEAD);
		Rotatable s = (Rotatable) b.getBlockData();
		s.setRotation(bf[new Random().nextInt(bf.length)]);
		b.setBlockData(s);
		try {
			CustomSkull.setSkull(b, texture);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		BlockStorage.store(b, SlimefunLuckyBlocks.luckyblock);
	}

	@Override
	public void onDisable() {
		luckylist = null;
		surprises = null;
		luckyblock = null;
		cfg = null;
		bf = null;
	}

}
