package com.dalthow.eggstaff.handler;

import net.minecraft.item.Item;

import com.dalthow.eggstaff.base.Reference;

import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Egg Staff
 *
 * @author Trevi Awater
 **/

public class RegisterHandler 
{
	/**
     * Registers a item so that it gets loaded in the game.
     *
     * @param item The item that should be registered.
     */
	public static void registerItem(Item item)
	{
		GameRegistry.registerItem(item, Reference.modId + "_" + item.getUnlocalizedName().substring(5));
	}
}
