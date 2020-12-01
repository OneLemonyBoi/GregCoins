package lodsofemone;

import com.google.common.collect.BiMap;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

import java.util.ArrayList;
import java.lang.Math;

@Mod(modid = LodsOfEmone.MODID, acceptedMinecraftVersions = "[1.12, 1.13)", dependencies = "required-after:ftbmoney", guiFactory = "lodsofemone.GuiFactory")
@Mod.EventBusSubscriber
public class LodsOfEmone
{
    public static final String MODID = "lodsofemone";

    public static Configuration config;

    public static int[] coinSmallValues;
    public static int[] coinBigValues;

// GT Coins
    public static int[] coinCupronickelValues;
    public static int[] coinSilverValues;
    public static int[] coinGoldValues;
    public static int[] coinPlatinumValues;
    public static int[] coinOsmiumValues;
    public static int[] coinNaquadahValues;
    public static int[] coinDarmstadtiumValues;

    @GameRegistry.ObjectHolder("lodsofemone:coin_small")
    public static Item COIN_SMALL;
    @GameRegistry.ObjectHolder("lodsofemone:coin_big")
    public static Item COIN_BIG;
    
    // Custom GT Coins
    
    @GameRegistry.ObjectHolder("lodsofemone:coin_cupronickel")
    public static Item COIN_CUPRONICKEL;
    @GameRegistry.ObjectHolder("lodsofemone:coin_silver")
    public static Item COIN_SILVER;
    @GameRegistry.ObjectHolder("lodsofemone:coin_gold")
    public static Item COIN_GOLD;
    @GameRegistry.ObjectHolder("lodsofemone:coin_platinum")
    public static Item COIN_PLATINUM;
    @GameRegistry.ObjectHolder("lodsofemone:coin_osmium")
    public static Item COIN_OSMIUM;
    @GameRegistry.ObjectHolder("lodsofemone:coin_naquadah")
    public static Item COIN_NAQUADAH;
    @GameRegistry.ObjectHolder("lodsofemone:coin_darmstadtium")
    public static Item COIN_DARMSTADTIUM;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        config = new Configuration(event.getSuggestedConfigurationFile());

