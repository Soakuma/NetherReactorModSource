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

public class NetherReatorFinishProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world instanceof ServerLevel _level)
			_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
					"/fill ~1 ~1 ~1 ~-1 ~1 ~-1 minecraft:obsidian");
		NetherreactormodMod.queueServerWork(20, () -> {
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						"/fill ~ ~ ~ ~ ~ ~ netherreactormod:finished_nether_reactor_core replace netherreactormod:active_nether_reactor_core");
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						"/fill ~1 ~ ~1 ~-1 ~ ~-1 minecraft:obsidian replace netherreactormod:glowing_obsidian");
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						"/fill ~1 ~ ~1 ~-1 ~ ~-1 minecraft:obsidian replace minecraft:air");
			NetherreactormodMod.queueServerWork(20, () -> {
				if (world instanceof ServerLevel _level)
					_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
							"/fill ~1 ~-1 ~1 ~-1 ~-1 ~-1 minecraft:obsidian");
				if (world instanceof ServerLevel _level) {
					_level.setDayTime(18000);
					NetherreactormodMod.queueServerWork(20, () -> {
            			damageStructure(_level, x, y, z);
        			});
				}
			});
		});
	}
	private static void damageStructure(ServerLevel level, double x, double y, double z) {
    Random random = new Random();
    int minY = (int) y - 1; // 바닥 위부터 시작
    int maxY = (int) y + 36; // 높이 34까지
    
    for (int dx = -8; dx <= 8; dx++) {
        for (int dz = -8; dz <= 8; dz++) {
            for (int dy = minY; dy <= maxY; dy++) {
                // 30% 확률로 블록 변경
                if (random.nextDouble() < 0.3) {
                    // 네더 반응기 코어 주변 3x3x3 영역은 제외
                    if (Math.abs(dx) > 1 || Math.abs(dz) > 1 || Math.abs(dy - (int)y) > 1) {
                        level.getServer().getCommands().performPrefixedCommand(
                            new CommandSourceStack(CommandSource.NULL, new Vec3(x + dx, dy, z + dz), Vec2.ZERO, level, 4, "", Component.literal(""), level.getServer(), null).withSuppressedOutput(),
                            "/execute if block " + (int)(x + dx) + " " + dy + " " + (int)(z + dz) + " minecraft:netherrack run setblock " + (int)(x + dx) + " " + dy + " " + (int)(z + dz) + " minecraft:air"
                        	);
                    	}
                	}
            	}
        	}
    	}
	}
}
