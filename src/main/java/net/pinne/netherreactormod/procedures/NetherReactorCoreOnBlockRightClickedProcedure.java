package net.pinne.netherreactormod.procedures;

import net.pinne.netherreactormod.NetherreactormodMod;

import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;

public class NetherReactorCoreOnBlockRightClickedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == Blocks.COBBLESTONE && (world.getBlockState(BlockPos.containing(x, y - 1, z - 1))).getBlock() == Blocks.COBBLESTONE
				&& (world.getBlockState(BlockPos.containing(x, y - 1, z + 1))).getBlock() == Blocks.COBBLESTONE && (world.getBlockState(BlockPos.containing(x + 1, y - 1, z))).getBlock() == Blocks.COBBLESTONE
				&& (world.getBlockState(BlockPos.containing(x - 1, y - 1, z))).getBlock() == Blocks.COBBLESTONE && (world.getBlockState(BlockPos.containing(x + 1, y - 1, z + 1))).getBlock() == Blocks.GOLD_BLOCK
				&& (world.getBlockState(BlockPos.containing(x + 1, y - 1, z - 1))).getBlock() == Blocks.GOLD_BLOCK && (world.getBlockState(BlockPos.containing(x - 1, y - 1, z + 1))).getBlock() == Blocks.GOLD_BLOCK
				&& (world.getBlockState(BlockPos.containing(x - 1, y - 1, z - 1))).getBlock() == Blocks.GOLD_BLOCK && (world.getBlockState(BlockPos.containing(x + 1, y, z - 1))).getBlock() == Blocks.COBBLESTONE
				&& (world.getBlockState(BlockPos.containing(x + 1, y, z + 1))).getBlock() == Blocks.COBBLESTONE && (world.getBlockState(BlockPos.containing(x - 1, y, z + 1))).getBlock() == Blocks.COBBLESTONE
				&& (world.getBlockState(BlockPos.containing(x - 1, y, z - 1))).getBlock() == Blocks.COBBLESTONE && (world.getBlockState(BlockPos.containing(x, y + 1, z + 1))).getBlock() == Blocks.COBBLESTONE
				&& (world.getBlockState(BlockPos.containing(x, y + 1, z - 1))).getBlock() == Blocks.COBBLESTONE && (world.getBlockState(BlockPos.containing(x - 1, y + 1, z))).getBlock() == Blocks.COBBLESTONE
				&& (world.getBlockState(BlockPos.containing(x + 1, y + 1, z))).getBlock() == Blocks.COBBLESTONE) {
			if (world instanceof ServerLevel _serverworld) {
				StructureTemplate template = _serverworld.getStructureManager().getOrCreate(new ResourceLocation("netherreactormod", "nether_tower"));
				if (template != null) {
					template.placeInWorld(_serverworld, BlockPos.containing(x - 8, y - 3, z - 8), BlockPos.containing(x - 8, y - 3, z - 8), new StructurePlaceSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setIgnoreEntities(false),
							_serverworld.random, 3);
				}
			}
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal((Component.translatable("block.netherreactormod.nether_reactor_core_active").getString())), false);
			NetherReactorActiveProcedure.execute(world, x, y, z);
			NetherreactormodMod.queueServerWork(900, () -> {
				NetherReatorFinishProcedure.execute(world, x, y, z);
			});
		} else {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal((Component.translatable("block.netherreactormod.nether_reactor_core_unactive").getString())), false);
		}
	}
}
