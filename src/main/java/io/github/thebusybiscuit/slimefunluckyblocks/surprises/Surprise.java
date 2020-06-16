package io.github.thebusybiscuit.slimefunluckyblocks.surprises;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface Surprise {

    String getName();

    LuckLevel getLuckLevel();

    void activate(Random random, Player p, Location l);

}
