package net.pinne.netherreactormod.procedures;

import net.pinne.netherreactormod.NetherreactormodMod;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import java.util.Random;


public class NetherReactorActiveProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		NetherreactormodMod.queueServerWork(30, () -> {
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						"/fill ~1 ~-1 ~1 ~-1 ~-1 ~-1 netherreactormod:glowing_obsidian replace minecraft:cobblestone");
			NetherreactormodMod.queueServerWork(20, () -> {
				if (world instanceof ServerLevel _level)
					_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
							"/fill ~1 ~ ~1 ~-1 ~ ~-1 netherreactormod:glowing_obsidian replace minecraft:cobblestone");
				NetherreactormodMod.queueServerWork(20, () -> {
					if (world instanceof ServerLevel _level)
						_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
								"/fill ~1 ~1 ~1 ~-1 ~1 ~-1 netherreactormod:glowing_obsidian replace minecraft:cobblestone");
					NetherreactormodMod.queueServerWork(60, () -> {
						if (world instanceof ServerLevel _level)
							_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
									"/fill ~1 ~-1 ~1 ~-1 ~-1 ~-1 netherreactormod:glowing_obsidian replace minecraft:gold_block");
						NetherreactormodMod.queueServerWork(40, () -> spawnLootRepeatedly(world, x, y, z, 0, 0));
                    });
                });
            });
        });
    }
    private static void spawnLootRepeatedly(LevelAccessor world, double x, double y, double z, int count, int piglinSpawnCount) {
        if (count >= 9) return; // Stop after 9 iterations

        if (world instanceof ServerLevel _level) {
            Random random = new Random();
            for (int i = 0; i < 5; i++) {
                int randomX, randomZ;
                do {
                    randomX = (int) x + (random.nextInt(13) - 6);
                    randomZ = (int) z + (random.nextInt(13) - 6);
                } while (Math.abs(randomX - x) <= 1 && Math.abs(randomZ - z) <= 1);

                int randomY = (int) y - 1;

                _level.getServer().getCommands().performPrefixedCommand(
                    new CommandSourceStack(
                        CommandSource.NULL,
                        new Vec3(randomX, randomY, randomZ),
                        Vec2.ZERO,
                        _level,
                        4,
                        "",
                        Component.literal(""),
                        _level.getServer(),
                        null
                    ).withSuppressedOutput(),
                    "/loot spawn " + randomX + " " + randomY + " " + randomZ + " loot netherreactormod:gameplay/nether_reactor_loot"
                );
            }
        	if (piglinSpawnCount < 4) {
                int piglinCount = 1 + random.nextInt(4);
                for (int i = 0; i < piglinCount; i++) {
                    int piglinX, piglinZ;
                    do {
                        piglinX = (int) x + (random.nextInt(13) - 6);
                        piglinZ = (int) z + (random.nextInt(13) - 6);
                    } while (Math.abs(piglinX - x) <= 1 && Math.abs(piglinZ - z) <= 1);

                    int piglinY = (int) y - 1;

                    _level.getServer().getCommands().performPrefixedCommand(
                        new CommandSourceStack(
                            CommandSource.NULL,
                            new Vec3(piglinX, piglinY, piglinZ),
                            Vec2.ZERO,
                            _level,
                            4,
                            "",
                            Component.literal(""),
                            _level.getServer(),
                            null
                        ).withSuppressedOutput(),
                        "/summon minecraft:zombified_piglin " + piglinX + " " + piglinY + " " + piglinZ
                    );
                }
                piglinSpawnCount++; // Increment piglin spawn count
            }
        }

       	final int nextCount = count + 1;
    	final int nextPiglinSpawnCount = piglinSpawnCount;
        NetherreactormodMod.queueServerWork(80, () -> spawnLootRepeatedly(world, x, y, z, nextCount, nextPiglinSpawnCount));
    }
}