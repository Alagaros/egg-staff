/**
 * Egg Staff
 *
 * 
 * @Author Dalthow Game Studios 
 * @Class RegisterHandler;
 * 
 **/

package com.dalthow.eggstaff.handler;

import net.minecraft.item.Item;

import com.dalthow.eggstaff.base.Reference;

import cpw.mods.fml.common.registry.GameRegistry;

public class RegisterHandler 
{
	// Registers a item so that it gets loaded in the game
	
	public static void registerItem(Item item)
	{
		GameRegistry.registerItem(item, Reference.modId + "_" + item.getUnlocalizedName().substring(5));
	}
}
