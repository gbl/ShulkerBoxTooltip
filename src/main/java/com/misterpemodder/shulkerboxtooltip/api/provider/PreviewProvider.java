package com.misterpemodder.shulkerboxtooltip.api.provider;

import java.util.Collections;
import java.util.List;
import com.misterpemodder.shulkerboxtooltip.api.renderer.PreviewRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

/**
 * Provides various infos about item preview such as the contained items.
 * @since 1.3.0
 */
@Environment(EnvType.CLIENT)
public interface PreviewProvider {
  /**
   * The default inventory color.
   * @since 1.3.0
   */
  static float[] DEFAULT_COLOR = new float[] {1f, 1f, 1f};

  /**
   * Queries if the preview window should be displayed for the given stack.
   * Should return {@code false} if the inventory if empty.
   * @param stack The stack.
   * @return Whether the preview should be displayed.
   * @since 1.3.0
   */
  boolean shouldDisplay(ItemStack stack);

  /**
   * Fetches the items to be displayed in the preview.
   * @param stack The preview stack
   * @return The list of items, may not be null or contain null elements.
   * @since 1.3.0
   */
  List<ItemStack> getInventory(ItemStack stack);

  /**
   * @param stack The stack.
   * @return The maximum inventory size for the given stack.
   * @since 1.3.0
   */
  int getInventoryMaxSize(ItemStack stack);

  /**
   * The maximum number of item stacks to be displayed in a row.
   * @param stack The stack.
   * @return the row size, defaults the max row size in config if 0.
   */
  default int getMaxRowSize(ItemStack stack) {
    return 0;
  }

  /**
   * @param stack The stack.
   * @return If false, compact mode will be the only type of preview.
   * @since 1.3.0
   */
  default boolean isFullPreviewAvailable(ItemStack stack) {
    return true;
  }

  /**
   * Should hint be shown in the item's tooltip?
   * @param stack The stack.
   * @return whether the hints should be shown.
   * @since 1.3.0
   */
  default boolean showTooltipHints(ItemStack stack) {
    return true;
  }

  /**
   * @param stack The stack.
   * @return The text to be displayed for the compact preview mode.
   * @since 1.3.0
   */
  default String getTooltipHintLangKey(ItemStack stack) {
    return "shulkerboxtooltip.hint.compact";
  }

  /**
   * @param stack The stack.
   * @return The text to be displayed for the full preview mode.
   * @since 1.3.0
   */
  default String getFullTooltipHintLangKey(ItemStack stack) {
    return "shulkerboxtooltip.hint.full";
  }

  /**
   * Which color the preview window should be in?
   * @param stack The stack.
   * @return An array of three floats (RGB). if {@code color.length < 3},
   * {@link #DEFAULT_COLOR} will be used.
   * @since 1.3.0
   */
  default float[] getWindowColor(ItemStack stack) {
    return DEFAULT_COLOR;
  }

  /**
   * @return A {@link PreviewRenderer} instance.
   * @since 1.3.0
   */
  default PreviewRenderer getRenderer() {
    return PreviewRenderer.getDefaultRendererInstance();
  }

  /**
   * Adds lines the stack tooltip.
   * Returned lines are added only if tooltip type is set to {@code MODDED} in the config.
   * @param stack The stack.
   * @return A list of Text components. If empty, no text will be added to the tooltip.
   * @since 1.4.0
   */
  @SuppressWarnings("unchecked")
  default List<Text> addTooltip(ItemStack stack) {
    return Collections.EMPTY_LIST;
  }
}
