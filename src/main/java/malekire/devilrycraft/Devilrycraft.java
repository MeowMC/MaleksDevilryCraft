package malekire.devilrycraft;

import com.tfc.minecraft_effekseer_implementation.meifabric.MEIFabric;
import com.tfc.minecraft_effekseer_implementation.meifabric.client.MEIFabricClient;
import malekire.devilrycraft.blockentities.BasicInfuserBlockEntity;
import malekire.devilrycraft.blockentities.MagicalCauldronBlockEntity;
import malekire.devilrycraft.blockentities.PortableHoleBlockEntity;
import malekire.devilrycraft.blockentities.VisPipeBlockEntity;
import malekire.devilrycraft.common.*;
import malekire.devilrycraft.entities.SmallDirectionalLightningEntity;
import malekire.devilrycraft.fluids.DevilryFluidRegistry;
import malekire.devilrycraft.recipies.BasicInfuserRecipeSerializer;
import malekire.devilrycraft.recipies.Type;
import malekire.devilrycraft.screen_stuff.screen_handlers.BasicInfuserScreenHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Devilrycraft implements ModInitializer {
    public static final String MOD_ID = "devilry_craft";
    public static final Logger LOGGER = LogManager.getLogger("Devilrycraft");

    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier("devilry_craft", "general"),
            () -> new ItemStack(DevilryItems.VIS_CRYSTAL));

    public static final ScreenHandlerType<BasicInfuserScreenHandler> BASIC_INFUSER_SCREEN_HANDLER;
    public static final Identifier BASIC_INFUSER_GUI;
    static {

        BASIC_INFUSER_GUI = new Identifier(MOD_ID, "textures/gui/basic_infuser_gui.png");
        BASIC_INFUSER_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(MOD_ID, "basic_infuser"), BasicInfuserScreenHandler::new);
    }



    public static Identifier CHAOS_PORTAL_ID = new Identifier(MOD_ID, "chaos_portal");
    public static SoundEvent CHAOS_PORTAL = new SoundEvent(CHAOS_PORTAL_ID);
    public static Identifier CAULDRON_BUBBLING_ID = new Identifier(MOD_ID, "cauldron_bubbling");
    public static SoundEvent CAULDRON_BUBBLING = new SoundEvent(CAULDRON_BUBBLING_ID);

    public static final EntityType<SmallDirectionalLightningEntity> SMALL_DIRECTIONAL_LIGHTNING_ENTITY = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier("devilry_craft", "small_directional_lightning"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, SmallDirectionalLightningEntity::new).dimensions(EntityDimensions.fixed(0f, 0f)).trackRangeBlocks(16).trackedUpdateRate(Integer.MAX_VALUE).build()
    );
    @Override
    public void onInitialize() {
        DevilryOreGeneration.RegisterFeatures();
        DevilryTreeGeneration.RegisterFeatures();
        Registry.register(Registry.SOUND_EVENT, CHAOS_PORTAL_ID, CHAOS_PORTAL);
        Registry.register(Registry.SOUND_EVENT, CAULDRON_BUBBLING_ID, CAULDRON_BUBBLING);

        Registry.register(Registry.RECIPE_SERIALIZER, BasicInfuserRecipeSerializer.ID, BasicInfuserRecipeSerializer.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, new Identifier(MOD_ID, "basic_infuser_recipe"), Type.INSTANCE);

        DevilryFluidRegistry.RegisterFluids();



    RegisterBlocks();
    RegisterItems();
    RegisterBlockItems();
    DevilryBlockEntities.MAGICAL_CAULDRON_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "devilry_craft:magical_cauldron",
            BlockEntityType.Builder.create(MagicalCauldronBlockEntity::new, DevilryBlocks.MAGICAL_CAULDRON_BLOCK).build(null));
        DevilryBlockEntities.PORTABLE_HOLE_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "devilry_craft:portable_hole",
                BlockEntityType.Builder.create(PortableHoleBlockEntity::new, DevilryBlocks.PORTABLE_HOLE_BLOCK).build(null));
        DevilryBlockEntities.BASIC_INFUSER_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "devilry_craft:basic_infuser",
                BlockEntityType.Builder.create(BasicInfuserBlockEntity::new, DevilryBlocks.BASIC_INFUSER).build(null));
        DevilryBlockEntities.PIPE_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "devilry_craft:pipe/default",
                BlockEntityType.Builder.create(VisPipeBlockEntity::new, DevilryBlocks.VIS_PIPE).build(null));


    }



    public static void RegisterBlocks() {
        for(BlockRegistryHelper block : DevilryBlocks.blocks)
            Registry.register(Registry.BLOCK, block.identifier, block.block);
    }
    public static void RegisterItems() {
        for(ItemRegistryHelper item : DevilryItems.items)
            Registry.register(Registry.ITEM, item.identifier, item.item);
    }
    public static void RegisterBlockItems() {
        for(ItemRegistryHelper blockItem : DevilryBlockItems.blockItems)
            Registry.register(Registry.ITEM, blockItem.identifier, blockItem.item);
    }
}
