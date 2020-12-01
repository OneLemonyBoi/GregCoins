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

@Mod(modid = LodsOfEmone.MODID, acceptedMinecraftVersions = "[1.12, 1.13)", dependencies = "required-after:ftbmoney", guiFactory = "lodsofemone.GuiFactory")
@Mod.EventBusSubscriber
public class LodsOfEmone
{
    public static final String MODID = "lodsofemone";

    public static Configuration config;

    public static int[] coinSmallValues;
    public static int[] coinBigValues;

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
        
        // GT Coins
        
        coinCupronickelValues = config.get("coins", "coinCupronickelValues", new int[] { 100, 200, 500, 1000, 5000, 10000 }, "Coin values that show up in JEI, if you need to change them.").getIntList();
        coinSilverValues = config.get("coins", "coinSilverValues", new int[] { 100, 200, 500, 1000, 5000, 10000 }, "Coin values that show up in JEI, if you need to change them.").getIntList();
        coinGoldValues = config.get("coins", "coinGoldValues", new int[] { 100, 200, 500, 1000, 5000, 10000 }, "Coin values that show up in JEI, if you need to change them.").getIntList();
        coinPlatinumValues = config.get("coins", "coinPlatinumValues", new int[] { 100, 200, 500, 1000, 5000, 10000 }, "Coin values that show up in JEI, if you need to change them.").getIntList();
        coinOsmiumValues = config.get("coins", "coinOsmiumValues", new int[] { 100, 200, 500, 1000, 5000, 10000 }, "Coin values that show up in JEI, if you need to change them.").getIntList();
        coinNaquadahValues = config.get("coins", "coinNaquadahValues", new int[] { 100, 200, 500, 1000, 5000, 10000 }, "Coin values that show up in JEI, if you need to change them.").getIntList();
        coinDarmstadtiumValues = config.get("coins", "coinDarmstadtiumValues", new int[] { 100, 200, 500, 1000, 5000, 10000 }, "Coin values that show up in JEI, if you need to change them.").getIntList();
       
        if (config.hasChanged()) {
            config.save();
        }
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        String[] coins = {"Copper", "Cupronickel", "Silver", "Gold", "Platinum", "Osmium", "Naquadah", "Darmstadtium"};
        for (String coin : coins) {
            String coinLower = coin.toLowerCase();
            String coinUpper = coin.toUpperCase();
            String coinVarRegister = "COIN_" + coinUpper;
            String coinVarValues = "coin" + coin + "Values;
            String coinVarRegisterLower = "coin_" + coinLower;
            event.getRegistry().register(CoinVarRegister = new ItemCoin() {
                @Override
                public int[] getDenominations() {
                    return coinVarValues;
                }
            }.setRegistryName(new ResourceLocation(LodsOfEmone.MODID,coinVarRegisterLower)).setUnlocalizedName(coinVarRegisterLower).setCreativeTab(CreativeTabs.MATERIALS));
        }
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
    }

    @SideOnly(Side.CLIENT)
    public static void registerItemModel(@Nonnull Item item, int meta, String variant) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), variant));
    }
}
