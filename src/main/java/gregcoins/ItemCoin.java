package gregcoins;

import com.feed_the_beast.mods.money.FTBMoney;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public abstract class ItemCoin extends Item {
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand hand) {

        ItemStack heldItem = player.getHeldItem(hand);

        int useCount = 1;

        if (player.isSneaking())
            useCount = heldItem.getCount();

        if(heldItem == GregCoins.cupronickelItemStack){
            long coinValue = 1
            long value = coinValue * useCount;
            FTBMoney.setMoney(player, FTBMoney.getMoney(player) + value);
            player.sendStatusMessage(FTBMoney.moneyComponent(FTBMoney.getMoney(player)),true);
        }

        if(heldItem == GregCoins.silverItemStack){
            long coinValue = 8
            long value = coinValue * useCount;
            FTBMoney.setMoney(player, FTBMoney.getMoney(player) + value);
            player.sendStatusMessage(FTBMoney.moneyComponent(FTBMoney.getMoney(player)),true);
        }

        if(heldItem == GregCoins.goldItemStack){
            long coinValue = 64
            long value = coinValue * useCount;
            FTBMoney.setMoney(player, FTBMoney.getMoney(player) + value);
            player.sendStatusMessage(FTBMoney.moneyComponent(FTBMoney.getMoney(player)),true);
        }

        if(heldItem == GregCoins.platinumItemStack){
            long coinValue = 512
            long value = coinValue * useCount;
            FTBMoney.setMoney(player, FTBMoney.getMoney(player) + value);
            player.sendStatusMessage(FTBMoney.moneyComponent(FTBMoney.getMoney(player)),true);
        }

        if(heldItem == GregCoins.osmiumItemStack){
            long coinValue = 4096
            long value = coinValue * useCount;
            FTBMoney.setMoney(player, FTBMoney.getMoney(player) + value);
            player.sendStatusMessage(FTBMoney.moneyComponent(FTBMoney.getMoney(player)),true);
        }

        if(heldItem == GregCoins.naquadahItemStack){
            long coinValue = 32768
            long value = coinValue * useCount;
            FTBMoney.setMoney(player, FTBMoney.getMoney(player) + value);
            player.sendStatusMessage(FTBMoney.moneyComponent(FTBMoney.getMoney(player)),true);
        }

        if(heldItem == GregCoins.darmstadtiumItemStack){
            long coinValue = 262144
            long value = coinValue * useCount;
            FTBMoney.setMoney(player, FTBMoney.getMoney(player) + value);
            player.sendStatusMessage(FTBMoney.moneyComponent(FTBMoney.getMoney(player)),true);
        }


        heldItem.shrink(useCount);

        return new ActionResult<>(EnumActionResult.SUCCESS, heldItem);
    }
}
