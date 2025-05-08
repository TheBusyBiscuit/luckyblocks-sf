package io.github.thebusybiscuit.slimefunluckyblocks;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;

import org.bstats.bukkit.Metrics;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Rotatable;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.libraries.dough.common.ChatColors;
import io.github.thebusybiscuit.slimefun4.libraries.dough.common.CommonPatterns;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerHead;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerSkin;
import io.github.thebusybiscuit.slimefun4.libraries.dough.updater.GitHubBuildsUpdater;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.CustomItemSurprise;
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
import io.github.thebusybiscuit.slimefunluckyblocks.surprises.unlucky.ZombiePigmenSurprise;
import me.mrCookieSlime.Slimefun.api.BlockStorage;

public class SlimefunLuckyBlocks extends JavaPlugin implements SlimefunAddon {

    private static final String TEXTURE = "b3b710b08b523bba7efba07c629ba0895ad61126d26c86beb3845603a97426c";

    private Config cfg;
    private final List<Surprise> surprises = new LinkedList<>();
    private final BlockFace[] blockfaces = { BlockFace.NORTH, BlockFace.NORTH_EAST, BlockFace.EAST, BlockFace.SOUTH_EAST, BlockFace.SOUTH, BlockFace.SOUTH_WEST, BlockFace.WEST, BlockFace.NORTH_WEST };

    @Override
    public void onEnable() {
        cfg = new Config(this);

        // Setting up bStats
        new Metrics(this, 4858);

        if (cfg.getBoolean("options.auto-update") && getDescription().getVersion().startsWith("DEV - ")) {
            new GitHubBuildsUpdater(this, getFile(), "TheBusyBiscuit/luckyblocks-sf/master").start();
        }

        ItemGroup itemGroup = new ItemGroup(new NamespacedKey(this, "lucky_blocks"), CustomItemStack.create(PlayerHead.getItemStack(PlayerSkin.fromHashCode(TEXTURE)), "&rLucky Blocks"));

        SlimefunItemStack luckyBlock = new SlimefunItemStack("LUCKY_BLOCK", TEXTURE, "&fLucky Block", "&7Luck: &f0");
        SlimefunItemStack veryLuckyBlock = new SlimefunItemStack("LUCKY_BLOCK_LUCKY", TEXTURE, "&fVery lucky Block", "&7Luck: &a+80");
        SlimefunItemStack veryUnluckyBlock = new SlimefunItemStack("LUCKY_BLOCK_UNLUCKY", TEXTURE, "&fVery unlucky Block", "&7Luck: &c-80");
        SlimefunItemStack pandorasBox = new SlimefunItemStack("PANDORAS_BOX", "86c7dde512871bd607b77e6635ad39f44f2d5b4729e60273f1b14fba9a86a", "&5Pandora\"s Box", "&7Luck: &c&oERROR");

        // @formatter:off
        new LuckyBlock(itemGroup, luckyBlock, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] { SlimefunItems.GOLD_12K.item(), SlimefunItems.GOLD_12K.item(), SlimefunItems.GOLD_12K.item(), SlimefunItems.GOLD_12K.item(), new ItemStack(Material.DISPENSER), SlimefunItems.GOLD_12K.item(), SlimefunItems.GOLD_12K.item(), SlimefunItems.GOLD_12K.item(), SlimefunItems.GOLD_12K.item() }).register(this, surprises, s -> s.getLuckLevel() != LuckLevel.PANDORA);