        loadConfig();

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if(event.getModID().equalsIgnoreCase(LodsOfEmone.MODID))
        {
            loadConfig();
        }
    }

    public static void loadConfig() {
        coinSmallValues = config.get("coins", "coinSmallValues", new int[] { 1, 10, 25, 50 }, "List of small coin denominations that show up in JEI.").getIntList();
        coinBigValues = config.get("coins", "coinBigValues", new int[] { 100, 200, 500, 1000, 5000, 10000 }, "List of big coin denominations that show up in JEI.").getIntList();
        coinCupronickelValues = config.get("coins", "coinCupronickelValues", new int[] { 1 }, "List of Cupronickel coin denominations that show up in JEI.").getIntList();
        coinSilverValues = config.get("coins", "coinSilverValues", new int[] { 8 }, "List of Silver coin denominations that show up in JEI.").getIntList();
        coinGoldValues = config.get("coins", "coinGoldValues", new int[] { 64 }, "List of Gold coin denominations that show up in JEI.").getIntList();
        coinPlatinumValues = config.get("coins", "coinPlatinumValues", new int[] { 512 }, "List of Platinum coin denominations that show up in JEI.").getIntList();
        coinOsmiumValues = config.get("coins", "coinOsmiumValues", new int[] { 4096 }, "List of Osmium coin denominations that show up in JEI.").getIntList();
        coinNaquadahValues = config.get("coins", "coinNaquadahValues", new int[] { 32768 }, "List of Naquadah coin denominations that show up in JEI.").getIntList();
        coinDarmstadtiumValues = config.get("coins", "coinDarmstadtiumValues", new int[] { 262144 }, "List of Darmstadtium coin denominations that show up in JEI.").getIntList();
       
        if (config.hasChanged()) {
            config.save();
        }
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {

        event.getRegistry().register(COIN_CUPRONICKEL = new ItemCoin() {
            @Override
            public int[] getDenominations() {
                return coinCupronickelValues;
            }
        }.setRegistryName(new ResourceLocation(LodsOfEmone.MODID,"coin_cupronickel")).setUnlocalizedName("coin_cupronickel").setCreativeTab(CreativeTabs.MATERIALS));
        event.getRegistry().register(COIN_SILVER = new ItemCoin() {
            @Override
            public int[] getDenominations() {
                return coinSilverValues;
            }
        }.setRegistryName(new ResourceLocation(LodsOfEmone.MODID,"coin_silver")).setUnlocalizedName("coin_silver").setCreativeTab(CreativeTabs.MATERIALS));
        event.getRegistry().register(COIN_GOLD = new ItemCoin() {
            @Override
            public int[] getDenominations() {
                return coinGoldValues;
            }
        }.setRegistryName(new ResourceLocation(LodsOfEmone.MODID,"coin_gold")).setUnlocalizedName("coin_gold").setCreativeTab(CreativeTabs.MATERIALS));
        event.getRegistry().register(COIN_PLATINUM = new ItemCoin() {
            @Override
            public int[] getDenominations() {
                return coinPlatinumValues;
            }
        }.setRegistryName(new ResourceLocation(LodsOfEmone.MODID,"coin_platinum")).setUnlocalizedName("coin_platinum").setCreativeTab(CreativeTabs.MATERIALS));
        event.getRegistry().register(COIN_OSMIUM = new ItemCoin() {
            @Override
            public int[] getDenominations() {
                return coinOsmiumValues;
            }
        }.setRegistryName(new ResourceLocation(LodsOfEmone.MODID,"coin_osmium")).setUnlocalizedName("coin_osmium").setCreativeTab(CreativeTabs.MATERIALS));
        event.getRegistry().register(COIN_NAQUADAH = new ItemCoin() {
            @Override
            public int[] getDenominations() {
                return coinNaquadahValues;
            }
        }.setRegistryName(new ResourceLocation(LodsOfEmone.MODID,"coin_naquadah")).setUnlocalizedName("coin_naquadah").setCreativeTab(CreativeTabs.MATERIALS));
        event.getRegistry().register(COIN_DARMSTADTIUM = new ItemCoin() {
            @Override
            public int[] getDenominations() {
                return coinDarmstadtiumValues;
            }
        }.setRegistryName(new ResourceLocation(LodsOfEmone.MODID,"coin_darmstadtium")).setUnlocalizedName("coin_darmstadtium").setCreativeTab(CreativeTabs.MATERIALS));



        event.getRegistry().register(COIN_SMALL = new ItemCoin() {
            @Override
            public int[] getDenominations() {
                return coinSmallValues;
            }
        }.setRegistryName(new ResourceLocation(LodsOfEmone.MODID,"coin_small")).setUnlocalizedName("coin_small").setCreativeTab(CreativeTabs.MATERIALS));
        event.getRegistry().register(COIN_BIG = new ItemCoin() {
            @Override
            public int[] getDenominations() {
                return coinBigValues;
            }
        }.setRegistryName(new ResourceLocation(LodsOfEmone.MODID,"coin_big")).setUnlocalizedName("coin_big").setCreativeTab(CreativeTabs.MATERIALS));
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void registerModels(ModelRegistryEvent event) {
        registerItemModel(COIN_SMALL, 0, "inventory");
        registerItemModel(COIN_BIG, 0, "inventory");

        registerItemModel(COIN_CUPRONICKEL, 0, "inventory");
        registerItemModel(COIN_SILVER, 0, "inventory");
        registerItemModel(COIN_GOLD, 0, "inventory");
        registerItemModel(COIN_PLATINUM, 0, "inventory");
        registerItemModel(COIN_OSMIUM, 0, "inventory");
        registerItemModel(COIN_NAQUADAH, 0, "inventory");
        registerItemModel(COIN_DARMSTADTIUM, 0, "inventory");
    }

    @SideOnly(Side.CLIENT)
    public static void registerItemModel(@Nonnull Item item, int meta, String variant) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), variant));
    }
}
