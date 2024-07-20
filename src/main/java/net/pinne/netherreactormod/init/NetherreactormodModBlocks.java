package net.pinne.netherreactormod.init;

import net.pinne.netherreactormod.block.NetherReactorCoreBlock;
import net.pinne.netherreactormod.block.GlowingObsidianBlock;
import net.pinne.netherreactormod.block.FinishedNetherReactorCoreBlock;
import net.pinne.netherreactormod.block.ActiveNetherReactorCoreBlock;
import net.pinne.netherreactormod.NetherreactormodMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

public class NetherreactormodModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, NetherreactormodMod.MODID);
	public static final RegistryObject<Block> NETHER_REACTOR_CORE = REGISTRY.register("nether_reactor_core", () -> new NetherReactorCoreBlock());
	public static final RegistryObject<Block> GLOWING_OBSIDIAN = REGISTRY.register("glowing_obsidian", () -> new GlowingObsidianBlock());
	public static final RegistryObject<Block> ACTIVE_NETHER_REACTOR_CORE = REGISTRY.register("active_nether_reactor_core", () -> new ActiveNetherReactorCoreBlock());
	public static final RegistryObject<Block> FINISHED_NETHER_REACTOR_CORE = REGISTRY.register("finished_nether_reactor_core", () -> new FinishedNetherReactorCoreBlock());
	// Start of user code block custom blocks
	// End of user code block custom blocks
}
