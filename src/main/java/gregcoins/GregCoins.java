package gregcoins;

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

@Mod(modid = GregCoins.MODID, acceptedMinecraftVersions = "[1.12, 1.13)", dependencies = "required-after:ftbmoney, gregtech", guiFactory = "gregcoins.GuiFactory")
@Mod.EventBusSubscriber
public class GregCoins
{
    public static final String MODID = "gregcoins";

    public static Configuration config;

// GT Coins
    @GameRegistry.ObjectHolder("gregtech:meta_item_1:32001")
    public static Item GT_CUPRONICKEL;
    @GameRegistry.ObjectHolder("gregtech:meta_item_1:32002")
    public static Item GT_SILVER;
    @GameRegistry.ObjectHolder("gregtech:meta_item_1:32003")
    public static Item GT_GOLD;
    @GameRegistry.ObjectHolder("gregtech:meta_item_1:32004")
    public static Item GT_PLATINUM;
    @GameRegistry.ObjectHolder("gregtech:meta_item_1:32005")
    public static Item GT_OSMIUM;
    @GameRegistry.ObjectHolder("gregtech:meta_item_1:32006")
    public static Item GT_NAQUADAH;
    @GameRegistry.ObjectHolder("gregtech:meta_item_1:32007")
    public static Item GT_DARMSTADTIUM;

    public static final ItemStack cupronickelItemStack = new ItemStack(GT_CUPRONICKEL);
    public static final ItemStack silverItemStack = new ItemStack(GT_SILVER);
    public static final ItemStack goldItemStack = new ItemStack(GT_GOLD);
    public static final ItemStack platinumItemStack = new ItemStack(GT_PLATINUM);
    public static final ItemStack osmiumItemStack = new ItemStack(GT_OSMIUM);
    public static final ItemStack naquahahItemStack = new ItemStack(GT_NAQUADAH);
    public static final ItemStack darmstadtiumItemStack = new ItemStack(GT_DARMSTADTIUM);


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }
