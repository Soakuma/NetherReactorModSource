package net.pinne.netherreactormod.init;

import net.pinne.netherreactormod.NetherreactormodMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

public class NetherreactormodModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, NetherreactormodMod.MODID);
	public static final RegistryObject<Item> NETHER_REACTOR_CORE = block(NetherreactormodModBlocks.NETHER_REACTOR_CORE);
	public static final RegistryObject<Item> GLOWING_OBSIDIAN = block(NetherreactormodModBlocks.GLOWING_OBSIDIAN);
	public static final RegistryObject<Item> ACTIVE_NETHER_REACTOR_CORE = block(NetherreactormodModBlocks.ACTIVE_NETHER_REACTOR_CORE);
	public static final RegistryObject<Item> FINISHED_NETHER_REACTOR_CORE = block(NetherreactormodModBlocks.FINISHED_NETHER_REACTOR_CORE);

	// Start of user code block custom items
	// End of user code block custom items
	private static RegistryObject<Item> block(RegistryObject<Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
	}
}
