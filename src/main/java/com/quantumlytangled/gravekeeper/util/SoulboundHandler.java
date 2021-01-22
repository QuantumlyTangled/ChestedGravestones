package com.quantumlytangled.gravekeeper.util;

import com.quantumlytangled.gravekeeper.GraveKeeperConfig;
import java.util.Map;
import javax.annotation.Nonnull;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class SoulboundHandler {

  public static boolean isSoulbound(@Nonnull final ItemStack itemStack) {
    // check for enchantments
    final Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(itemStack);
    for (final Enchantment enchantment : enchantments.keySet()) {
      if (enchantment != null) {
        if (GraveKeeperConfig.ANY_ENCHANT_IS_SOULBOUND
            || GraveKeeperConfig.SOULBOUND_ENCHANTMENTS.contains(enchantment)) {
          return true;
        }
      }
    }

    // check for NBT tags
    final NBTTagCompound tagCompound = itemStack.getTagCompound();
    if (tagCompound != null) {
      for (final String tagBoolean : GraveKeeperConfig.SOULBOUND_TAG_BOOLEAN) {
        if (tagCompound.hasKey(tagBoolean)
            && tagCompound.getBoolean(tagBoolean)) {
          return true;
        }
      }
    }
    return false;
  }

}