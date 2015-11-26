package com.dalthow.eggstaff.handler;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.dalthow.eggstaff.base.Main;

import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Egg Staff
 *
 * @author Trevi Awater
 **/

public class RecipeHandler 
{
	/**
     * Adds custom recipe's to the game if they are allowed to be crafted.
     */
	public static void addRecipes()
	{
		if(Main.recipeEggStaff.getBoolean(false))
		{
			GameRegistry.addRecipe(new ItemStack(Main.itemEggStaff, 1),
			"  E",
			" S ",
			"N  ",

			'E', Items.egg, 'S', Items.stick, 'N', Items.gold_nugget);
		}
		
		if(Main.recipeCapturingEgg.getBoolean(false))
		{
			GameRegistry.addRecipe(new ItemStack(Main.itemCapturingEgg, 4),
			"SLS",
			"FEM",
			"SZS",

			'L', Items.leather, 'S', Items.string, 'E', Items.egg, 'F', Items.feather, 'Z', Items.rotten_flesh, 'M', Items.spider_eye);
		}
	}
}
