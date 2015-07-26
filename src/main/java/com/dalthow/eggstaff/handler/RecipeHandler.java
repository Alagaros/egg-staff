/**
 * Egg Staff
 *
 * 
 * @Author Dalthow Game Studios 
 * @Class RecipeHandler.java
 * 
 **/

package com.dalthow.eggstaff.handler;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.dalthow.eggstaff.base.Main;

import cpw.mods.fml.common.registry.GameRegistry;

public class RecipeHandler 
{
	// Is called during the initialisation of the game to load the modded recipes
	
	public static void addRecipes()
	{
		if(Main.recipeEggStaff.getBoolean(false) == true)
		{
			GameRegistry.addRecipe(new ItemStack(Main.itemEggStaff, 1), new Object[]
			{
				"  E",
				" S ",
				"N  ",
				
				'E', Items.egg, 'S', Items.stick, 'N', Items.gold_nugget 
			});
		}
		
		if(Main.recipeCapturingEgg.getBoolean(false) == true)
		{
			GameRegistry.addRecipe(new ItemStack(Main.itemCapturingEgg, 4), new Object[]
			{
				"SLS",
				"FEM",
				"SZS",
				
				'L', Items.leather, 'S', Items.string, 'E', Items.egg, 'F', Items.feather, 'Z', Items.rotten_flesh, 'M', Items.spider_eye 
			});
		}
	}
}