        new LuckyBlock(itemGroup, veryLuckyBlock, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] { null, SlimefunItems.GOLD_12K.item(), null, SlimefunItems.GOLD_12K.item(), luckyBlock.item(), SlimefunItems.GOLD_12K.item(), null, SlimefunItems.GOLD_12K.item(), null }).register(this, surprises, s -> s.getLuckLevel() == LuckLevel.LUCKY);

        new LuckyBlock(itemGroup, veryUnluckyBlock, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] { null, new ItemStack(Material.SPIDER_EYE), null, new ItemStack(Material.SPIDER_EYE), luckyBlock.item(), new ItemStack(Material.SPIDER_EYE), null, new ItemStack(Material.SPIDER_EYE), null }).register(this, surprises, s -> s.getLuckLevel() == LuckLevel.UNLUCKY);

        new LuckyBlock(itemGroup, pandorasBox, RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[] { new ItemStack(Material.OAK_PLANKS), new ItemStack(Material.LAPIS_BLOCK), new ItemStack(Material.OAK_PLANKS), new ItemStack(Material.LAPIS_BLOCK), luckyBlock.item(), new ItemStack(Material.LAPIS_BLOCK), new ItemStack(Material.OAK_PLANKS), new ItemStack(Material.LAPIS_BLOCK), new ItemStack(Material.OAK_PLANKS) }).register(this, surprises, s -> s.getLuckLevel() == LuckLevel.PANDORA);
        // @formatter:on

        new WorldGenerator(this);

        registerDefaultSurprises();
        registerCustomSurprises();

        getLogger().log(Level.INFO, "Loaded {0} different Surprises!", surprises.size());
    }

    private void registerDefaultSurprises() {
        // Lucky Surprises
        registerSurprise(new CookedFoodSurprise());
        registerSurprise(new GoldenAppleSurprise());
        registerSurprise(new DiamondBlockSurprise());
        registerSurprise(new DiamondBlockPillarSurprise());
        registerSurprise(new EmeraldBlockSurprise());
        registerSurprise(new IronBlockSurprise());
        registerSurprise(new TamedDogsSurprise());
        registerSurprise(new TamedCatsSurprise());
        registerSurprise(new ValuablesSurprise());
        registerSurprise(new LuckySwordSurprise());
        registerSurprise(new LuckyPickaxeSurprise());
        registerSurprise(new LuckyAxeSurprise());
        registerSurprise(new XPRainSurprise());
        registerSurprise(new LuckyHelmetSurprise());
        registerSurprise(new LuckyChestplateSurprise());
        registerSurprise(new LuckyLeggingsSurprise());
        registerSurprise(new LuckyBootsSurprise());
        registerSurprise(new LuckyPotionsSurprise());
        registerSurprise(new UnluckyPotionsSurprise());
        registerSurprise(new CakeSurprise());

        // Neutral Surprises
        registerSurprise(new GrootSurprise());
        registerSurprise(new RawFoodSurprise());
        registerSurprise(new FishSurprise());
        registerSurprise(new WanderingTraderSurprise());
        registerSurprise(new RainbowSheepSurprise());
        registerSurprise(new ChickenRainSurprise());
        registerSurprise(new DyeSurprise());
        registerSurprise(new HaySurprise());
        registerSurprise(new CookieSurprise());
        registerSurprise(new JebSheepSurprise());
        registerSurprise(new VillagersSurprise());
        registerSurprise(new PotatOSSurprise());
        registerSurprise(new JerrySlimeSurprise());

        // Unlucky Surprises
        registerSurprise(new ChargedCreeperSurprise());
        registerSurprise(new WitchSurprise());
        registerSurprise(new ExplosionSurprise());
        registerSurprise(new VoidHoleSurprise());
        registerSurprise(new AnvilRainSurprise());
        registerSurprise(new EnclosedWaterSurprise());
        registerSurprise(new TNTRainSurprise());
        registerSurprise(new FlyingCreeperSurprise());
        registerSurprise(new FlyingTNTSurprise());
        registerSurprise(new FakeDiamondBlock());
        registerSurprise(new BryanZombieSurprise());
        registerSurprise(new WalshrusSurprise());
        registerSurprise(new HighJumpSurprise());
        registerSurprise(new CobwebSurprise());
        registerSurprise(new GiantSlimeSurprise());
        registerSurprise(new ZombiePigmenSurprise());

        // Pandora Box Surprises
        registerSurprise(new ReapersSurprise());
        registerSurprise(new IronGolemsSurprise());
    }

    private void registerCustomSurprises() {
        // CustomItem Surprises
        if (cfg.getValue("custom") != null && !cfg.getKeys("custom").isEmpty()) {
            for (String name : cfg.getKeys("custom")) {
                LuckLevel luckLevel = LuckLevel.NEUTRAL;
                List<ItemStack> items = new ArrayList<>();
                List<String> commands = new ArrayList<>();

                if (cfg.getString("custom." + name + ".lucklevel") != null) {
                    try {
                        luckLevel = LuckLevel.valueOf(cfg.getString("custom." + name + ".lucklevel").toUpperCase());
                    } catch (IllegalArgumentException ex) {
                        getLogger().log(Level.WARNING, "Couldn\"t load lucklevel of CustomItem Surprise \"{0}\", now using NEUTRAL (default)", name);
                        getLogger().log(Level.WARNING, "Valid lucklevel types: LUCKY, NEUTRAL, UNLUCKY, PANDORA");
                    }
                }

                if (cfg.getValue("custom." + name + ".commands") != null && !cfg.getStringList("custom." + name + ".commands").isEmpty()) {
                    commands.addAll(cfg.getStringList("custom." + name + ".commands"));
                }

                if (cfg.getValue("custom." + name + ".items") != null && !cfg.getKeys("custom." + name + ".items").isEmpty()) {
                    for (String itemID : cfg.getKeys("custom." + name + ".items")) {
                        ItemStack item = null;
                        String itemPath = "custom." + name + ".items." + itemID;

                        if (cfg.getString(itemPath + ".slimefun_item") != null) {
                            String id = cfg.getString(itemPath + ".slimefun_item").toUpperCase(Locale.ROOT);
                            SlimefunItem sfItem = SlimefunItem.getById(id);

                            if (sfItem != null) {
                                item = sfItem.getItem();

                                if (cfg.getInt(itemPath + ".amount") > 1) {
                                    item.setAmount(cfg.getInt(itemPath + ".amount"));
                                }
                            } else {
                                getLogger().log(Level.WARNING, "Could not load SlimefunItem \"{0}\" to custom surprise \"{1}\"", new Object[] { id, name });
                            }
                        } else if (cfg.getString(itemPath + ".type") != null && Material.getMaterial(cfg.getString(itemPath + ".type")) != null) {
                            item = new ItemStack(Material.getMaterial(cfg.getString(itemPath + ".type")));
                            ItemMeta itemMeta = item.getItemMeta();

                            if (cfg.getInt(itemPath + ".amount") > 1) {
                                item.setAmount(cfg.getInt(itemPath + ".amount"));
                            }

                            if (cfg.getString(itemPath + ".displayname") != null) {
                                itemMeta.setDisplayName(ChatColors.color(cfg.getString(itemPath + ".displayname")));
                            }

                            if (!cfg.getStringList(itemPath + ".lore").isEmpty()) {
                                List<String> lore = new ArrayList<>();
                                for (String l : cfg.getStringList(itemPath + ".lore")) {
                                    lore.add(ChatColors.color(l));
                                }
                                itemMeta.setLore(lore);
                            }

                            if (!cfg.getStringList(itemPath + ".enchants").isEmpty()) {
                                for (String ench : cfg.getStringList(itemPath + ".enchants")) {
                                    String[] split = ench.split(":");
                                    String enchName = split[0];
                                    Enchantment enchantment = Enchantment.getByName(enchName.toUpperCase(Locale.ROOT));
                                    int level = 1;

                                    if (enchantment != null) {
                                        if (split.length == 2) {
                                            if (!CommonPatterns.NUMERIC.matcher(split[1]).matches()) {
                                                getLogger().log(Level.WARNING, "Could not set \"{0}\" enchant with level \"{1}\" for custom surprise \"{2}\"", new Object[] { enchName, split[1], name });
                                                continue;
                                            }

                                            level = Integer.parseInt(split[1]);
                                        }

                                        itemMeta.addEnchant(enchantment, level, true);
                                    } else {
                                        getLogger().log(Level.WARNING, "Could not set \"{0}\" enchant for custom surprise \"{1}\"", new Object[] { enchName, name });
                                    }
                                }
                            }

                            item.setItemMeta(itemMeta);
                        }

                        if (item != null) {
                            items.add(item);
                        }
                    }
                }
                if (!items.isEmpty() || !commands.isEmpty()) {
                    registerSurprise(new CustomItemSurprise(name, items, commands, luckLevel));
                }
            }
        }
    }

    public static ItemStack createPotion(Color color, PotionEffect effect, boolean lucky) {
        ItemStack potion = new ItemStack(lucky ? Material.POTION : Material.SPLASH_POTION);
        PotionMeta pm = (PotionMeta) potion.getItemMeta();
        pm.setDisplayName(ChatColors.color((lucky ? "&6Lucky" : "&cUnlucky") + " potion"));
        pm.setColor(color);
        pm.addCustomEffect(effect, false);
        potion.setItemMeta(pm);
        return potion;
    }

    public void registerSurprise(Surprise surprise) {
        if (surprise instanceof CustomItemSurprise) {
            if (cfg.getBoolean("custom." + surprise.getName() + ".enabled")) {
                surprises.add(surprise);
            }

            return;
        }

        if (cfg.contains("events." + surprise.getName())) {
            if (cfg.getBoolean("events." + surprise.getName())) {
                surprises.add(surprise);
            }
        } else {
            cfg.setValue("events." + surprise.getName(), true);
            cfg.save();
            surprises.add(surprise);
        }
    }

    public void spawnLuckyBlock(Block b) {
        BlockData data = Material.PLAYER_HEAD.createBlockData(bd -> {
            if (bd instanceof Rotatable) {
                Rotatable skull = (Rotatable) bd;

                BlockFace rotation = blockfaces[ThreadLocalRandom.current().nextInt(blockfaces.length)];
                skull.setRotation(rotation);
            }
        });

        b.setBlockData(data);
        PlayerHead.setSkin(b, PlayerSkin.fromHashCode(TEXTURE), true);
        BlockStorage.store(b, "LUCKY_BLOCK");

        if (getCfg().getBoolean("debug")) {
            getLogger().log(Level.INFO, "spawned lucky block at {0} {1} {2} - {3}", new Object[] { b.getX(), b.getY(), b.getZ(), b.getWorld().getName() });
        }
    }

    public Config getCfg() {
        return cfg;
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    @Override
    public String getBugTrackerURL() {
        return "https://github.com/TheBusyBiscuit/luckyblocks-sf/issues";
    }

}
