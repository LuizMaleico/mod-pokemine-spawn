package com.tuxedo;

import com.mojang.logging.LogUtils;
import net.fabricmc.api.ModInitializer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.biome.Biome;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PokemonSpawner implements ModInitializer {

    private static final Logger LOGGER = LogUtils.getLogger();
    private static final Map<String, Biome.Category> POKEMON_BIOME_MAP = new HashMap<>();
    private static final int SPAWN_RADIUS = 2500;
    private static final double SPAWN_CHANCE = 0.08; // 8% chance

    static {
        POKEMON_BIOME_MAP.put("articuno", Biome.Category.ICY);
        POKEMON_BIOME_MAP.put("zapdos", Biome.Category.SAVANNA);
        POKEMON_BIOME_MAP.put("moltres", Biome.Category.DESERT);
        POKEMON_BIOME_MAP.put("mewtwo", Biome.Category.JUNGLE);
        POKEMON_BIOME_MAP.put("hooh", Biome.Category.MOUNTAIN);
        POKEMON_BIOME_MAP.put("lugia", Biome.Category.OCEAN);
        POKEMON_BIOME_MAP.put("raikou", Biome.Category.SAVANNA);
        POKEMON_BIOME_MAP.put("entei", Biome.Category.DESERT);
        POKEMON_BIOME_MAP.put("suicune", Biome.Category.RIVER);
        POKEMON_BIOME_MAP.put("regirock", Biome.Category.MESA);
        POKEMON_BIOME_MAP.put("regice", Biome.Category.ICY);
        POKEMON_BIOME_MAP.put("registeel", Biome.Category.PLAINS);
        POKEMON_BIOME_MAP.put("latias", Biome.Category.MOUNTAIN);
        POKEMON_BIOME_MAP.put("latios", Biome.Category.MOUNTAIN);
        POKEMON_BIOME_MAP.put("kyogre", Biome.Category.OCEAN);
        POKEMON_BIOME_MAP.put("groudon", Biome.Category.DESERT);
        POKEMON_BIOME_MAP.put("rayquaza", Biome.Category.SKY);
        POKEMON_BIOME_MAP.put("uxie", Biome.Category.RIVER);
        POKEMON_BIOME_MAP.put("mesprit", Biome.Category.RIVER);
        POKEMON_BIOME_MAP.put("azelf", Biome.Category.RIVER);
        POKEMON_BIOME_MAP.put("dialga", Biome.Category.MOUNTAIN);
        POKEMON_BIOME_MAP.put("palkia", Biome.Category.RIVER);
        POKEMON_BIOME_MAP.put("heatran", Biome.Category.MESA);
        POKEMON_BIOME_MAP.put("regigigas", Biome.Category.PLAINS);
        POKEMON_BIOME_MAP.put("giratina", Biome.Category.SWAMP);
        POKEMON_BIOME_MAP.put("cresselia", Biome.Category.FOREST);
        POKEMON_BIOME_MAP.put("zekrom", Biome.Category.MOUNTAIN);
        POKEMON_BIOME_MAP.put("reshiram", Biome.Category.DESERT);
        POKEMON_BIOME_MAP.put("kyurem", Biome.Category.ICY);
        POKEMON_BIOME_MAP.put("cobalion", Biome.Category.FOREST);
        POKEMON_BIOME_MAP.put("terrakion", Biome.Category.MOUNTAIN);
        POKEMON_BIOME_MAP.put("virizion", Biome.Category.PLAINS);
        POKEMON_BIOME_MAP.put("tornadus", Biome.Category.SKY);
        POKEMON_BIOME_MAP.put("thundurus", Biome.Category.SKY);
        POKEMON_BIOME_MAP.put("landorus", Biome.Category.PLAINS);
        POKEMON_BIOME_MAP.put("xerneas", Biome.Category.FOREST);
        POKEMON_BIOME_MAP.put("yveltal", Biome.Category.SWAMP);
        POKEMON_BIOME_MAP.put("zygarde", Biome.Category.MOUNTAIN);
        POKEMON_BIOME_MAP.put("typenull", Biome.Category.PLAINS);
        POKEMON_BIOME_MAP.put("silvally", Biome.Category.PLAINS);
        POKEMON_BIOME_MAP.put("tapukoko", Biome.Category.FOREST);
        POKEMON_BIOME_MAP.put("tapulele", Biome.Category.JUNGLE);
        POKEMON_BIOME_MAP.put("tapufini", Biome.Category.OCEAN);
        POKEMON_BIOME_MAP.put("tapubulu", Biome.Category.SAVANNA);
        POKEMON_BIOME_MAP.put("cosmog", Biome.Category.SKY);
        POKEMON_BIOME_MAP.put("cosmoem", Biome.Category.SKY);
        POKEMON_BIOME_MAP.put("solgaleo", Biome.Category.DESERT);
        POKEMON_BIOME_MAP.put("lunala", Biome.Category.SWAMP);
        POKEMON_BIOME_MAP.put("necrozma", Biome.Category.MOUNTAIN);
        POKEMON_BIOME_MAP.put("zacian", Biome.Category.FOREST);
        POKEMON_BIOME_MAP.put("zamazenta", Biome.Category.FOREST);
        POKEMON_BIOME_MAP.put("eternatus", Biome.Category.SKY);
        POKEMON_BIOME_MAP.put("kubfu", Biome.Category.MOUNTAIN);
        POKEMON_BIOME_MAP.put("urshifu", Biome.Category.MOUNTAIN);
        POKEMON_BIOME_MAP.put("regieleki", Biome.Category.DESERT);
        POKEMON_BIOME_MAP.put("regidrago", Biome.Category.MESA);
        POKEMON_BIOME_MAP.put("glastrier", Biome.Category.ICY);
        POKEMON_BIOME_MAP.put("spectrier", Biome.Category.PLAINS);
        POKEMON_BIOME_MAP.put("calyrex", Biome.Category.PLAINS);
        POKEMON_BIOME_MAP.put("enamorus", Biome.Category.SWAMP);
        POKEMON_BIOME_MAP.put("tinglu", Biome.Category.MOUNTAIN);
        POKEMON_BIOME_MAP.put("chienpao", Biome.Category.ICY);
        POKEMON_BIOME_MAP.put("wochien", Biome.Category.FOREST);
        POKEMON_BIOME_MAP.put("chiyu", Biome.Category.RIVER);
        POKEMON_BIOME_MAP.put("koraidon", Biome.Category.JUNGLE);
        POKEMON_BIOME_MAP.put("miraidon", Biome.Category.SKY);
        POKEMON_BIOME_MAP.put("okidogi", Biome.Category.FOREST);
        POKEMON_BIOME_MAP.put("munkidori", Biome.Category.JUNGLE);
        POKEMON_BIOME_MAP.put("fezandipiti", Biome.Category.FOREST);
        POKEMON_BIOME_MAP.put("ogerpon", Biome.Category.MOUNTAIN);
        POKEMON_BIOME_MAP.put("terapagos", Biome.Category.OCEAN);
        POKEMON_BIOME_MAP.put("darkrai", Biome.Category.SWAMP);
        POKEMON_BIOME_MAP.put("celebi", Biome.Category.FOREST);
    }

    @Override
    public void onInitialize() {
        LOGGER.info("Pokemon Spawner Mod initialized!");


    }

    public static void trySpawnPokemon(ServerWorld world, BlockPos playerPos) {
        Random random = Random.create();

        if (random.nextDouble() < SPAWN_CHANCE) {
            String pokemon = POKEMON_BIOME_MAP.keySet().stream().skip(random.nextInt(POKEMON_BIOME_MAP.size())).findFirst().orElse(null);

            if (pokemon != null) {
                Biome.Category category = POKEMON_BIOME_MAP.get(pokemon);
                Optional<BlockPos> biomePos = world.locateBiome(b -> b.getCategory() == category, playerPos, SPAWN_RADIUS, 8);

                biomePos.ifPresent(pos -> {
                    world.setBlockState(pos, world.getBlockState(pos)); // Simula spawn (substituir com lógica de entidade real se aplicável)
                    world.getPlayers().forEach(player -> player.sendMessage(Text.literal(pokemon + " spawned at " + category.name()), false));
                });
            }
        }
    }
}
